package com.oalvarez.appticonsulting.database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by oalvarez on 05/01/2017.
 */

public class EstadoTicketDb extends RealmObject {
    @PrimaryKey
    private int idEstadoTicket;
    private String descripcion;

    public int getIdEstadoTicket() {
        return idEstadoTicket;
    }

    public void setIdEstadoTicket(int idEstadoTicket) {
        this.idEstadoTicket = idEstadoTicket;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
