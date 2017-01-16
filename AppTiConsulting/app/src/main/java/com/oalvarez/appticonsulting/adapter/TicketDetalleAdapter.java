package com.oalvarez.appticonsulting.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oalvarez.appticonsulting.R;
import com.oalvarez.appticonsulting.entidades.TicketDetalle;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oalvarez on 15/01/2017.
 */

public class TicketDetalleAdapter extends RecyclerView.Adapter<TicketDetalleAdapter.TicketDetalleHolder> {

    private ArrayList<TicketDetalle> listaDetalle;

    public TicketDetalleAdapter (ArrayList<TicketDetalle> listaDetalle){
        this.listaDetalle = listaDetalle;
    }

    @Override
    public TicketDetalleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detalle_ticket, parent, false);
        return new TicketDetalleHolder(view);
    }

    @Override
    public void onBindViewHolder(TicketDetalleHolder holder, int position) {
        TicketDetalle ticketDetalle = listaDetalle.get(position);
        holder.tvRepuestoDesc.setText(ticketDetalle.get_repuesto().get_descripcion());
        holder.tvCantRepuesto.setText(ticketDetalle.get_cantidad().toString());
        holder.tvMonedaRep.setText(ticketDetalle.get_idMoneda());
        holder.tvPrecioRep.setText(ticketDetalle.get_precio().toString());
    }

    @Override
    public int getItemCount() {
        return listaDetalle.size();
    }

    public class TicketDetalleHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvRepuestoDesc)
        TextView tvRepuestoDesc;
        @BindView(R.id.tvCantRepuesto)
        TextView tvCantRepuesto;
        @BindView(R.id.tvMonedaRep)
        TextView tvMonedaRep;
        @BindView(R.id.tvPrecioRep)
        TextView tvPrecioRep;

        public TicketDetalleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
