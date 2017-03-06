package com.oalvarez.appticonsulting1.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.oalvarez.appticonsulting1.R;
import com.oalvarez.appticonsulting1.adapter.LiquidacionAdapter;
import com.oalvarez.appticonsulting1.entidades.Liquidacion;
import com.oalvarez.appticonsulting1.servicios.HelperWs;
import com.oalvarez.appticonsulting1.servicios.TicketsApiWs;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiquidacionFragment extends Fragment {


    @BindView(R.id.rvLiquidaciones)
    RecyclerView rvLiquidaciones;
    @BindView(R.id.pbListaLiq)
    ProgressBar pbListaLiq;

    private ArrayList<Liquidacion> listaLiquidacion;
    public LiquidacionAdapter adapter;
    private String sIdUsuario;

    public LiquidacionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_liquidacion, container, false);
        ButterKnife.bind(this, view);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            sIdUsuario = bundle.getString("idusuario");

            TicketsApiWs ticketsApiWs = HelperWs.getConfiguration(getActivity()).create(TicketsApiWs.class);
            Call<ArrayList<Liquidacion>> respuesta = ticketsApiWs.ListarLiquidacion(sIdUsuario);

            pbListaLiq.setIndeterminate(true);
            pbListaLiq.setVisibility(View.VISIBLE);

            respuesta.enqueue(new Callback<ArrayList<Liquidacion>>() {
                @Override
                public void onResponse(Call<ArrayList<Liquidacion>> call, Response<ArrayList<Liquidacion>> response) {
                    listaLiquidacion = response.body();

                    if (listaLiquidacion != null) {
                        adapter = new LiquidacionAdapter(listaLiquidacion);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        rvLiquidaciones.setLayoutManager(layoutManager);
                        rvLiquidaciones.setAdapter(adapter);
                        pbListaLiq.setVisibility(View.INVISIBLE);
                    } else {
                        pbListaLiq.setVisibility(View.INVISIBLE);
                        Toast.makeText(getActivity(), "El usuario no tiene liquidaciones de gastos registradas", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Liquidacion>> call, Throwable t) {

                }
            });
        }

        return view;
    }

}
