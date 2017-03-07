package com.oalvarez.appticonsulting1.fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oalvarez.appticonsulting1.R;
import com.oalvarez.appticonsulting1.database.TransporteDb;
import com.oalvarez.appticonsulting1.entidades.LiquidacionDetalle;
import com.oalvarez.appticonsulting1.entidades.RespuestaCodigo;
import com.oalvarez.appticonsulting1.servicios.HelperWs;
import com.oalvarez.appticonsulting1.servicios.TicketsApiWs;
import com.oalvarez.appticonsulting1.util.Listas;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
public class LiquidacionDetRegFragment extends Fragment {


    @BindView(R.id.etFechaDetLiq)
    EditText etFechaDetLiq;
    @BindView(R.id.tilFechaDetLiq)
    TextInputLayout tilFechaDetLiq;
    @BindView(R.id.ibtnFechaTicket)
    ImageButton ibtnFechaTicket;
    @BindView(R.id.etMotivoDetalleLiq)
    EditText etMotivoDetalleLiq;
    @BindView(R.id.tilMotivoDetalleLiq)
    TextInputLayout tilMotivoDetalleLiq;
    @BindView(R.id.spTransporte)
    Spinner spTransporte;
    @BindView(R.id.etLugarInicioDetLiq)
    EditText etLugarInicioDetLiq;
    @BindView(R.id.tilLugarInicioDetLiq)
    TextInputLayout tilLugarInicioDetLiq;
    @BindView(R.id.etLugarFinDetLiq)
    EditText etLugarFinDetLiq;
    @BindView(R.id.tilLugarFinDetLiq)
    TextInputLayout tilLugarFinDetLiq;
    @BindView(R.id.etImporteDetLiq)
    EditText etImporteDetLiq;
    @BindView(R.id.tilImporteDetLiq)
    TextInputLayout tilImporteDetLiq;
    @BindView(R.id.nsvScroll)
    NestedScrollView nsvScroll;
    @BindView(R.id.btnAgregarDetalleLiq)
    Button btnAgregarDetalleLiq;

    private ArrayList<String> alListaTransporte = new ArrayList<>();
    private int nNroLiquidacion;
    private Double nSaldoLiquidacion;
    private int nAnio, nMes, nDia;
    private String sIdTransporteSeleccionado = "ND";


    public LiquidacionDetRegFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_liquidacion_det_reg, container, false);
        ButterKnife.bind(this, view);

        final Bundle bundle = this.getArguments();
        if (bundle != null) {
            nNroLiquidacion = Integer.parseInt(bundle.getString("nroliquidacion"));

            final Calendar fechaActual = Calendar.getInstance();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String sFecha = simpleDateFormat.format(fechaActual.getTime());

            etFechaDetLiq.setText(sFecha);

            nAnio = fechaActual.get(Calendar.YEAR);
            nMes = fechaActual.get(Calendar.MONTH);
            nDia = fechaActual.get(Calendar.DAY_OF_MONTH);

            alListaTransporte = new Listas().listarTransporteDb();
            ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, alListaTransporte);
            spTransporte.setAdapter(arrayAdapter);
            arrayAdapter.notifyDataSetChanged();
        }


        return view;
    }

    @OnClick(R.id.btnAgregarDetalleLiq)
    public void agregarDetalle(){

        if (etMotivoDetalleLiq.getText().toString().trim().equals("")){
            Snackbar snackbar = Snackbar.make(nsvScroll, "Debe indicar el motivo del detalle", Snackbar.LENGTH_LONG);
            snackbar.show();
            etMotivoDetalleLiq.requestFocus();
            return;
        }

        if (etLugarInicioDetLiq.getText().toString().trim().equals("")){
            Snackbar snackbar = Snackbar.make(nsvScroll, "Debe indicar el lugar de inicio del detalle", Snackbar.LENGTH_LONG);
            snackbar.show();
            etLugarInicioDetLiq.requestFocus();
            return;
        }

        if (etLugarFinDetLiq.getText().toString().trim().equals("")){
            Snackbar snackbar = Snackbar.make(nsvScroll, "Debe indicar el lugar de fin del detalle", Snackbar.LENGTH_LONG);
            snackbar.show();
            etLugarFinDetLiq.requestFocus();
            return;
        }

        if (etImporteDetLiq.getText().toString().trim().equals("")){
            Snackbar snackbar = Snackbar.make(nsvScroll, "Debe indicar el importe del detalle", Snackbar.LENGTH_LONG);
            snackbar.show();
            etImporteDetLiq.requestFocus();
            return;
        }

        LiquidacionDetalle liquidacionDetalle = new LiquidacionDetalle();
        liquidacionDetalle.set_nroLiquidacion(nNroLiquidacion);

        Calendar calFechaTicket = Calendar.getInstance();
        calFechaTicket.set(nAnio, nMes, nDia, 0, 0, 0);
        Date date = calFechaTicket.getTime();
        liquidacionDetalle.set_fechaDetalle(date);

        liquidacionDetalle.set_motivo(etMotivoDetalleLiq.getText().toString());

        liquidacionDetalle.set_tipoTransporte(sIdTransporteSeleccionado);
        liquidacionDetalle.set_lugarInicio(etLugarInicioDetLiq.getText().toString());
        liquidacionDetalle.set_lugarFin(etLugarFinDetLiq.getText().toString());
        liquidacionDetalle.set_importe(Double.parseDouble(etImporteDetLiq.getText().toString()));

//        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
//        String sJson = gson.toJson(liquidacionDetalle);
//        etLugarFinDetLiq.setText(sJson);

        TicketsApiWs ticketsApiWs =  HelperWs.getConfiguration(getActivity()).create(TicketsApiWs.class);
        Call<ResponseBody> respuesta = ticketsApiWs.AgregarDetalleLiquidacion("R",liquidacionDetalle);

        respuesta.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code()==200){
                    Toast.makeText(getActivity(), "Se agregó el detalle de la liquidación",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


    }

    @OnClick(R.id.ibtnFechaTicket)
    public void onClick() {
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
            nMes = month;
            nDia = dayOfMonth;

            StringBuilder sb = new StringBuilder();

            if (nDia < 10)
                sb.append("0");

            sb.append(nDia);
            sb.append("/");

            if (nMes < 9)
                sb.append("0");

            sb.append(nMes + 1);
            sb.append("/");
            sb.append(nAnio);


            etFechaDetLiq.setText(sb.toString());
        }
    };

    @Override
    public void onResume() {
        super.onResume();

        spTransporte.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String sTransporte = alListaTransporte.get(position);
                Toast.makeText(getActivity(), sTransporte + "-" + String.valueOf(position), Toast.LENGTH_SHORT).show();
                //nIdTransporteSeleccionado = position;
                switch (position) {
                    case 0:
                        sIdTransporteSeleccionado = "TA";
                        break;
                    case 1:
                        sIdTransporteSeleccionado = "TP";
                        break;
                    case 2:
                        sIdTransporteSeleccionado = "BI";
                        break;
                    case 3:
                        sIdTransporteSeleccionado = "AV";
                        break;
                    case 4:
                        sIdTransporteSeleccionado = "ND";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
