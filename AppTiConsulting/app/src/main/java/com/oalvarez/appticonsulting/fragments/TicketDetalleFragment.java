package com.oalvarez.appticonsulting.fragments;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.oalvarez.appticonsulting.R;
import com.oalvarez.appticonsulting.entidades.Ticket;
import com.oalvarez.appticonsulting.servicios.HelperWs;
import com.oalvarez.appticonsulting.servicios.TicketsApiWs;
import com.oalvarez.appticonsulting.views.MovableButton;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TicketDetalleFragment extends Fragment implements OnMapReadyCallback {


    @BindView(R.id.mapSede)
    MapView mapSede;

    @BindView(R.id.etNroTicket)
    EditText etNroTicket;
    @BindView(R.id.etEstadoTicket)
    EditText etEstadoTicket;
    @BindView(R.id.tilNroTicket)
    TextInputLayout tilNroTicket;
    @BindView(R.id.tilEstadoTicket)
    TextInputLayout tilEstadoTicket;
    @BindView(R.id.etClienteTicket)
    EditText etClienteTicket;
    @BindView(R.id.tilClienteTicket)
    TextInputLayout tilClienteTicket;
    @BindView(R.id.etSedeTicket)
    EditText etSedeTicket;
    @BindView(R.id.tilSedeTicket)
    TextInputLayout tilSedeTicket;
    @BindView(R.id.etFechaTicket)
    EditText etFechaTicket;
    @BindView(R.id.tilFechaTicket)
    TextInputLayout tilFechaTicket;
    @BindView(R.id.etTituloTicket)
    EditText etTituloTicket;
    @BindView(R.id.tilTituloTicket)
    TextInputLayout tilTituloTicket;
    @BindView(R.id.etDetalleTicket)
    EditText etDetalleTicket;
    @BindView(R.id.tilDetalleTicket)
    TextInputLayout tilDetalleTicket;
    @BindView(R.id.etSolucionTicket)
    EditText etSolucionTicket;
    @BindView(R.id.tilSolucionTicket)
    TextInputLayout tilSolucionTicket;
    @BindView(R.id.nsvScroll)
    NestedScrollView nsvScroll;
    @BindView(R.id.btnAtender)
    MovableButton btnAtender;
    @BindView(R.id.btnRepuesto)
    MovableButton btnRepuesto;
    @BindView(R.id.btnEsperaRepuesto)
    MovableButton btnEsperaRepuesto;
    @BindView(R.id.layoutTicketDetalle)
    CoordinatorLayout layoutTicketDetalle;

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

        mapSede.onCreate(savedInstanceState);
        mapSede.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                //googleMap.setMyLocationEnabled(true);
                gMap = googleMap;
                googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        });

        if (bundle != null) {
            int nroTicket = bundle.getInt("nroticket");
            etNroTicket.setText(String.valueOf(nroTicket));

            TicketsApiWs ticketsApiWs = HelperWs.getConfiguration().create(TicketsApiWs.class);
            Call<Ticket> respuesta = ticketsApiWs.ConsultarTicket(nroTicket);

            respuesta.enqueue(new Callback<Ticket>() {
                @Override
                public void onResponse(Call<Ticket> call, Response<Ticket> response) {
                    oTicket = response.body();

                    if (oTicket != null) {

                        etNroTicket.setText(String.valueOf(oTicket.get_nroTicket()));

                        etEstadoTicket.setText(oTicket.get_estadoTicket().get_descripcion());
                        etClienteTicket.setText(oTicket.get_cliente().get_razonSocial());
                        etSedeTicket.setText(oTicket.get_sede().get_nombre());
                        etTituloTicket.setText(oTicket.get_titulo());

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String sFecha = simpleDateFormat.format(oTicket.get_fechaTicket());
                        etFechaTicket.setText(sFecha);

                        etDetalleTicket.setText(oTicket.get_detalle());

                        nLatitud = Double.parseDouble(oTicket.get_sede().get_latitud());
                        nLongitud = Double.parseDouble(oTicket.get_sede().get_longitud());

                        gMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

                        gMap.addMarker(new MarkerOptions()
                                .position(new LatLng(nLatitud, nLongitud))
                                .title(oTicket.get_cliente().get_razonSocial())
                                .snippet(oTicket.get_sede().get_nombre())
                        );

                        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new
                                LatLng(nLatitud, nLongitud), 17));
                    }
                }

                @Override
                public void onFailure(Call<Ticket> call, Throwable t) {
                    Log.d("ERROR: ", t.getMessage());
                }
            });
        }

        desactivarCampos();

        return view;
    }

    private void desactivarCampos() {
        etNroTicket.setFocusable(false);
        etNroTicket.setClickable(false);

        etEstadoTicket.setFocusable(false);
        etEstadoTicket.setClickable(false);

        etClienteTicket.setFocusable(false);
        etClienteTicket.setClickable(false);

        etSedeTicket.setFocusable(false);
        etSedeTicket.setClickable(false);

        etFechaTicket.setFocusable(false);
        etFechaTicket.setClickable(false);

        etTituloTicket.setFocusable(false);
        etTituloTicket.setClickable(false);

        etDetalleTicket.setFocusable(false);
        etDetalleTicket.setClickable(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mapSede != null) {
            mapSede.onResume();
        }

        mapSede.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        nsvScroll.requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        nsvScroll.requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return mapSede.onTouchEvent(motionEvent);
            }
        });
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

    @OnClick(R.id.btnAtender)
    public void onClick() {
//        Toast.makeText(getActivity(), "FAB",
//                Toast.LENGTH_SHORT).show();
        Ticket ticket = new Ticket();
        ticket.set_nroTicket(oTicket.get_nroTicket());
        ticket.set_solucion(etSolucionTicket.getText().toString());
        ticket.set_observaciones("");
        ticket.set_ordenServicio("");
        ticket.set_usuarioAsignado(oTicket.get_usuarioAsignado());

        //Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        //String sJson = gson.toJson(ticket);
        //etSolucionTicket.setText(sJson);
        //Toast.makeText(getActivity(), sJson, Toast.LENGTH_SHORT).show();

        TicketsApiWs ticketsApiWs = HelperWs.getConfiguration().create(TicketsApiWs.class);
        Call<ResponseBody> respuesta = ticketsApiWs.AtenderTicket(ticket);

        respuesta.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    Snackbar snackbar = Snackbar.make(nsvScroll, "Ticket Atendido", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    //Toast.makeText(getActivity(), "Ticket Atendido", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.btnEsperaRepuesto)
    public void esperarRepuesto(){
        Snackbar snackbar = Snackbar.make(nsvScroll, "Prueba", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @OnClick(R.id.btnRepuesto)
    public void agregarRepuesto(){
        Snackbar snackbar = Snackbar.make(layoutTicketDetalle, "Prueba", Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
