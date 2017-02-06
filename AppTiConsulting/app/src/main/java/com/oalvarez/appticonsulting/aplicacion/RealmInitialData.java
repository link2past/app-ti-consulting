package com.oalvarez.appticonsulting.aplicacion;

import com.oalvarez.appticonsulting.database.EstadoTicketDb;
import com.oalvarez.appticonsulting.database.NivelUrgenciaDb;

import io.realm.Realm;

/**
 * Created by oalvarez on 08/01/2017.
 */

public class RealmInitialData implements Realm.Transaction {
    @Override
    public void execute(Realm realm) {

        //Estado Ticket
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

        //Nivel Urgencia
        NivelUrgenciaDb nivelUrgenciaDb = new NivelUrgenciaDb();

        nivelUrgenciaDb.setIdNivelUrgencia(1);
        nivelUrgenciaDb.setDescripcion("BAJO");
        realm.insertOrUpdate(nivelUrgenciaDb);

        nivelUrgenciaDb.setIdNivelUrgencia(2);
        nivelUrgenciaDb.setDescripcion("MEDIO - BAJO");
        realm.insertOrUpdate(nivelUrgenciaDb);

        nivelUrgenciaDb.setIdNivelUrgencia(3);
        nivelUrgenciaDb.setDescripcion("MEDIO");
        realm.insertOrUpdate(nivelUrgenciaDb);

        nivelUrgenciaDb.setIdNivelUrgencia(4);
        nivelUrgenciaDb.setDescripcion("MEDIO - ALTO");
        realm.insertOrUpdate(nivelUrgenciaDb);

        nivelUrgenciaDb.setIdNivelUrgencia(5);
        nivelUrgenciaDb.setDescripcion("ALTO");
        realm.insertOrUpdate(nivelUrgenciaDb);
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
