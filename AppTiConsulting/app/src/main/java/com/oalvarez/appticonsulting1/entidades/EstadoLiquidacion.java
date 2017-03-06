package com.oalvarez.appticonsulting1.entidades;

/**
 * Created by oalvarez on 05/03/2017.
 */

public class EstadoLiquidacion {
    private int _idEstadoLiquidacion;
    private String _descripcion;
    private int _idEstado;
    private Estado _estado;

    public int get_idEstadoTicket() {
        return _idEstadoLiquidacion;
    }

    public void set_idEstadoTicket(int _idEstadoTicket) {
        this._idEstadoLiquidacion = _idEstadoTicket;
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
