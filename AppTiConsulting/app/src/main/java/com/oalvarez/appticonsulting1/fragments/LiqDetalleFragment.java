package com.oalvarez.appticonsulting1.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.oalvarez.appticonsulting1.R;
import com.oalvarez.appticonsulting1.adapter.LiquidacionDetAdapter;
import com.oalvarez.appticonsulting1.entidades.Liquidacion;
import com.oalvarez.appticonsulting1.entidades.LiquidacionDetalle;
import com.oalvarez.appticonsulting1.events.ClickListener;
import com.oalvarez.appticonsulting1.events.RecyclerTouchListener;
import com.oalvarez.appticonsulting1.servicios.HelperWs;
import com.oalvarez.appticonsulting1.servicios.TicketsApiWs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
public class LiqDetalleFragment extends Fragment {


    @BindView(R.id.etNroLiq)
    EditText etNroLiq;
    @BindView(R.id.tilNroLiq)
    TextInputLayout tilNroLiq;
    @BindView(R.id.etEstadoLiq)
    EditText etEstadoLiq;
    @BindView(R.id.tilEstadoLiq)
    TextInputLayout tilEstadoLiq;
    @BindView(R.id.etSaldoInicial)
    EditText etSaldoInicial;
    @BindView(R.id.tilSaldoInicial)
    TextInputLayout tilSaldoInicial;
    @BindView(R.id.etMontoAsignado)
    EditText etMontoAsignado;
    @BindView(R.id.tilMontoAsignado)
    TextInputLayout tilMontoAsignado;
    @BindView(R.id.etFechaLiquidacion)
    EditText etFechaLiquidacion;
    @BindView(R.id.tilFechaLiquidacion)
    TextInputLayout tilFechaLiquidacion;
    @BindView(R.id.etTotalLiquidado)
    EditText etTotalLiquidado;
    @BindView(R.id.tilTotalLiquidado)
    TextInputLayout tilTotalLiquidado;
    @BindView(R.id.etSaldoLiquidacion)
    EditText etSaldoLiquidacion;
    @BindView(R.id.tilSaldoLiquidacion)
    TextInputLayout tilSaldoLiquidacion;
    @BindView(R.id.rvDetalleLiq)
    RecyclerView rvDetalleLiq;
    @BindView(R.id.nsvScrollLiq)
    NestedScrollView nsvScrollLiq;
    @BindView(R.id.idbtnAgregarDetalle)
    ImageButton idbtnAgregarDetalle;
    @BindView(R.id.etTotalAsignado)
    EditText etTotalAsignado;
    @BindView(R.id.tilTotalAsignado)
    TextInputLayout tilTotalAsignado;

    private String sIdUsuario;
    private Liquidacion oLiquidacion;
    private ArrayList<LiquidacionDetalle> listaDetalle = new ArrayList<>();
    private LiquidacionDetAdapter adapter;
    private int nroLiquidacion;

    public LiqDetalleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_liq_detalle, container, false);
        ButterKnife.bind(this, view);

        final Bundle bundle = this.getArguments();

        if (bundle != null) {
            sIdUsuario = bundle.getString("idusuario");
            nroLiquidacion = bundle.getInt("nroliquidacion");

            etNroLiq.setText(String.valueOf(nroLiquidacion));
            cargarLiquidacion();

        }

        return view;
    }

    private void cargarLiquidacion() {
        final TicketsApiWs ticketsApiWs = HelperWs.getConfiguration(getActivity()).create(TicketsApiWs.class);
        Call<Liquidacion> respuesta = ticketsApiWs.ConsultarLiquidacion(nroLiquidacion);

        respuesta.enqueue(new Callback<Liquidacion>() {
            @Override
            public void onResponse(Call<Liquidacion> call, Response<Liquidacion> response) {
                oLiquidacion = response.body();

                if (oLiquidacion != null) {
                    etEstadoLiq.setText(oLiquidacion.get_estadoLiquidacion().get_descripcion());
                    etSaldoInicial.setText(String.valueOf(oLiquidacion.get_saldoInicial()));
                    etMontoAsignado.setText(String.valueOf(oLiquidacion.get_montoAsignado()));
                    etTotalAsignado.setText(String.valueOf(oLiquidacion.get_totalAsignado()));

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String sFecha = simpleDateFormat.format(oLiquidacion.get_fechaLiquidacion());
                    etFechaLiquidacion.setText(sFecha);

                    etTotalLiquidado.setText(String.valueOf(oLiquidacion.get_totalLiquidacion()));
                    etSaldoLiquidacion.setText(String.valueOf(oLiquidacion.get_saldoLiquidacion()));

                    desactivarCampos(oLiquidacion.get_idEstadoLiquidacion());

                    final TicketsApiWs ticketsApiWs2 = HelperWs.getConfiguration(getActivity()).create(TicketsApiWs.class);
                    Call<ArrayList<LiquidacionDetalle>> respuestaDetalle = ticketsApiWs2.ListarLiquidacionDetalle(nroLiquidacion);
                    respuestaDetalle.enqueue(new Callback<ArrayList<LiquidacionDetalle>>() {
                        @Override
                        public void onResponse(Call<ArrayList<LiquidacionDetalle>> call, Response<ArrayList<LiquidacionDetalle>> response) {
                            listaDetalle = response.body();
                            if (listaDetalle != null) {
                                adapter = new LiquidacionDetAdapter(listaDetalle);
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                                rvDetalleLiq.setLayoutManager(layoutManager);
                                rvDetalleLiq.setAdapter(adapter);
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<LiquidacionDetalle>> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Liquidacion> call, Throwable t) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        rvDetalleLiq.addOnItemTouchListener(new RecyclerTouchListener(
                getActivity(),
                rvDetalleLiq,
                new ClickListener() {
                    @Override
                    public void onClick(View view, final int position) {
                        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
                        alertBuilder.setTitle(getString(R.string.tituloAlertaConfirmacion));
                        alertBuilder.setMessage("¿Está seguro de ELIMINAR el detalle de la liquidación?");
                        alertBuilder
                                .setCancelable(true)
                                .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        LiquidacionDetalle liquidacionDetalle = listaDetalle.get(position);

                                        TicketsApiWs ticketsApiWs = HelperWs.getConfiguration(getActivity()).create(TicketsApiWs.class);
                                        Call<ResponseBody> respuesta = ticketsApiWs.AgregarDetalleLiquidacion("D", liquidacionDetalle);

                                        respuesta.enqueue(new Callback<ResponseBody>() {
                                            @Override
                                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                                if (response.code() == 200) {
                                                    Toast.makeText(getActivity(), "Se eliminó el detalle de la liquidación", Toast.LENGTH_SHORT).show();
                                                    cargarLiquidacion();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                                            }
                                        });


                                        //Toast.makeText(getActivity(), String.valueOf(liquidacionDetalle.get_motivo()),Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alerta = alertBuilder.create();
                        alerta.show();
                    }

                    @Override
                    public void onLongClick(View view, final int position) {

                    }
                }
        ));
    }

    private void desactivarCampos(int nIdEstado) {
        etNroLiq.setFocusable(false);
        etNroLiq.setClickable(false);

        etEstadoLiq.setFocusable(false);
        etEstadoLiq.setClickable(false);

        etSaldoInicial.setFocusable(false);
        etSaldoInicial.setClickable(false);

        etTotalAsignado.setFocusable(false);
        etTotalAsignado.setClickable(false);

        etMontoAsignado.setFocusable(false);
        etMontoAsignado.setClickable(false);

        etFechaLiquidacion.setFocusable(false);
        etFechaLiquidacion.setClickable(false);

        etTotalLiquidado.setFocusable(false);
        etTotalLiquidado.setClickable(false);

        etSaldoLiquidacion.setFocusable(false);
        etSaldoLiquidacion.setClickable(false);

        if (nIdEstado != 1) {
            idbtnAgregarDetalle.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.idbtnAgregarDetalle)
    public void onClick() {
        Fragment fragment;
        fragment = new LiquidacionDetRegFragment();
        Bundle bundleDetalleLiq = new Bundle();
        bundleDetalleLiq.putString("nroliquidacion", etNroLiq.getText().toString());
        bundleDetalleLiq.putDouble("saldo", Double.parseDouble(etSaldoLiquidacion.getText().toString()));
        fragment.setArguments(bundleDetalleLiq);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment).addToBackStack("fragment");
        ft.commit();
    }
}
