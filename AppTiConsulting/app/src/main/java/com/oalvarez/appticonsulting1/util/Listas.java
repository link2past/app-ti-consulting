package com.oalvarez.appticonsulting1.util;

import com.oalvarez.appticonsulting1.database.EstadoTicketDb;
import com.oalvarez.appticonsulting1.database.NivelUrgenciaDb;
import com.oalvarez.appticonsulting1.database.TransporteDb;

import java.lang.reflect.Array;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

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

    public ArrayList<String> listarNivelUrgenciaDb(){
        ArrayList<String> tabla = new ArrayList<>();

        Realm realm = Realm.getDefaultInstance();

        RealmResults<NivelUrgenciaDb> realmResults = null;

        realmResults = realm
                .where(NivelUrgenciaDb.class)
                .findAllSorted("idNivelUrgencia", Sort.ASCENDING);

        for (NivelUrgenciaDb item: realmResults) {
            tabla.add(item.getDescripcion());
        }

        return tabla;
    }

    public ArrayList<String> listarTransporteDb(){
        ArrayList<String> table = new ArrayList<>();
        Realm realm = Realm.getDefaultInstance();
        RealmResults<TransporteDb> realmResults = null;

        realmResults = realm
                .where(TransporteDb.class)
                .findAllSorted("idTransporte", Sort.ASCENDING);

        for(TransporteDb item:realmResults){
            table.add(item.getDescripcion());
        }

        return table;
    }

}
