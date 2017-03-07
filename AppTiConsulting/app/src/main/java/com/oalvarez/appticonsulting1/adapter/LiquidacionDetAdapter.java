package com.oalvarez.appticonsulting1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oalvarez.appticonsulting1.R;
import com.oalvarez.appticonsulting1.entidades.LiquidacionDetalle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oalvarez on 07/03/2017.
 */

public class LiquidacionDetAdapter extends RecyclerView.Adapter<LiquidacionDetAdapter.LiquidacionDetHolder> {


    private ArrayList<LiquidacionDetalle> listaDetalle;

    public LiquidacionDetAdapter(ArrayList<LiquidacionDetalle> lista) {
        listaDetalle = lista;
    }

    @Override
    public LiquidacionDetHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detalle_liq, parent, false);
        return new LiquidacionDetHolder(view);
    }

    @Override
    public void onBindViewHolder(LiquidacionDetHolder holder, int position) {
        LiquidacionDetalle item = listaDetalle.get(position);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String sFecha = simpleDateFormat.format(item.get_fechaDetalle());
        holder.tvFechaDetalle.setText( sFecha);

        holder.tvImporteDetalle.setText(String.valueOf(item.get_importe()));
        holder.tvMotivoDetalle.setText(item.get_motivo());
        holder.tvTransporteDetalle.setText(item.get_tipoTransporte());
        holder.tvInicioDetalle.setText(item.get_lugarInicio());
        holder.tvFinalDetalle.setText(item.get_lugarFin());
    }

    @Override
    public int getItemCount() {
        return listaDetalle.size();
    }

    public class LiquidacionDetHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvFechaDetalle)
        TextView tvFechaDetalle;
        @BindView(R.id.tvImporteDetalle)
        TextView tvImporteDetalle;
        @BindView(R.id.tvMotivoDetalle)
        TextView tvMotivoDetalle;
        @BindView(R.id.tvTransporteDetalle)
        TextView tvTransporteDetalle;
        @BindView(R.id.tvInicioDetalle)
        TextView tvInicioDetalle;
        @BindView(R.id.tvFinalDetalle)
        TextView tvFinalDetalle;

        public LiquidacionDetHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
