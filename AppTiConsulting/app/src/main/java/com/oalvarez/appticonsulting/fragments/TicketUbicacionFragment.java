package com.oalvarez.appticonsulting.fragments;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class TicketUbicacionFragment extends Fragment implements OnMapReadyCallback {


    @BindView(R.id.mapSede)
    MapView mapSede;
    @BindView(R.id.etDireccionSede)
    EditText etDireccionSede;
    @BindView(R.id.tilDireccionSede)
    TextInputLayout tilDireccionSede;
    @BindView(R.id.etContactoSede)
    EditText etContactoSede;
    @BindView(R.id.tilContactoSede)
    TextInputLayout tilContactoSede;
    @BindView(R.id.etUsuarioAtencion)
    EditText etUsuarioAtencion;
    @BindView(R.id.tilUsuarioAtencion)
    TextInputLayout tilUsuarioAtencion;
    @BindView(R.id.btnRegresar)
    Button btnRegresar;

    public TicketUbicacionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticket_ubicacion, container, false);
        ButterKnife.bind(this, view);

        final Bundle bundle = this.getArguments();

        if (bundle != null){

            final String sNombreCliente = bundle.getString("nombreCliente");
            final String sSedeCliente = bundle.getString("sedeCliente");
            final Double nLatitud = Double.parseDouble(bundle.getString("latitud"));
            final Double nLongitud = Double.parseDouble(bundle.getString("longitud"));
            String sDireccionSede = bundle.getString("direccionSede");
            String sContactoSede = bundle.getString("contactoSede");
            String sUsuarioAtencion = bundle.getString("usuarioAtencion");

            etDireccionSede.setText(sDireccionSede);
            etContactoSede.setText(sContactoSede);
            etUsuarioAtencion.setText(sUsuarioAtencion);

            mapSede.onCreate(savedInstanceState);
            mapSede.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                    googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(nLatitud, nLongitud))
                        .title(sNombreCliente)
                        .snippet(sSedeCliente));

                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new
                            LatLng(nLatitud, nLongitud), 17));
                }
            });

        }



        return view;
    }

    @OnClick(R.id.btnRegresar)
    public void onClick() {
        getFragmentManager().popBackStack();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

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
}
