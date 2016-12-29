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
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.oalvarez.appticonsulting.R;
import com.oalvarez.appticonsulting.entidades.Ticket;
import com.oalvarez.appticonsulting.servicios.HelperWs;
import com.oalvarez.appticonsulting.servicios.TicketsApiWs;

import java.io.IOException;
import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    @BindView(R.id.tvFechaTicket)
    TextView tvFechaTicket;
    @BindView(R.id.tvDetalleTicket)
    TextView tvDetalleTicket;

    private Ticket oTicket;
    private Double nLatitud;
    private Double nLongitud;

    GoogleMap gMap;

    public TicketDetalleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticket_detalle, container, false);
        ButterKnife.bind(this, view);


        final Bundle bundle = this.getArguments();
        if (bundle != null) {
            int nroTicket = bundle.getInt("nroticket");
            tvNroTicket.setText(String.valueOf(nroTicket));

            TicketsApiWs ticketsApiWs = HelperWs.getConfiguration().create(TicketsApiWs.class);
            Call<Ticket> respuesta = ticketsApiWs.ConsultarTicket(nroTicket);


            respuesta.enqueue(new Callback<Ticket>() {
                @Override
                public void onResponse(Call<Ticket> call, Response<Ticket> response) {
                    oTicket = response.body();

                    if (oTicket != null) {

                        tvNroTicket.setText(String.valueOf(oTicket.get_nroTicket()));
                        tvClienteTicket.setText(oTicket.get_cliente().get_razonSocial());
                        tvSedeTicket.setText(oTicket.get_sede().get_nombre());
                        tvTituloTicket.setText(oTicket.get_titulo());

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String sFecha = simpleDateFormat.format(oTicket.get_fechaTicket());
                        tvFechaTicket.setText(sFecha);

                        tvDetalleTicket.setText(oTicket.get_detalle());

                        nLatitud = Double.parseDouble(oTicket.get_sede().get_latitud());
                        nLongitud = Double.parseDouble(oTicket.get_sede().get_longitud());

                        

                    }
                }

                @Override
                public void onFailure(Call<Ticket> call, Throwable t) {
                    Log.d("ERROR: ", t.getMessage());
                }
            });
        }

        //if (oTicket != null) {

            mapSede.onCreate(savedInstanceState);
            mapSede.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    //googleMap.setMyLocationEnabled(true);
                    googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                    //if (oTicket != null){
                        nLatitud = -16.395188;
                        nLongitud = -71.548212;

                        googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(nLatitud, nLongitud))
//                                .title(oTicket.get_cliente().get_razonSocial())
//                                .snippet(oTicket.get_sede().get_nombre())
                        );

                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new
                                LatLng(nLatitud, nLongitud), 17));
                    //}

                }
            });

        //}
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

    }

}
