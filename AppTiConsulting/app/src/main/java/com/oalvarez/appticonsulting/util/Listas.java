package com.oalvarez.appticonsulting.util;

import android.widget.Toast;

import com.oalvarez.appticonsulting.entidades.EstadoTicket;
import com.oalvarez.appticonsulting.servicios.HelperWs;
import com.oalvarez.appticonsulting.servicios.TicketsApiWs;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by oalvarez on 05/01/2017.
 */

public class Listas {

    ArrayList<EstadoTicket> listaEstadoTicket = new ArrayList<>();
    ArrayList<String> tablaEstadoTicket = new ArrayList<>();

    public ArrayList<String> listarEstadoTicketDb(){

        TicketsApiWs ticketsApiWs = HelperWs.getConfiguration().create(TicketsApiWs.class);
        final Call<ArrayList<EstadoTicket>> tabla = ticketsApiWs.ListarEstadoTicket();

        tabla.enqueue(new Callback<ArrayList<EstadoTicket>>() {
            @Override
            public void onResponse(Call<ArrayList<EstadoTicket>> call, Response<ArrayList<EstadoTicket>> response) {
                listaEstadoTicket = response.body();

                for (EstadoTicket item: listaEstadoTicket) {
                    tablaEstadoTicket.add(item.get_descripcion());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<EstadoTicket>> call, Throwable t) {

            }
        });
        return tablaEstadoTicket;
    }

}
