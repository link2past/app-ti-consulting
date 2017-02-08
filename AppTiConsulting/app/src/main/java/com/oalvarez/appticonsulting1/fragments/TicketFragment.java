package com.oalvarez.appticonsulting1.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.oalvarez.appticonsulting1.R;
import com.oalvarez.appticonsulting1.adapter.TicketAdapter;
import com.oalvarez.appticonsulting1.entidades.Ticket;
import com.oalvarez.appticonsulting1.events.ClickListener;
import com.oalvarez.appticonsulting1.events.RecyclerTouchListener;
import com.oalvarez.appticonsulting1.servicios.HelperWs;
import com.oalvarez.appticonsulting1.servicios.TicketsApiWs;
import com.oalvarez.appticonsulting1.util.Listas;

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
public class TicketFragment extends Fragment {


    @BindView(R.id.rvTicketsAsignados)
    RecyclerView rvTicketsAsignados;
    @BindView(R.id.spEstadoTicket)
    Spinner spEstadoTicket;
    @BindView(R.id.btnRegistrarTicket)
    Button btnRegistrarTicket;
    @BindView(R.id.pbListaTicket)
    ProgressBar pbListaTicket;

    private ArrayList<Ticket> listaTickets = new ArrayList<>();
    public TicketAdapter adapter;
    private ArrayList<String> alEstadoTicket = new ArrayList<>();

    private String sIdUsuario;
    private int nIdTipoUsuario;

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
        if (bundle != null) {

            sIdUsuario = bundle.getString("idusuario");
            nIdTipoUsuario = bundle.getInt("idtipousuario");

            alEstadoTicket = new Listas().listarEstadoTicketDb(nIdTipoUsuario);

            ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, alEstadoTicket);
            spEstadoTicket.setAdapter(arrayAdapter);
            arrayAdapter.notifyDataSetChanged();

            TicketsApiWs ticketsApiWs = HelperWs.getConfiguration(getActivity()).create(TicketsApiWs.class);
            String sUsuario = sIdUsuario;
            Call<ArrayList<Ticket>> respuesta = ticketsApiWs.ConsultarTicketsAsignados("asignado", sUsuario);

            pbListaTicket.setIndeterminate(true);
            pbListaTicket.setVisibility(View.VISIBLE);

            respuesta.enqueue(new Callback<ArrayList<Ticket>>() {
                @Override
                public void onResponse(Call<ArrayList<Ticket>> call, Response<ArrayList<Ticket>> response) {
                    listaTickets = response.body();

                    if (listaTickets != null) {
                        adapter = new TicketAdapter(listaTickets);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        rvTicketsAsignados.setLayoutManager(layoutManager);
                        rvTicketsAsignados.setAdapter(adapter);
                        pbListaTicket.setVisibility(View.INVISIBLE);
                    } else {
                        pbListaTicket.setVisibility(View.INVISIBLE);
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

    @OnClick(R.id.btnRegistrarTicket)
    public void registrarTicket() {
        Fragment fragment = null;

        fragment = new TicketRegistroFragment();
        Bundle bundleFragment = new Bundle();
        bundleFragment.putString("idusuario", sIdUsuario);
        fragment.setArguments(bundleFragment);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment).addToBackStack("fragment");
        ft.commit();
    }

    @Override
    public void onResume() {
        super.onResume();


        spEstadoTicket.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String sItem = alEstadoTicket.get(i);
                //Toast.makeText(getActivity(), sItem, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        rvTicketsAsignados.addOnItemTouchListener(new RecyclerTouchListener(
                getActivity(),
                rvTicketsAsignados,
                new ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Ticket ticket = new Ticket();
                        ticket = listaTickets.get(position);


                        if (ticket != null && ticket.get_idEstadoTicket() == 2) {
                            Ticket ticket1 = new Ticket();
                            ticket1.set_nroTicket(ticket.get_nroTicket());
                            ticket1.set_usuarioAsignado(ticket.get_usuarioAsignado());
                            ticket1.set_idEstadoTicket(3);

                            TicketsApiWs ticketsApiWs2 = HelperWs.getConfiguration(getActivity()).create(TicketsApiWs.class);
                            Call<ResponseBody> respuesta2 = ticketsApiWs2.AtenderTicket(ticket1);
                            respuesta2.enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if (response.code() == 200) {
                                        Toast.makeText(getActivity(), "Ticket marcado como RECIBIDO", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {

                                }
                            });
                        }


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


                        //Toast.makeText(getActivity(), "Ticket: " + ticket.get_titulo(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }
        ));

    }
}
