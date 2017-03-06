package com.oalvarez.appticonsulting1.entidades;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by oalvarez on 05/03/2017.
 */

public class Liquidacion {
    private int _nroLiquidacion;
    private String _idUsuario;
    private Usuario _usuario;
    private Date _fechaLiquidacion;
    private Double _saldoInicial;
    private Double _montoAsignado;
    private Double _totalAsignado;
    private Double _totalLiquidacion;
    private Double _saldoLiquidacion;
    private int _idEstadoLiquidacion;
    private EstadoLiquidacion _estadoLiquidacion;
    private String _idUsuarioEncargado;
    private Usuario _usuarioEncargado;
    private ArrayList<LiquidacionDetalle> _detalle;

    public int get_nroLiquidacion() {
        return _nroLiquidacion;
    }

    public void set_nroLiquidacion(int _nroLiquidacion) {
        this._nroLiquidacion = _nroLiquidacion;
    }

    public String get_idUsuario() {
        return _idUsuario;
    }

    public void set_idUsuario(String _idUsuario) {
        this._idUsuario = _idUsuario;
    }

    public Usuario get_usuario() {
        return _usuario;
    }

    public void set_usuario(Usuario _usuario) {
        this._usuario = _usuario;
    }

    public Date get_fechaLiquidacion() {
        return _fechaLiquidacion;
    }

    public void set_fechaLiquidacion(Date _fechaLiquidacion) {
        this._fechaLiquidacion = _fechaLiquidacion;
    }

    public Double get_saldoInicial() {
        return _saldoInicial;
    }

    public void set_saldoInicial(Double _saldoInicial) {
        this._saldoInicial = _saldoInicial;
    }

    public Double get_montoAsignado() {
        return _montoAsignado;
    }

    public void set_montoAsignado(Double _montoAsignado) {
        this._montoAsignado = _montoAsignado;
    }

    public Double get_totalLiquidacion() {
        return _totalLiquidacion;
    }

    public void set_totalLiquidacion(Double _totalLiquidacion) {
        this._totalLiquidacion = _totalLiquidacion;
    }

    public Double get_saldoLiquidacion() {
        return _saldoLiquidacion;
    }

    public void set_saldoLiquidacion(Double _saldoLiquidacion) {
        this._saldoLiquidacion = _saldoLiquidacion;
    }

    public int get_idEstadoLiquidacion() {
        return _idEstadoLiquidacion;
    }

    public void set_idEstadoLiquidacion(int _idEstadoLiquidacion) {
        this._idEstadoLiquidacion = _idEstadoLiquidacion;
    }

    public EstadoLiquidacion get_estadoLiquidacion() {
        return _estadoLiquidacion;
    }

    public void set_estadoLiquidacion(EstadoLiquidacion _estadoLiquidacion) {
        this._estadoLiquidacion = _estadoLiquidacion;
    }

    public String get_idUsuarioEncargado() {
        return _idUsuarioEncargado;
    }

    public void set_idUsuarioEncargado(String _idUsuarioEncargado) {
        this._idUsuarioEncargado = _idUsuarioEncargado;
    }

    public Usuario get_usuarioEncargado() {
        return _usuarioEncargado;
    }

    public void set_usuarioEncargado(Usuario _usuarioEncargado) {
        this._usuarioEncargado = _usuarioEncargado;
    }

    public ArrayList<LiquidacionDetalle> get_detalle() {
        return _detalle;
    }

    public void set_detalle(ArrayList<LiquidacionDetalle> _detalle) {
        this._detalle = _detalle;
    }

    public Double get_totalAsignado() {
        return _totalAsignado;
    }

    public void set_totalAsignado(Double _totalAsignado) {
        this._totalAsignado = _totalAsignado;
    }
}
