package com.oalvarez.appticonsulting1.gps;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.oalvarez.appticonsulting1.entidades.SessionManager;
import com.oalvarez.appticonsulting1.entidades.Token;
import com.oalvarez.appticonsulting1.entidades.UbicacionGps;
import com.oalvarez.appticonsulting1.servicios.HelperWs;
import com.oalvarez.appticonsulting1.servicios.TicketsApiWs;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by oalvarez on 09/03/2017.
 */

public class GpsService extends Service {

    private static final String TAG = "GestiGPS";
    private LocationManager locationManager = null;
    private static final int LOCATION_INTERVAL = 3000;
    private static final float LOCATION_DISTANCE= 30f;

    private String idUsuario;
    private int idTipoUsuario;

    private SessionManager sessionManager;

    private class LocationListener implements android.location.LocationListener{

        Location mLastLocation;

        public LocationListener(String provider)
        {
            Log.e(TAG, "LocationListener " + provider);
            mLastLocation = new Location(provider);
        }

        @Override
        public void onLocationChanged(Location location) {

            sessionManager = new SessionManager(getApplicationContext());
            Token token = sessionManager.obtenerDatosSesion();

            Log.e(TAG, "onLocationChanged: " + location);
            mLastLocation.set(location);
            Toast.makeText(getApplicationContext(), String.valueOf(location.getLatitude()), Toast.LENGTH_SHORT);

            UbicacionGps ubicacionGps = new UbicacionGps();
            ubicacionGps.set_idUsuario(token.get_idUsuario());
            ubicacionGps.set_latitud(String.valueOf(location.getLatitude()));
            ubicacionGps.set_longitud(String.valueOf(location.getLongitude()));

            TicketsApiWs ticketsApiWs = HelperWs.getConfiguration(getApplicationContext()).create(TicketsApiWs.class);
            Call<ResponseBody> respuesta = ticketsApiWs.RegistrarUbicacion(ubicacionGps);

            respuesta.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code()==200) {
                        Toast.makeText(getApplicationContext(), "Ubicación enviada", Toast.LENGTH_SHORT);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.e(TAG, "onStatusChanged: " + provider);
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.e(TAG, "onProviderEnabled: " + provider);
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.e(TAG, "onProviderDisabled: " + provider);
        }
    }

    LocationListener[] mLocationListeners = new LocationListener[] {
            new LocationListener(LocationManager.GPS_PROVIDER),
            new LocationListener(LocationManager.NETWORK_PROVIDER)
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.e(TAG, "onStartCommand");
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    private void initializeLocationManager() {
        Log.e(TAG, "initializeLocationManager");
        if (locationManager == null) {
            locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        }
    }

    @Override
    public void onCreate()
    {
        Log.e(TAG, "onCreate");
        initializeLocationManager();
        try {
            locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[1]);
        } catch (java.lang.SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "network provider does not exist, " + ex.getMessage());
        }
        try {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[0]);
        } catch (java.lang.SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "gps provider does not exist " + ex.getMessage());
        }
    }

    @Override
    public void onDestroy()
    {

        Log.e(TAG, "onDestroy");
        super.onDestroy();
        if (locationManager != null) {
            for (int i = 0; i < mLocationListeners.length; i++) {
                try {

                    if ( Build.VERSION.SDK_INT >= 23 &&
                            ContextCompat.checkSelfPermission( getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
                            ContextCompat.checkSelfPermission( getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        {
                            locationManager.removeUpdates(mLocationListeners[i]);
                        }
                    }

                } catch (Exception ex) {
                    Log.i(TAG, "fail to remove location listners, ignore", ex);
                }
            }
        }
    }
}
