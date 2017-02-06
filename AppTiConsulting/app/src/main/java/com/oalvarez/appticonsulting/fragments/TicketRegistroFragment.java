package com.oalvarez.appticonsulting.fragments;


import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.oalvarez.appticonsulting.R;
import com.oalvarez.appticonsulting.entidades.*;
import com.oalvarez.appticonsulting.servicios.HelperWs;
import com.oalvarez.appticonsulting.servicios.TicketsApiWs;
import com.oalvarez.appticonsulting.util.Listas;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TicketRegistroFragment extends Fragment {


    @BindView(R.id.spCliente)
    Spinner spCliente;
    @BindView(R.id.spUnidadNegocio)
    Spinner spUnidadNegocio;
    @BindView(R.id.spSedeCliente)
    Spinner spSedeCliente;
    @BindView(R.id.spUsuarioAtencion)
    Spinner spUsuarioAtencion;
    @BindView(R.id.spCategoria)
    Spinner spCategoria;
    @BindView(R.id.spNivelUrgencia)
    Spinner spNivelUrgencia;
    @BindView(R.id.etFechaTicket)
    EditText etFechaTicket;
    @BindView(R.id.tilFechaTicket)
    TextInputLayout tilFechaTicket;
    @BindView(R.id.etNroTicketCliente)
    EditText etNroTicketCliente;
    @BindView(R.id.tilNroTicketCliente)
    TextInputLayout tilNroTicketCliente;
    @BindView(R.id.etTituloTicket)
    EditText etTituloTicket;
    @BindView(R.id.tilTituloTicket)
    TextInputLayout tilTituloTicket;
    @BindView(R.id.etDetalleTicket)
    EditText etDetalleTicket;
    @BindView(R.id.tilDetalleTicket)
    TextInputLayout tilDetalleTicket;

    private ArrayList<Cliente> alCliente = new ArrayList<>();
    private String sIdClienteSeleccionado;

    private ArrayList<UnidadNegocio> alUnidadNegocio = new ArrayList<>();
    private int nIdUnidadNegocioSeleccionada;

    private ArrayList<SedeCliente> alSedeCliente = new ArrayList<>();
    private int nIdSedeSeleccionada;

    private ArrayList<UsuarioSede> alUsuarioSede = new ArrayList<>();
    private int nIdUsuarioSede;

    private ArrayList<CategoriaProblema> alCategoriaProblema = new ArrayList<>();
    private int nCategoriaProblemaSeleccionada;

    private ArrayList<String> alNivelUrgencia = new ArrayList<>();

    public TicketRegistroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticket_registro, container, false);
        ButterKnife.bind(this, view);

        alNivelUrgencia = new Listas().listarNivelUrgenciaDb();
        ArrayAdapter ap = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, alNivelUrgencia);
        spNivelUrgencia.setAdapter(ap);
        ap.notifyDataSetChanged();

        TicketsApiWs ticketsApiWs = HelperWs.getConfiguration(getActivity()).create(TicketsApiWs.class);
        Call<ArrayList<Cliente>> respuesta = ticketsApiWs.ListarClientes();

        respuesta.enqueue(new Callback<ArrayList<Cliente>>() {
            @Override
            public void onResponse(Call<ArrayList<Cliente>> call, Response<ArrayList<Cliente>> response) {
                if (response.code()==200){

                    alCliente = response.body();
                    ListarClientes(alCliente);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Cliente>> call, Throwable t) {

            }
        });

        Call<ArrayList<CategoriaProblema>> respuesta2  = ticketsApiWs.ListarCategoriaProblema();
        respuesta2.enqueue(new Callback<ArrayList<CategoriaProblema>>() {
            @Override
            public void onResponse(Call<ArrayList<CategoriaProblema>> call, Response<ArrayList<CategoriaProblema>> response) {
                if (response.code()==200){

                    alCategoriaProblema = response.body();
                    ListarCategoriaProblema(alCategoriaProblema);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CategoriaProblema>> call, Throwable t) {

            }
        });

        return view;
    }
    
    private void ListarClientes(ArrayList<Cliente> lista){

        ArrayList<String> clientes = new ArrayList<>();
        clientes.add("-SELECCIONE EL CLIENTE-");
        for (Cliente item: lista) {
            clientes.add(item.get_razonSocial());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, clientes);
        spCliente.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
        
    }

    private void ListarCategoriaProblema(ArrayList<CategoriaProblema> lista){

        ArrayList<String> cp = new ArrayList<>();
        cp.add("-SELECCIONE CATEGORÍA-");
        for (CategoriaProblema item: lista) {
            cp.add(item.get_descripcion());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, cp);
        spCategoria.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();

    }

    @Override
    public void onResume() {
        super.onResume();

        spCliente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int nPosicionReal = position - 1;

                if (nPosicionReal >= 0){
                    Cliente cliente;
                    cliente = alCliente.get(nPosicionReal);
                    sIdClienteSeleccionado = cliente.get_idCliente();
                    Toast.makeText(getActivity(), cliente.get_nroDi(), Toast.LENGTH_SHORT).show();

                    TicketsApiWs ticketsApiWs = HelperWs.getConfiguration(getActivity()).create(TicketsApiWs.class);
                    Call<ArrayList<UnidadNegocio>> respuesta = ticketsApiWs.ListarUnidadNegocioCliente(cliente.get_idCliente());

                    respuesta.enqueue(new Callback<ArrayList<UnidadNegocio>>() {
                        @Override
                        public void onResponse(Call<ArrayList<UnidadNegocio>> call, Response<ArrayList<UnidadNegocio>> response) {
                            if (response.code()==200){

                                alUnidadNegocio = response.body();
                                ListarUnidadNegocio(alUnidadNegocio);
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<UnidadNegocio>> call, Throwable t) {

                        }
                    });

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spUnidadNegocio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int nPosicionReal = position - 1;

                if (nPosicionReal >= 0){
                    UnidadNegocio un = alUnidadNegocio.get(nPosicionReal);
                    nIdUnidadNegocioSeleccionada = un.get_idUnidadNegocio();

                    TicketsApiWs ticketsApiWs = HelperWs.getConfiguration(getActivity()).create(TicketsApiWs.class);
                    Call<ArrayList<SedeCliente>> respuesta = ticketsApiWs.ListarSedePorUn(sIdClienteSeleccionado, nIdUnidadNegocioSeleccionada);

                    respuesta.enqueue(new Callback<ArrayList<SedeCliente>>() {
                        @Override
                        public void onResponse(Call<ArrayList<SedeCliente>> call, Response<ArrayList<SedeCliente>> response) {
                            if (response.code()==200){

                                alSedeCliente = response.body();
                                ListarSedePorUn(alSedeCliente);
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<SedeCliente>> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spSedeCliente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int nPosicionReal = position - 1;

                if (nPosicionReal >= 0){
                    SedeCliente sede = alSedeCliente.get(nPosicionReal);
                    nIdSedeSeleccionada = sede.get_idSedeCliente();

                    TicketsApiWs ticketsApiWs = HelperWs.getConfiguration(getActivity()).create(TicketsApiWs.class);
                    Call<ArrayList<UsuarioSede>> respuesta = ticketsApiWs.ListarUsuarioSede(nIdSedeSeleccionada);

                    respuesta.enqueue(new Callback<ArrayList<UsuarioSede>>() {
                        @Override
                        public void onResponse(Call<ArrayList<UsuarioSede>> call, Response<ArrayList<UsuarioSede>> response) {
                            if (response.code()==200){

                                alUsuarioSede = response.body();
                                ListarUsuarioSede(alUsuarioSede);
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<UsuarioSede>> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void ListarUnidadNegocio(ArrayList<UnidadNegocio> lista){

        ArrayList<String> un = new ArrayList<>();
        un.add("-SELECCIONE UNIDAD NEGOCIO-");
        for (UnidadNegocio item: lista) {
            un.add(item.get_descripcion());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, un);
        spUnidadNegocio.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();

    }

    private void ListarSedePorUn(ArrayList<SedeCliente> lista){
        ArrayList<String> sede = new ArrayList<>();
        sede.add("-SELECCIONE SEDE-");
        for (SedeCliente item: lista) {
            sede.add(item.get_nombre());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, sede);
        spSedeCliente.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
    }

    private void ListarUsuarioSede(ArrayList<UsuarioSede> lista){
        ArrayList<String> us = new ArrayList<>();
        us.add("-SELECCIONE USUARIO-");
        for (UsuarioSede item: lista) {
            us.add(item.get_nombre());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, us);
        spUsuarioAtencion.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
    }
}
