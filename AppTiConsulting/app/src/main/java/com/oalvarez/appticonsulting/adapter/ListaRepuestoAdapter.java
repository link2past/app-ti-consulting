package com.oalvarez.appticonsulting.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oalvarez.appticonsulting.R;
import com.oalvarez.appticonsulting.entidades.Repuesto;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oalvarez on 15/01/2017.
 */

public class ListaRepuestoAdapter extends RecyclerView.Adapter<ListaRepuestoAdapter.ListaRepuestoHolder> {

    private ArrayList<Repuesto> listaRepuesto;

    public ListaRepuestoAdapter(ArrayList<Repuesto> listaRepuesto){
        this.listaRepuesto = listaRepuesto;
    }

    @Override
    public ListaRepuestoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_repuesto, parent, false);
        return new ListaRepuestoHolder(view);
    }

    @Override
    public void onBindViewHolder(ListaRepuestoHolder holder, int position) {
        Repuesto repuesto = listaRepuesto.get(position);
        holder.tvRepuestoDesc.setText(repuesto.get_descripcion().trim());
        holder.tvMonedaRep.setText(repuesto.get_idMoneda().trim());
        holder.tvPrecioRep.setText(repuesto.get_precioActual().toString());
    }

    @Override
    public int getItemCount() {
        return listaRepuesto.size();
    }

    public class ListaRepuestoHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvRepuestoDesc)
        TextView tvRepuestoDesc;
        @BindView(R.id.tvMonedaRep)
        TextView tvMonedaRep;
        @BindView(R.id.tvPrecioRep)
        TextView tvPrecioRep;


        public ListaRepuestoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
