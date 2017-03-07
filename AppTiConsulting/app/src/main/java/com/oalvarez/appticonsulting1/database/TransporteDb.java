package com.oalvarez.appticonsulting1.database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by oalvarez on 07/03/2017.
 */

public class TransporteDb extends RealmObject {
    @PrimaryKey
    private int idTransporte;
    private String descripcion;

    public int getIdTransporte() {
        return idTransporte;
    }

    public void setIdTransporte(int idTransporte) {
        this.idTransporte = idTransporte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
