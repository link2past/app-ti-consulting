package com.oalvarez.appticonsulting.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.oalvarez.appticonsulting.R;
import com.oalvarez.appticonsulting.adapter.TicketAdapter;
import com.oalvarez.appticonsulting.entidades.Ticket;
import com.oalvarez.appticonsulting.events.ClickListener;
import com.oalvarez.appticonsulting.events.RecyclerTouchListener;
import com.oalvarez.appticonsulting.servicios.HelperWs;
import com.oalvarez.appticonsulting.servicios.TicketsApiWs;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.fragment;
import static com.oalvarez.appticonsulting.R.id.toolbar;

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

        Bundle bundle = this.getArguments();
        if (bundle != null){

            String sIdUsuario = bundle.getString("idusuario");

            TicketsApiWs ticketsApiWs = HelperWs.getConfiguration().create(TicketsApiWs.class);
            String sUsuario = sIdUsuario;
            Call<ArrayList<Ticket>> respuesta = ticketsApiWs.ConsultarTicketsAsignados("asignado", sUsuario);

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
                    else{
                        Toast.makeText(getActivity(), "El usuario no tiene tickets asignados", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Ticket>> call, Throwable t) {

                }
            });

        }


        return view;
    }

    @OnClick(R.id.fabActualizar)
    public void onClick() {
    }

    @Override
    public void onResume() {
        super.onResume();

        rvTicketsAsignados.addOnItemTouchListener(new RecyclerTouchListener(
                getActivity(),
                rvTicketsAsignados,
                new ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Ticket ticket = new Ticket();
                        ticket = listaTickets.get(position);

                        Fragment fragment = null;

                        fragment = new TicketDetalleFragment();
                        Bundle bundleFragment = new Bundle();
                        bundleFragment.putInt("nroticket", ticket.get_nroTicket());
                        fragment.setArguments(bundleFragment);


                        //((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Ticket");
                        //toolbar.setTitle("Detalle de Tickets");

                        if (fragment != null) {
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.replace(R.id.content_frame, fragment).addToBackStack("fragment");
                            ft.commit();
                        }


                        Toast.makeText(getActivity(), "Ticket: " + ticket.get_titulo(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }
        ));

    }
}
