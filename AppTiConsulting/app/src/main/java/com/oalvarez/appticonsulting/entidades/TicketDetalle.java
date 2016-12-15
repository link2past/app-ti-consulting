package com.oalvarez.appticonsulting.entidades;

/**
 * Created by oalvarez on 14/12/2016.
 */

public class TicketDetalle {

    private int _nroTicket;
    private int _idRepuesto;
    private Repuesto _repuesto;
    private Double _cantidad;
    private String _idMoneda;
    private Moneda _moneda;
    private Double _precio;
    private String _usuarioCreacion;
    private String _usuarioModificacion;

    public int get_nroTicket() {
        return _nroTicket;
    }

    public void set_nroTicket(int _nroTicket) {
        this._nroTicket = _nroTicket;
    }

    public int get_idRepuesto() {
        return _idRepuesto;
    }

    public void set_idRepuesto(int _idRepuesto) {
        this._idRepuesto = _idRepuesto;
    }

    public Repuesto get_repuesto() {
        return _repuesto;
    }

    public void set_repuesto(Repuesto _repuesto) {
        this._repuesto = _repuesto;
    }

    public Double get_cantidad() {
        return _cantidad;
    }

    public void set_cantidad(Double _cantidad) {
        this._cantidad = _cantidad;
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

    public Double get_precio() {
        return _precio;
    }

    public void set_precio(Double _precio) {
        this._precio = _precio;
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
}
