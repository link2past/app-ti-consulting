package com.oalvarez.appticonsulting1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oalvarez.appticonsulting1.R;
import com.oalvarez.appticonsulting1.entidades.TicketArchivo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oalvarez on 21/02/2017.
 */

public class TicketArchivoAdapter extends RecyclerView.Adapter<TicketArchivoAdapter.TicketArchivoHolder> {


    private ArrayList<TicketArchivo> listaDetalle;

    public TicketArchivoAdapter(ArrayList<TicketArchivo> lista) {
        this.listaDetalle = lista;
    }

    @Override
    public TicketArchivoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_archivo_ticket, parent, false);
        return new TicketArchivoHolder(view);
    }

    @Override
    public void onBindViewHolder(TicketArchivoHolder holder, int position) {
        TicketArchivo ticketArchivo = listaDetalle.get(position);
        holder.tvNombreArchivo.setText(ticketArchivo.get_nombreArchivo());
    }

    @Override
    public int getItemCount() {
        return listaDetalle.size();
    }

    public class TicketArchivoHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvNombreArchivo)
        TextView tvNombreArchivo;

        public TicketArchivoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
