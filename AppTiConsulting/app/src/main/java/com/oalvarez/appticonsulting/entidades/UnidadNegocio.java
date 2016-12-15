package com.oalvarez.appticonsulting.entidades;

/**
 * Created by oalvarez on 14/12/2016.
 */

public class UnidadNegocio {

    private int _idUnidadNegocio;
    private String _descripcion;
    private int _idEstado;
    private Estado _estado;

    public int get_idUnidadNegocio() {
        return _idUnidadNegocio;
    }

    public void set_idUnidadNegocio(int _idUnidadNegocio) {
        this._idUnidadNegocio = _idUnidadNegocio;
    }

    public String get_descripcion() {
        return _descripcion;
    }

    public void set_descripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public int get_idEstado() {
        return _idEstado;
    }

    public void set_idEstado(int _idEstado) {
        this._idEstado = _idEstado;
    }

    public Estado get_estado() {
        return _estado;
    }

    public void set_estado(Estado _estado) {
        this._estado = _estado;
    }
}
