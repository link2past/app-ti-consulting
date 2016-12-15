package com.oalvarez.appticonsulting.entidades;

/**
 * Created by oalvarez on 14/12/2016.
 */

public class Repuesto {

    private int _idRepuesto;
    private String _descripcion;
    private String _idMoneda;
    private Moneda _moneda;
    private Double _precioActual;
    private Double _stockActual;
    private String _usuarioCreacion;
    private String _usuarioModificacion;
    private int _idEstado;
    private Estado _estado;

    public int get_idRepuesto() {
        return _idRepuesto;
    }

    public void set_idRepuesto(int _idRepuesto) {
        this._idRepuesto = _idRepuesto;
    }

    public String get_descripcion() {
        return _descripcion;
    }

    public void set_descripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public String get_idMoneda() {
        return _idMoneda;
    }

    public void set_idMoneda(String _idMoneda) {
        this._idMoneda = _idMoneda;
    }

    public Moneda get_moneda() {
        return _moneda;
    }

    public void set_moneda(Moneda _moneda) {
        this._moneda = _moneda;
    }

    public Double get_precioActual() {
        return _precioActual;
    }

    public void set_precioActual(Double _precioActual) {
        this._precioActual = _precioActual;
    }

    public Double get_stockActual() {
        return _stockActual;
    }

    public void set_stockActual(Double _stockActual) {
        this._stockActual = _stockActual;
    }

    public String get_usuarioCreacion() {
        return _usuarioCreacion;
    }

    public void set_usuarioCreacion(String _usuarioCreacion) {
        this._usuarioCreacion = _usuarioCreacion;
    }

    public String get_usuarioModificacion() {
        return _usuarioModificacion;
    }

    public void set_usuarioModificacion(String _usuarioModificacion) {
        this._usuarioModificacion = _usuarioModificacion;
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
