package com.oalvarez.appticonsulting.fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.oalvarez.appticonsulting.R;
import com.oalvarez.appticonsulting.entidades.CategoriaProblema;
import com.oalvarez.appticonsulting.entidades.Cliente;
import com.oalvarez.appticonsulting.entidades.RespuestaCodigo;
import com.oalvarez.appticonsulting.entidades.SedeCliente;
import com.oalvarez.appticonsulting.entidades.SessionManager;
import com.oalvarez.appticonsulting.entidades.Ticket;
import com.oalvarez.appticonsulting.entidades.UnidadNegocio;
import com.oalvarez.appticonsulting.entidades.UsuarioSede;
import com.oalvarez.appticonsulting.servicios.HelperWs;
import com.oalvarez.appticonsulting.servicios.TicketsApiWs;
import com.oalvarez.appticonsulting.util.Listas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.ibtnFechaTicket)
    ImageButton ibtnFechaTicket;
    @BindView(R.id.btnGrabarTicket)
    Button btnGrabarTicket;
    @BindView(R.id.nsvScroll)
    NestedScrollView nsvScroll;

    private ArrayList<Cliente> alCliente = new ArrayList<>();
    private String sIdClienteSeleccionado;

    private ArrayList<UnidadNegocio> alUnidadNegocio = new ArrayList<>();
    private int nIdUnidadNegocioSeleccionada;

    private ArrayList<SedeCliente> alSedeCliente = new ArrayList<>();
    private int nIdSedeSeleccionada;

    private ArrayList<UsuarioSede> alUsuarioSede = new ArrayList<>();
    private int nIdUsuarioSedeSeleccionado;

    private ArrayList<CategoriaProblema> alCategoriaProblema = new ArrayList<>();
    private int nIdCategoriaProblemaSeleccionada;

    private ArrayList<String> alNivelUrgencia = new ArrayList<>();
    private int nIdNivelUrgencia;

    private int nAnio, nMes, nDia;
    private String sIdUsuario;

    public TicketRegistroFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticket_registro, container, false);
        ButterKnife.bind(this, view);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            sIdUsuario = bundle.getString("idusuario");
        }

        final Calendar fechaActual = Calendar.getInstance();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String sFecha = simpleDateFormat.format(fechaActual.getTime());

        etFechaTicket.setText(sFecha);

        nAnio = fechaActual.get(Calendar.YEAR);
        nMes = fechaActual.get(Calendar.MONTH);
        nDia = fechaActual.get(Calendar.DAY_OF_MONTH);

        alNivelUrgencia = new Listas().listarNivelUrgenciaDb();
        ArrayAdapter ap = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, alNivelUrgencia);
        spNivelUrgencia.setAdapter(ap);
        ap.notifyDataSetChanged();

        TicketsApiWs ticketsApiWs = HelperWs.getConfiguration(getActivity()).create(TicketsApiWs.class);
        Call<ArrayList<Cliente>> respuesta = ticketsApiWs.ListarClientes();

        respuesta.enqueue(new Callback<ArrayList<Cliente>>() {
            @Override
            public void onResponse(Call<ArrayList<Cliente>> call, Response<ArrayList<Cliente>> response) {
                if (response.code() == 200) {

                    alCliente = response.body();
                    ListarClientes(alCliente);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Cliente>> call, Throwable t) {

            }
        });

        Call<ArrayList<CategoriaProblema>> respuesta2 = ticketsApiWs.ListarCategoriaProblema();
        respuesta2.enqueue(new Callback<ArrayList<CategoriaProblema>>() {
            @Override
            public void onResponse(Call<ArrayList<CategoriaProblema>> call, Response<ArrayList<CategoriaProblema>> response) {
                if (response.code() == 200) {

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

    @OnClick(R.id.btnGrabarTicket)
    public void grabarTicket() {

        Ticket ticket = new Ticket();
        ticket.set_idCliente(Integer.parseInt(sIdClienteSeleccionado));
        ticket.set_idSede(nIdSedeSeleccionada);
        try {
            ticket.set_fechaTicket(new SimpleDateFormat("dd/mm/yyyy").parse(etFechaTicket.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ticket.set_idCategoriaProblema(nIdCategoriaProblemaSeleccionada);
        ticket.set_idNivelUrgencia(nIdNivelUrgencia);
        ticket.set_titulo(etTituloTicket.getText().toString());
        ticket.set_detalle(etDetalleTicket.getText().toString());
        ticket.set_idUsuarioSede(nIdUsuarioSedeSeleccionado);
        ticket.set_nroTicketCliente(etNroTicketCliente.getText().toString());
        ticket.set_idUsuario(sIdUsuario);

        TicketsApiWs ticketsApiWs = HelperWs.getConfiguration(getActivity()).create(TicketsApiWs.class);
        Call<RespuestaCodigo> respuesta = ticketsApiWs.RegistrarTicket("registro",ticket);

        respuesta.enqueue(new Callback<RespuestaCodigo>() {
            @Override
            public void onResponse(Call<RespuestaCodigo> call, Response<RespuestaCodigo> response) {
                if (response.code() == 200){

                    RespuestaCodigo respuestaCodigo = response.body();
                    Toast.makeText(getActivity(), "Se registró el ticket número: " + respuestaCodigo.getCodigo(), Toast.LENGTH_SHORT).show();
                    getFragmentManager().popBackStack();
                }
            }

            @Override
            public void onFailure(Call<RespuestaCodigo> call, Throwable t) {

            }
        });

    }

    @OnClick(R.id.ibtnFechaTicket)
    public void seleccionarFecha() {
        //final Calendar calendar = Calendar.getInstance();
        SelectDateFragment date = new SelectDateFragment();

        Bundle args = new Bundle();
        args.putInt("year", nAnio);
        args.putInt("month", nMes);
        args.putInt("day", nDia);

        date.setArguments(args);
        date.setCallBack(ondate);
        date.show(getActivity().getFragmentManager(), "Elegir Fecha");
    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            nAnio = year;
            nMes = month + 1;
            nDia = dayOfMonth;

            StringBuilder sb = new StringBuilder();

            if (nDia < 10)
                sb.append("0");

            sb.append(nDia);
            sb.append("/");

            if (nMes < 10)
                sb.append("0");

            sb.append(nMes);
            sb.append("/");
            sb.append(nAnio);


            etFechaTicket.setText(sb.toString());
        }
    };

    private void ListarClientes(ArrayList<Cliente> lista) {

        ArrayList<String> clientes = new ArrayList<>();
        clientes.add("-SELECCIONE EL CLIENTE-");
        for (Cliente item : lista) {
            clientes.add(item.get_razonSocial());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, clientes);
        spCliente.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();

    }

    private void ListarCategoriaProblema(ArrayList<CategoriaProblema> lista) {

        ArrayList<String> cp = new ArrayList<>();
        cp.add("-SELECCIONE CATEGORÍA-");
        for (CategoriaProblema item : lista) {
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

                if (nPosicionReal >= 0) {
                    Cliente cliente;
                    cliente = alCliente.get(nPosicionReal);
                    sIdClienteSeleccionado = cliente.get_idCliente();
                    Toast.makeText(getActivity(), cliente.get_nroDi(), Toast.LENGTH_SHORT).show();

                    TicketsApiWs ticketsApiWs = HelperWs.getConfiguration(getActivity()).create(TicketsApiWs.class);
                    Call<ArrayList<UnidadNegocio>> respuesta = ticketsApiWs.ListarUnidadNegocioCliente(cliente.get_idCliente());

                    respuesta.enqueue(new Callback<ArrayList<UnidadNegocio>>() {
                        @Override
                        public void onResponse(Call<ArrayList<UnidadNegocio>> call, Response<ArrayList<UnidadNegocio>> response) {
                            if (response.code() == 200) {

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

                if (nPosicionReal >= 0) {
                    UnidadNegocio un = alUnidadNegocio.get(nPosicionReal);
                    nIdUnidadNegocioSeleccionada = un.get_idUnidadNegocio();

                    TicketsApiWs ticketsApiWs = HelperWs.getConfiguration(getActivity()).create(TicketsApiWs.class);
                    Call<ArrayList<SedeCliente>> respuesta = ticketsApiWs.ListarSedePorUn(sIdClienteSeleccionado, nIdUnidadNegocioSeleccionada);

                    respuesta.enqueue(new Callback<ArrayList<SedeCliente>>() {
                        @Override
                        public void onResponse(Call<ArrayList<SedeCliente>> call, Response<ArrayList<SedeCliente>> response) {
                            if (response.code() == 200) {

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

                if (nPosicionReal >= 0) {
                    SedeCliente sede = alSedeCliente.get(nPosicionReal);
                    nIdSedeSeleccionada = sede.get_idSedeCliente();

                    TicketsApiWs ticketsApiWs = HelperWs.getConfiguration(getActivity()).create(TicketsApiWs.class);
                    Call<ArrayList<UsuarioSede>> respuesta = ticketsApiWs.ListarUsuarioSede(nIdSedeSeleccionada);

                    respuesta.enqueue(new Callback<ArrayList<UsuarioSede>>() {
                        @Override
                        public void onResponse(Call<ArrayList<UsuarioSede>> call, Response<ArrayList<UsuarioSede>> response) {
                            if (response.code() == 200) {

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

        spUsuarioAtencion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int nPosicionReal = position - 1;
                if (nPosicionReal >= 0){
                    UsuarioSede usuarioSede = alUsuarioSede.get(nPosicionReal);
                    nIdUsuarioSedeSeleccionado = usuarioSede.get_idUsuarioSede();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int nPosicionReal = position - 1;
                if (nPosicionReal >= 0){
                    CategoriaProblema categoriaProblema = alCategoriaProblema.get(nPosicionReal);
                    nIdCategoriaProblemaSeleccionada = categoriaProblema.get_idCategoriaProblema();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spNivelUrgencia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nIdNivelUrgencia = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void ListarUnidadNegocio(ArrayList<UnidadNegocio> lista) {

        ArrayList<String> un = new ArrayList<>();
        un.add("-SELECCIONE UNIDAD NEGOCIO-");
        for (UnidadNegocio item : lista) {
            un.add(item.get_descripcion());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, un);
        spUnidadNegocio.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();

    }

    private void ListarSedePorUn(ArrayList<SedeCliente> lista) {
        ArrayList<String> sede = new ArrayList<>();
        sede.add("-SELECCIONE SEDE-");
        for (SedeCliente item : lista) {
            sede.add(item.get_nombre());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, sede);
        spSedeCliente.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
    }

    private void ListarUsuarioSede(ArrayList<UsuarioSede> lista) {
        ArrayList<String> us = new ArrayList<>();
        us.add("-SELECCIONE USUARIO-");
        for (UsuarioSede item : lista) {
            us.add(item.get_nombre());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, us);
        spUsuarioAtencion.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
    }


}
