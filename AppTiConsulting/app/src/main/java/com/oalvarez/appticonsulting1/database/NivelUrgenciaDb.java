package com.oalvarez.appticonsulting1.database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by oalvarez on 06/02/2017.
 */

public class NivelUrgenciaDb extends RealmObject {
    @PrimaryKey
    private int idNivelUrgencia;
    private String descripcion;

    public int getIdNivelUrgencia() {
        return idNivelUrgencia;
    }

    public void setIdNivelUrgencia(int _idNivelUrgencia) {
        this.idNivelUrgencia = _idNivelUrgencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String _descripcion) {
        this.descripcion = _descripcion;
    }

}
