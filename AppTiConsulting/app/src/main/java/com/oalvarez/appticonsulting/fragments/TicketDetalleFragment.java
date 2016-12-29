package com.oalvarez.appticonsulting.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.oalvarez.appticonsulting.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TicketDetalleFragment extends Fragment implements OnMapReadyCallback {


    @BindView(R.id.mapSede)
    MapView mapSede;
    @BindView(R.id.tvNroTicket)
    TextView tvNroTicket;
    @BindView(R.id.tvClienteTicket)
    TextView tvClienteTicket;
    @BindView(R.id.tvSedeTicket)
    TextView tvSedeTicket;
    @BindView(R.id.tvTituloTicket)
    TextView tvTituloTicket;

    public TicketDetalleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticket_detalle, container, false);
        ButterKnife.bind(this, view);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            int nroTicket = bundle.getInt("nroticket");
            tvNroTicket.setText(String.valueOf(nroTicket));
        }

        mapSede.onCreate(savedInstanceState);
        mapSede.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                //googleMap.setMyLocationEnabled(true);
                googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(-16.3909262, -71.5529233))
                        .title("Prueba")
                        .snippet("Demo")
                );

                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-16.3909262, -71.5529233), 17));
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mapSede != null) {
            mapSede.onResume();
        }
    }

    @Override
    public void onPause() {
        if (mapSede != null) {
            mapSede.onPause();
        }
        super.onPause();
    }

    @Override
    public void onDestroy() {
        if (mapSede != null) {
            try {
                mapSede.onDestroy();
            } catch (NullPointerException ex) {
                Log.e("TAG", "Error while attempting MapView.onDestroy(), ignoring exception", ex);
            }
        }

        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (mapSede != null) {
            mapSede.onLowMemory();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mapSede != null) {
            mapSede.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
//        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//        googleMap.addMarker(new MarkerOptions()
//                .position(new LatLng(-16.3909262, -71.5529233))
//                .title("Prueba")
//                .snippet("Demo"));
    }
}
