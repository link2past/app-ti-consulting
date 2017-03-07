package com.oalvarez.appticonsulting1.entidades;

import java.util.Date;

/**
 * Created by oalvarez on 05/03/2017.
 */

public class LiquidacionDetalle {
    private int _nroLiquidacion;
    private int _nroDetalle;
    private Date _fechaDetalle;
    private String _motivo;
    private String _lugarInicio;
    private String _lugarFin;
    private Double _importe;
    private int _nroTicket;
    private String _tipoTransporte;

    public int get_nroLiquidacion() {
        return _nroLiquidacion;
    }

    public void set_nroLiquidacion(int _nroLiquidacion) {
        this._nroLiquidacion = _nroLiquidacion;
    }

    public int get_nroDetalle() {
        return _nroDetalle;
    }

    public void set_nroDetalle(int _nroDetalle) {
        this._nroDetalle = _nroDetalle;
    }

    public Date get_fechaDetalle() {
        return _fechaDetalle;
    }

    public void set_fechaDetalle(Date _fechaDetalle) {
        this._fechaDetalle = _fechaDetalle;
    }

    public String get_motivo() {
        return _motivo;
    }

    public void set_motivo(String _motivo) {
        this._motivo = _motivo;
    }

    public String get_lugarInicio() {
        return _lugarInicio;
    }

    public void set_lugarInicio(String _lugarInicio) {
        this._lugarInicio = _lugarInicio;
    }

    public String get_lugarFin() {
        return _lugarFin;
    }

    public void set_lugarFin(String _lugarFin) {
        this._lugarFin = _lugarFin;
    }

    public Double get_importe() {
        return _importe;
    }

    public void set_importe(Double _importe) {
        this._importe = _importe;
    }

    public int get_nroTicket() {
        return _nroTicket;
    }

    public void set_nroTicket(int _nroTicket) {
        this._nroTicket = _nroTicket;
    }

    public String get_tipoTransporte() {
        return _tipoTransporte;
    }

    public void set_tipoTransporte(String _tipoTransporte) {
        this._tipoTransporte = _tipoTransporte;
    }
}
