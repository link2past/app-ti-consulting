package com.oalvarez.appticonsulting1.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.oalvarez.appticonsulting1.R;
import com.oalvarez.appticonsulting1.entidades.Ticket;
import com.oalvarez.appticonsulting1.servicios.HelperWs;
import com.oalvarez.appticonsulting1.servicios.TicketsApiWs;

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
public class TicketDetalleFragment extends Fragment {


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
    Button btnAtender;
    @BindView(R.id.btnRepuesto)
    Button btnRepuesto;
    @BindView(R.id.btnEsperaRepuesto)
    Button btnEsperaRepuesto;
    @BindView(R.id.layoutTicketDetalle)
    CoordinatorLayout layoutTicketDetalle;
    @BindView(R.id.etObservacionTicket)
    EditText etObservacionTicket;
    @BindView(R.id.tilObservacionTicket)
    TextInputLayout tilObservacionTicket;
    @BindView(R.id.etOrdenServicioTicket)
    EditText etOrdenServicioTicket;
    @BindView(R.id.tilOrdenServicioTicket)
    TextInputLayout tilOrdenServicioTicket;
    @BindView(R.id.idbtnVerDireccion)
    ImageButton idbtnVerDireccion;
    @BindView(R.id.btnAsignar)
    Button btnAsignar;
    @BindView(R.id.btnAnular)
    Button btnAnular;
    @BindView(R.id.btnCerrar)
    Button btnCerrar;
    @BindView(R.id.btnAgregarFoto)
    Button btnAgregarFoto;
    @BindView(R.id.btnGrabarDatos)
    Button btnGrabarDatos;

    private Ticket oTicket;
    int nIdTipoUsuario;
    private String sIdUsuario;

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

        if (bundle != null) {
            sIdUsuario = bundle.getString("idusuario");
            int nroTicket = bundle.getInt("nroticket");
            nIdTipoUsuario = bundle.getInt("idtipousuario");
            etNroTicket.setText(String.valueOf(nroTicket));


            TicketsApiWs ticketsApiWs = HelperWs.getConfiguration(getActivity()).create(TicketsApiWs.class);
            Call<Ticket> respuesta = ticketsApiWs.ConsultarTicket(nroTicket);

            respuesta.enqueue(new Callback<Ticket>() {
                @Override
                public void onResponse(Call<Ticket> call, Response<Ticket> response) {
                    oTicket = response.body();

                    if (oTicket != null) {

                        etNroTicket.setText(String.valueOf(oTicket.get_nroTicket()));

                        if (oTicket.get_idEstadoTicket() == 2 && nIdTipoUsuario == 3) {
                            etEstadoTicket.setText(getString(R.string.estadoRecibido));
                        } else {
                            etEstadoTicket.setText(oTicket.get_estadoTicket().get_descripcion());
                        }

                        etClienteTicket.setText(oTicket.get_cliente().get_razonSocial());
                        etSedeTicket.setText(oTicket.get_sede().get_nombre());
                        etTituloTicket.setText(oTicket.get_titulo());
                        etSolucionTicket.setText(oTicket.get_solucion());
                        etOrdenServicioTicket.setText(oTicket.get_ordenServicio());
                        etObservacionTicket.setText(oTicket.get_observaciones());

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String sFecha = simpleDateFormat.format(oTicket.get_fechaTicket());
                        etFechaTicket.setText(sFecha);

                        etDetalleTicket.setText(oTicket.get_detalle());

                        desactivarCampos(oTicket.get_idEstadoTicket());

                    }
                }

                @Override
                public void onFailure(Call<Ticket> call, Throwable t) {
                    Log.d("ERROR: ", t.getMessage());
                }
            });

        }

        //desactivarCampos(oTicket.get_idEstadoTicket());

        return view;
    }

    private void desactivarCampos(int nIdEstadoTicket) {
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

        if (nIdTipoUsuario == 3) {
            btnAsignar.setVisibility(View.GONE);
            btnAnular.setVisibility(View.GONE);
            btnCerrar.setVisibility(View.GONE);
        }

        if (nIdEstadoTicket == 5) {
            btnAtender.setVisibility(View.GONE);
            btnRepuesto.setVisibility(View.GONE);
            btnEsperaRepuesto.setVisibility(View.GONE);
            btnAtender.setVisibility(View.GONE);
            btnAnular.setVisibility(View.GONE);
            btnCerrar.setVisibility(View.GONE);
            btnAgregarFoto.setVisibility(View.GONE);
            btnGrabarDatos.setVisibility(View.GONE);
        }
    }


    @OnClick(R.id.btnAtender)
    public void onClick() {

        if (etSolucionTicket.getText().toString().trim().equals("")) {
            Snackbar snackbar = Snackbar.make(nsvScroll, "Debe indicar la solución del ticket", Snackbar.LENGTH_LONG);
            snackbar.show();
            etSolucionTicket.requestFocus();
            return;
        }

        if (etOrdenServicioTicket.getText().toString().trim().equals("")) {
            Snackbar snackbar = Snackbar.make(nsvScroll, "Debe indicar la orden de servicio del ticket", Snackbar.LENGTH_LONG);
            snackbar.show();
            etOrdenServicioTicket.requestFocus();
            return;
        }

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
        alertBuilder.setTitle(getString(R.string.tituloAlertaConfirmacion));
        alertBuilder.setMessage("¿Está seguro de marcar el ticket como ATENDIDO?");
        alertBuilder
                .setCancelable(true)
                .setPositiveButton("Atendido", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Ticket ticket = new Ticket();
                        ticket.set_nroTicket(oTicket.get_nroTicket());
                        ticket.set_solucion(etSolucionTicket.getText().toString());
                        ticket.set_observaciones(etObservacionTicket.getText().toString());
                        ticket.set_ordenServicio(etOrdenServicioTicket.getText().toString());
                        ticket.set_usuarioAsignado(oTicket.get_usuarioAsignado());
                        ticket.set_idEstadoTicket(4);

                        TicketsApiWs ticketsApiWs = HelperWs.getConfiguration(getActivity()).create(TicketsApiWs.class);
                        Call<ResponseBody> respuesta = ticketsApiWs.AtenderTicket(ticket);

                        respuesta.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.code() == 200) {
                                    Snackbar snackbar = Snackbar.make(nsvScroll, "Ticket Atendido", Snackbar.LENGTH_LONG);
                                    snackbar.show();
                                    etEstadoTicket.setText(getString(R.string.estadoAtendido));
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
                        dialog.cancel();
                    }
                });

        AlertDialog alerta = alertBuilder.create();
        alerta.show();

    }

    @OnClick(R.id.btnEsperaRepuesto)
    public void esperarRepuesto() {

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
        alertBuilder.setTitle(getString(R.string.tituloAlertaConfirmacion));
        alertBuilder.setMessage("¿Está seguro de marcar el ticket como EN ESPERA DE REPUESTO?");
        alertBuilder
                .setCancelable(true)
                .setPositiveButton("En espera de Repuesto", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Ticket ticket = new Ticket();
                        ticket.set_nroTicket(oTicket.get_nroTicket());
                        ticket.set_usuarioAsignado(oTicket.get_usuarioAsignado());
                        ticket.set_idEstadoTicket(6);

                        //Gson gson = new GsonBuilder().disableHtmlEscaping().create();
                        //String sJson = gson.toJson(ticket);
                        //etSolucionTicket.setText(sJson);
                        //Toast.makeText(getActivity(), sJson, Toast.LENGTH_SHORT).show();

                        TicketsApiWs ticketsApiWs = HelperWs.getConfiguration(getActivity()).create(TicketsApiWs.class);
                        Call<ResponseBody> respuesta = ticketsApiWs.AtenderTicket(ticket);

                        respuesta.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.code() == 200) {
                                    Snackbar snackbar = Snackbar.make(nsvScroll, "Ticket En Espera de Repuesto", Snackbar.LENGTH_LONG);
                                    snackbar.show();
                                    etEstadoTicket.setText(getString(R.string.estadoEsperaRep));
                                    //Toast.makeText(getActivity(), "Ticket Atendido", Toast.LENGTH_SHORT).show();
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
                        dialog.cancel();
                    }
                });

        AlertDialog alerta = alertBuilder.create();
        alerta.show();


    }

    @OnClick(R.id.btnRepuesto)
    public void agregarRepuesto() {
        Fragment fragment;
        fragment = new TicketRepuestoFragment();
        Bundle bundleRepuesto = new Bundle();
        bundleRepuesto.putString("nroticket", etNroTicket.getText().toString());
        bundleRepuesto.putString("solucion", etSolucionTicket.getText().toString());
        fragment.setArguments(bundleRepuesto);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment).addToBackStack("fragment");
        ft.commit();
    }

    @OnClick(R.id.btnAsignar)
    public void asignarTicket() {
        Fragment fragment;
        fragment = new AsignarTecnicoFragment();
        Bundle bundleRepuesto = new Bundle();
        bundleRepuesto.putString("nroticket", etNroTicket.getText().toString());
        bundleRepuesto.putString("idusuario", sIdUsuario);
        fragment.setArguments(bundleRepuesto);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment).addToBackStack("asignarfragment");
        ft.commit();
    }

    @OnClick(R.id.btnCerrar)
    public void cerrarTicket() {

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
        alertBuilder.setTitle(getString(R.string.tituloAlertaConfirmacion));
        alertBuilder.setMessage("¿Está seguro de CERRAR el ticket?");
        alertBuilder
                .setCancelable(true)
                .setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Ticket ticket = new Ticket();
                        ticket.set_nroTicket(oTicket.get_nroTicket());
                        ticket.set_usuarioAsignado(sIdUsuario);
                        ticket.set_idEstadoTicket(5);

                        //Gson gson = new GsonBuilder().disableHtmlEscaping().create();
                        //String sJson = gson.toJson(ticket);
                        //etSolucionTicket.setText(sJson);
                        //Toast.makeText(getActivity(), sJson, Toast.LENGTH_SHORT).show();

                        TicketsApiWs ticketsApiWs = HelperWs.getConfiguration(getActivity()).create(TicketsApiWs.class);
                        Call<ResponseBody> respuesta = ticketsApiWs.AtenderTicket(ticket);

                        respuesta.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.code() == 200) {
                                    Snackbar snackbar = Snackbar.make(nsvScroll, "Ticket Cerrado", Snackbar.LENGTH_LONG);
                                    snackbar.show();
                                    etEstadoTicket.setText(getString(R.string.estadoCerrado));
                                    //Toast.makeText(getActivity(), "Ticket Atendido", Toast.LENGTH_SHORT).show();
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
                        dialog.cancel();
                    }
                });

        AlertDialog alerta = alertBuilder.create();
        alerta.show();

    }

    @OnClick(R.id.btnAnular)
    public void anularTicket() {

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
        alertBuilder.setTitle(getString(R.string.tituloAlertaConfirmacion));
        alertBuilder.setMessage("¿Está seguro de ANULAR el ticket?");
        alertBuilder
                .setCancelable(true)
                .setPositiveButton("Anular", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Ticket ticket = new Ticket();
                        ticket.set_nroTicket(oTicket.get_nroTicket());
                        ticket.set_usuarioAsignado(oTicket.get_usuarioAsignado());
                        ticket.set_idEstadoTicket(7);

                        //Gson gson = new GsonBuilder().disableHtmlEscaping().create();
                        //String sJson = gson.toJson(ticket);
                        //etSolucionTicket.setText(sJson);
                        //Toast.makeText(getActivity(), sJson, Toast.LENGTH_SHORT).show();

                        TicketsApiWs ticketsApiWs = HelperWs.getConfiguration(getActivity()).create(TicketsApiWs.class);
                        Call<ResponseBody> respuesta = ticketsApiWs.AtenderTicket(ticket);

                        respuesta.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.code() == 200) {
                                    Snackbar snackbar = Snackbar.make(nsvScroll, "Ticket Anulado", Snackbar.LENGTH_LONG);
                                    snackbar.show();
                                    etEstadoTicket.setText(getString(R.string.estadoAnulado));
                                    //Toast.makeText(getActivity(), "Ticket Atendido", Toast.LENGTH_SHORT).show();
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
                        dialog.cancel();
                    }
                });

        AlertDialog alerta = alertBuilder.create();
        alerta.show();

    }

    @OnClick(R.id.idbtnVerDireccion)
    public void verDireccion() {
        Fragment fragment;
        fragment = new TicketUbicacionFragment();
        Bundle bundleDireccion = new Bundle();
        bundleDireccion.putString("nombreCliente", oTicket.get_cliente().get_razonSocial());
        bundleDireccion.putString("sedeCliente", oTicket.get_sede().get_nombre());
        bundleDireccion.putString("latitud", oTicket.get_sede().get_latitud());
        bundleDireccion.putString("longitud", oTicket.get_sede().get_longitud());
        bundleDireccion.putString("direccionSede", oTicket.get_sede().get_direccion());
        bundleDireccion.putString("contactoSede", oTicket.get_sede().get_nombreContacto());
        bundleDireccion.putString("usuarioAtencion", oTicket.get_usuarioSede().get_nombre());

        fragment.setArguments(bundleDireccion);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment).addToBackStack("fragmentDireccion");
        ft.commit();
    }

    @OnClick(R.id.btnAgregarFoto)
    public void agregarFoto() {
        Fragment fragment;
        fragment = new TicketArchivoFragment();
        Bundle bundleRepuesto = new Bundle();
        bundleRepuesto.putString("nroticket", etNroTicket.getText().toString());
        fragment.setArguments(bundleRepuesto);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment).addToBackStack("fragment");
        ft.commit();
    }

    @OnClick(R.id.btnGrabarDatos)
    public void grabarDatos(){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
        alertBuilder.setTitle(getString(R.string.tituloAlertaConfirmacion));
        alertBuilder.setMessage("¿Está seguro de grabar los datos ingresados?");
        alertBuilder
                .setCancelable(true)
                .setPositiveButton("Grabar Datos", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Ticket ticket = new Ticket();
                        ticket.set_nroTicket(oTicket.get_nroTicket());
                        ticket.set_solucion(etSolucionTicket.getText().toString());
                        ticket.set_observaciones(etObservacionTicket.getText().toString());
                        ticket.set_ordenServicio(etOrdenServicioTicket.getText().toString());
                        ticket.set_usuarioAsignado(oTicket.get_usuarioAsignado());
                        ticket.set_idEstadoTicket(1000);

                        //Gson gson = new GsonBuilder().disableHtmlEscaping().create();
                        //String sJson = gson.toJson(ticket);
                        //etSolucionTicket.setText(sJson);
                        //Toast.makeText(getActivity(), sJson, Toast.LENGTH_SHORT).show();

                        TicketsApiWs ticketsApiWs = HelperWs.getConfiguration(getActivity()).create(TicketsApiWs.class);
                        Call<ResponseBody> respuesta = ticketsApiWs.AtenderTicket(ticket);

                        respuesta.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.code() == 200) {
                                    Snackbar snackbar = Snackbar.make(nsvScroll, "Se grabaron los datos ingresados", Snackbar.LENGTH_LONG);
                                    snackbar.show();
                                    //Toast.makeText(getActivity(), "Ticket Atendido", Toast.LENGTH_SHORT).show();
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
                        dialog.cancel();
                    }
                });

        AlertDialog alerta = alertBuilder.create();
        alerta.show();
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}
