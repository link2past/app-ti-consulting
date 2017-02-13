package com.oalvarez.appticonsulting1.fragments;


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
import android.widget.Toast;

import com.oalvarez.appticonsulting1.R;
import com.oalvarez.appticonsulting1.adapter.ListaUsuarioAdapter;
import com.oalvarez.appticonsulting1.entidades.Ticket;
import com.oalvarez.appticonsulting1.entidades.Usuario;
import com.oalvarez.appticonsulting1.events.ClickListener;
import com.oalvarez.appticonsulting1.events.RecyclerTouchListener;
import com.oalvarez.appticonsulting1.servicios.HelperWs;
import com.oalvarez.appticonsulting1.servicios.TicketsApiWs;

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
public class AsignarTecnicoFragment extends Fragment {


    @BindView(R.id.etBuscarUsuario)
    EditText etBuscarUsuario;
    @BindView(R.id.ibtnBuscarTecnico)
    ImageButton ibtnBuscarTecnico;
    @BindView(R.id.rvAsignarTecnico)
    RecyclerView rvAsignarTecnico;

    private ArrayList<Usuario> listaUsuarioBusqueda;
    private ListaUsuarioAdapter adapter;
    private int nNroTicket;


    public AsignarTecnicoFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_asignar_tecnico, container, false);
        ButterKnife.bind(this, view);

        final Bundle bundle = this.getArguments();
        if (bundle != null){
            nNroTicket = Integer.parseInt(bundle.getString("nroticket"));
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        rvAsignarTecnico.addOnItemTouchListener(new RecyclerTouchListener(
                getActivity(),
                rvAsignarTecnico,
                new ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        final Usuario usuario = listaUsuarioBusqueda.get(position);
                        //Toast.makeText(getActivity(), usuario.get_nombre(), Toast.LENGTH_SHORT).show();

                        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
                        alertBuilder.setTitle(getString(R.string.tituloAlertaConfirmacion));
                        alertBuilder.setMessage("¿Está seguro de asignar al usuario " + usuario.get_nombre() + " al ticket?");

                        alertBuilder
                                .setCancelable(true)
                                .setPositiveButton("Asignar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Ticket ticket = new Ticket();
                                        ticket.set_nroTicket(nNroTicket);
                                        ticket.set_idUsuarioAsignado(usuario.get_usuario());
                                        ticket.set_idUsuario("JCACERES");
                                        ticket.set_idEstadoTicket(2);

                                        TicketsApiWs ticketsApiWs = HelperWs.getConfiguration(getActivity()).create(TicketsApiWs.class);
                                        Call<ResponseBody> respuesta = ticketsApiWs.AtenderTicket(ticket);

                                        respuesta.enqueue(new Callback<ResponseBody>() {
                                            @Override
                                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                                if (response.code() == 200){
                                                    Toast.makeText(getActivity(), "Se asignó el técnico al ticket",Toast.LENGTH_SHORT).show();
                                                    getFragmentManager().popBackStack();
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
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });

                        AlertDialog alert = alertBuilder.create();
                        alert.show();


                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }
        ));
    }

    @OnClick(R.id.ibtnBuscarTecnico)
    public void onClick() {

        String sNombreUsuario = etBuscarUsuario.getText().toString();
        TicketsApiWs ticketsApiWs = HelperWs.getConfiguration(getActivity()).create(TicketsApiWs.class);
        Call<ArrayList<Usuario>> respuesta = ticketsApiWs.ListarUsuario(3, sNombreUsuario);

        respuesta.enqueue(new Callback<ArrayList<Usuario>>() {
            @Override
            public void onResponse(Call<ArrayList<Usuario>> call, Response<ArrayList<Usuario>> response) {
                listaUsuarioBusqueda = response.body();

                if (listaUsuarioBusqueda != null){
                    adapter = new ListaUsuarioAdapter(listaUsuarioBusqueda);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    rvAsignarTecnico.setLayoutManager(layoutManager);
                    rvAsignarTecnico.setAdapter(adapter);
                }
                else{
                    Toast.makeText(getActivity(), "No se encontraron técnicos con los datos ingresados", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Usuario>> call, Throwable t) {

            }
        });

    }
}
