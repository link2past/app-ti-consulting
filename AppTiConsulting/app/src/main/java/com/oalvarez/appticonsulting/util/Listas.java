package com.oalvarez.appticonsulting.util;

import android.content.Context;
import android.widget.Toast;

import com.oalvarez.appticonsulting.database.EstadoTicketDb;
import com.oalvarez.appticonsulting.entidades.EstadoTicket;
import com.oalvarez.appticonsulting.servicios.HelperWs;
import com.oalvarez.appticonsulting.servicios.TicketsApiWs;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by oalvarez on 05/01/2017.
 */

public class Listas {

    public ArrayList<String> listarEstadoTicketDb(int nIdTipoUsuario){

        ArrayList<String> tabla = new ArrayList<>();

        Realm realm = Realm.getDefaultInstance();

        RealmResults<EstadoTicketDb> realmResults = null;

        if (nIdTipoUsuario == 3){
            realmResults = realm
                    .where(EstadoTicketDb.class)
                    .beginGroup()
                        .equalTo("idEstadoTicket", 2 )
                        .or()
                        .equalTo("idEstadoTicket", 3 )
                        .or()
                        .equalTo("idEstadoTicket", 4 )
                        .or()
                        .equalTo("idEstadoTicket", 5 )
                        .or()
                        .equalTo("idEstadoTicket", 6 )
                    .endGroup()
                    .findAllSorted("idEstadoTicket", Sort.ASCENDING);
        }else
        {
            realmResults = realm
                .where(EstadoTicketDb.class)
                .findAllSorted("idEstadoTicket", Sort.ASCENDING);
        }

        for (EstadoTicketDb item: realmResults) {
            tabla.add(item.getDescripcion());
        }

        return tabla;
    }

}
