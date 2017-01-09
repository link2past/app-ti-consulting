package com.oalvarez.appticonsulting.aplicacion;

import com.oalvarez.appticonsulting.database.EstadoTicketDb;

import io.realm.Realm;

/**
 * Created by oalvarez on 08/01/2017.
 */

public class RealmInitialData implements Realm.Transaction {
    @Override
    public void execute(Realm realm) {
        EstadoTicketDb estadoTicketDb = new EstadoTicketDb();

        estadoTicketDb.setIdEstadoTicket(1);
        estadoTicketDb.setDescripcion("REGISTRADO");
        realm.insertOrUpdate(estadoTicketDb);

        estadoTicketDb.setIdEstadoTicket(2);
        estadoTicketDb.setDescripcion("ASIGNADO");
        realm.insertOrUpdate(estadoTicketDb);

        estadoTicketDb.setIdEstadoTicket(3);
        estadoTicketDb.setDescripcion("RECIBIDO");
        realm.insertOrUpdate(estadoTicketDb);

        estadoTicketDb.setIdEstadoTicket(4);
        estadoTicketDb.setDescripcion("ATENDIDO");
        realm.insertOrUpdate(estadoTicketDb);

        estadoTicketDb.setIdEstadoTicket(5);
        estadoTicketDb.setDescripcion("CERRADO");
        realm.insertOrUpdate(estadoTicketDb);

        estadoTicketDb.setIdEstadoTicket(6);
        estadoTicketDb.setDescripcion("EN ESPERA DE REPUESTO");
        realm.insertOrUpdate(estadoTicketDb);

        estadoTicketDb.setIdEstadoTicket(7);
        estadoTicketDb.setDescripcion("ANULADO");
        realm.insertOrUpdate(estadoTicketDb);
    }

    @Override
    public int hashCode() {
        return RealmInitialData.class.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof RealmInitialData;
    }
}
