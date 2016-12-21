package com.oalvarez.appticonsulting.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oalvarez.appticonsulting.R;
import com.oalvarez.appticonsulting.adapter.TicketAdapter;
import com.oalvarez.appticonsulting.entidades.Ticket;
import com.oalvarez.appticonsulting.servicios.HelperWs;
import com.oalvarez.appticonsulting.servicios.TicketsApiWs;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TicketFragment extends Fragment {


    @BindView(R.id.rvTicketsAsignados)
    RecyclerView rvTicketsAsignados;
    @BindView(R.id.fabActualizar)
    FloatingActionButton fabActualizar;

    private ArrayList<Ticket> listaTickets = new ArrayList<>();
    public TicketAdapter adapter;

    public TicketFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticket, container, false);
        ButterKnife.bind(this, view);

        TicketsApiWs ticketsApiWs = HelperWs.getConfiguration().create(TicketsApiWs.class);
        String sUsuario = "AMENDIGURE";
        Call<ArrayList<Ticket>> respuesta = ticketsApiWs.ConsultarTicketsAsignados(sUsuario);

        respuesta.enqueue(new Callback<ArrayList<Ticket>>() {
            @Override
            public void onResponse(Call<ArrayList<Ticket>> call, Response<ArrayList<Ticket>> response) {
                listaTickets = response.body();

                if (listaTickets != null){
                    adapter = new TicketAdapter(listaTickets);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    rvTicketsAsignados.setLayoutManager(layoutManager);
                    rvTicketsAsignados.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Ticket>> call, Throwable t) {

            }
        });

        return view;
    }

    @OnClick(R.id.fabActualizar)
    public void onClick() {
    }
}
