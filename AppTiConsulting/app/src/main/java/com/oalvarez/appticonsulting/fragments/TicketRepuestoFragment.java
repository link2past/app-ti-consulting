package com.oalvarez.appticonsulting.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oalvarez.appticonsulting.R;
import com.oalvarez.appticonsulting.adapter.ListaRepuestoAdapter;
import com.oalvarez.appticonsulting.adapter.TicketDetalleAdapter;
import com.oalvarez.appticonsulting.entidades.Repuesto;
import com.oalvarez.appticonsulting.entidades.TicketDetalle;
import com.oalvarez.appticonsulting.events.ClickListener;
import com.oalvarez.appticonsulting.events.RecyclerTouchListener;
import com.oalvarez.appticonsulting.servicios.HelperWs;
import com.oalvarez.appticonsulting.servicios.TicketsApiWs;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.oalvarez.appticonsulting.R.string.ticket;

/**
 * A simple {@link Fragment} subclass.
 */
public class TicketRepuestoFragment extends Fragment {


    @BindView(R.id.rvRepuestosUsados)
    RecyclerView rvRepuestosUsados;
    @BindView(R.id.etBuscarRepuesto)
    EditText etBuscarRepuesto;
    @BindView(R.id.btnBuscarRepuesto)
    ImageButton btnBuscarRepuesto;
    @BindView(R.id.rvAgregarRepuesto)
    RecyclerView rvAgregarRepuesto;
    @BindView(R.id.tvMensajeRepuesto)
    TextView tvMensajeRepuesto;

    private ArrayList<Repuesto> listaRepuestoBusqueda = new ArrayList<>();
    public ListaRepuestoAdapter listaRepuestoAdapter;

    private ArrayList<TicketDetalle> listaDetalleTicket = new ArrayList<>();
    public TicketDetalleAdapter detalleAdapter;

    private int nroTicket;

    public TicketRepuestoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticket_repuesto, container, false);
        ButterKnife.bind(this, view);

        final Bundle bundle = this.getArguments();
        if (bundle != null) {
            nroTicket = Integer.parseInt(bundle.getString("nroticket"));
            listarRepuestos();
        }

        return view;
    }

    private void listarRepuestos(){
        TicketsApiWs ticketsApiWs = HelperWs.getConfiguration(getActivity()).create(TicketsApiWs.class);
        Call<ArrayList<TicketDetalle>> respuesta = ticketsApiWs.ListarDetalleTicket(nroTicket);

        respuesta.enqueue(new Callback<ArrayList<TicketDetalle>>() {
            @Override
            public void onResponse(Call<ArrayList<TicketDetalle>> call, Response<ArrayList<TicketDetalle>> response) {
                listaDetalleTicket = response.body();

                if (listaDetalleTicket != null) {
                    detalleAdapter = new TicketDetalleAdapter(listaDetalleTicket);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    rvRepuestosUsados.setLayoutManager(layoutManager);
                    rvRepuestosUsados.setAdapter(detalleAdapter);
                } else {
                    tvMensajeRepuesto.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TicketDetalle>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        rvAgregarRepuesto.addOnItemTouchListener(new RecyclerTouchListener(
                getActivity(),
                rvAgregarRepuesto,
                new ClickListener() {
                    @Override
                    public void onClick(View view, final int position) {
                        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                        View mView = layoutInflater.inflate(R.layout.dialog_cant_repuesto, null);
                        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
                        alertBuilder.setView(mView);

                        final EditText etCantidadRepuesto =(EditText)mView.findViewById(R.id.etCantidadRepuesto);
                        alertBuilder
                                .setCancelable(false)
                                .setPositiveButton("Agregar Repuesto", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //Toast.makeText(getActivity(), etCantidadRepuesto.getText().toString(),Toast.LENGTH_SHORT).show();

                                        Repuesto repuesto = listaRepuestoBusqueda.get(position);
                                        TicketDetalle ticketDetalle = new TicketDetalle();
                                        ticketDetalle.set_nroTicket(nroTicket);
                                        ticketDetalle.set_idRepuesto(repuesto.get_idRepuesto());
                                        ticketDetalle.set_precio(repuesto.get_precioActual());
                                        ticketDetalle.set_idMoneda(repuesto.get_idMoneda());
                                        ticketDetalle.set_cantidad(Double.parseDouble(etCantidadRepuesto.getText().toString()));

//                                        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
//                                        String sJson = gson.toJson(ticketDetalle);
//                                        etBuscarRepuesto.setText(sJson);
//                                        Toast.makeText(getActivity(), sJson, Toast.LENGTH_SHORT).show();

                                        TicketsApiWs ticketsApiWs = HelperWs.getConfiguration(getActivity()).create(TicketsApiWs.class);
                                        Call<ResponseBody> respuesta = ticketsApiWs.AgregarRepuesto(ticketDetalle);

                                        respuesta.enqueue(new Callback<ResponseBody>() {
                                            @Override
                                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                                if (response.code() == 200) {
                                                    Toast.makeText(getActivity(), "Se agregó el repuesto al ticket",Toast.LENGTH_SHORT).show();
                                                    listarRepuestos();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                                            }
                                        });

                                    }
                                })
                                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });

                        AlertDialog alerta = alertBuilder.create();
                        alerta.show();
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }
        ));
    }

    @OnClick(R.id.btnBuscarRepuesto)
    public void onClick() {

        String sNombreRepuesto = etBuscarRepuesto.getText().toString().trim();
        TicketsApiWs ticketsApiWs = HelperWs.getConfiguration(getActivity()).create(TicketsApiWs.class);
        Call<ArrayList<Repuesto>> respuesta = ticketsApiWs.BuscarRepuesto(sNombreRepuesto);

        respuesta.enqueue(new Callback<ArrayList<Repuesto>>() {
            @Override
            public void onResponse(Call<ArrayList<Repuesto>> call, Response<ArrayList<Repuesto>> response) {
                listaRepuestoBusqueda = response.body();

                if (listaRepuestoBusqueda != null) {
                    listaRepuestoAdapter = new ListaRepuestoAdapter(listaRepuestoBusqueda);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    rvAgregarRepuesto.setLayoutManager(layoutManager);
                    rvAgregarRepuesto.setAdapter(listaRepuestoAdapter);
                } else {
                    Toast.makeText(getActivity(), "No se encontraron repuestos con los datos ingresados", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Repuesto>> call, Throwable t) {

            }
        });

    }
}
