package com.oalvarez.appticonsulting1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oalvarez.appticonsulting1.R;
import com.oalvarez.appticonsulting1.entidades.Liquidacion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oalvarez on 05/03/2017.
 */

public class LiquidacionAdapter extends RecyclerView.Adapter<LiquidacionAdapter.LiquidacionHolder> {



    private ArrayList<Liquidacion> listaLiquidacion;

    public LiquidacionAdapter(ArrayList<Liquidacion> lista) {
        listaLiquidacion = lista;
    }

    @Override
    public LiquidacionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_liquidacion, parent, false);
        return new LiquidacionHolder(view);
    }

    @Override
    public void onBindViewHolder(LiquidacionHolder holder, int position) {
        Liquidacion liquidacion = listaLiquidacion.get(position);
        holder.tvNroLiquidacion.setText(String.valueOf(liquidacion.get_nroLiquidacion()));
        holder.tvTotalLiq.setText(String.valueOf(liquidacion.get_totalAsignado()));
        holder.tvSaldoLiq.setText(String.valueOf(liquidacion.get_saldoLiquidacion()));
        holder.tvEstadoLiq.setText(liquidacion.get_estadoLiquidacion().get_descripcion());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String sFecha = simpleDateFormat.format(liquidacion.get_fechaLiquidacion());
        holder.tvFechaLiquidacion.setText(sFecha);
    }

    @Override
    public int getItemCount() {
        return listaLiquidacion.size();
    }

    public class LiquidacionHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvNroLiquidacion)
        TextView tvNroLiquidacion;
        @BindView(R.id.tvSaldoLiq)
        TextView tvSaldoLiq;
        @BindView(R.id.tvTotalLiq)
        TextView tvTotalLiq;
        @BindView(R.id.tvFechaLiquidacion)
        TextView tvFechaLiquidacion;
        @BindView(R.id.tvEstadoLiq)
        TextView tvEstadoLiq;

        public LiquidacionHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
