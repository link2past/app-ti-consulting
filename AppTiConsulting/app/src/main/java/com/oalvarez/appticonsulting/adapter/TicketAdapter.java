package com.oalvarez.appticonsulting.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oalvarez.appticonsulting.R;
import com.oalvarez.appticonsulting.entidades.Ticket;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oalvarez on 21/12/2016.
 */

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketHolder> {


    private ArrayList<Ticket> listaTickets;

    public TicketAdapter(ArrayList<Ticket> lista) {
        this.listaTickets = lista;
    }

    public class TicketHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvNroTicket)
        TextView tvNroTicket;
        @BindView(R.id.tvEstadoTicket)
        TextView tvEstadoTicket;
        @BindView(R.id.tvFechaTicket)
        TextView tvFechaTicket;
        @BindView(R.id.tvClienteTicket)
        TextView tvClienteTicket;
        @BindView(R.id.tvSedeTicket)
        TextView tvSedeTicket;

        public TicketHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public TicketHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket, parent, false);
        return new TicketHolder(view);
    }

    @Override
    public void onBindViewHolder(TicketHolder holder, int position) {
        Ticket oTicket = listaTickets.get(position);
        holder.tvNroTicket.setText( String.valueOf(oTicket.get_nroTicket()) );
        holder.tvEstadoTicket.setText( oTicket.get_estadoTicket().get_descripcion() );

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String sFecha = simpleDateFormat.format(oTicket.get_fechaTicket());
        holder.tvFechaTicket.setText( sFecha);

        holder.tvClienteTicket.setText(oTicket.get_cliente().get_razonSocial());
        holder.tvSedeTicket.setText(oTicket.get_sede().get_nombre());
    }

    @Override
    public int getItemCount() {
        return listaTickets.size();
    }
}
