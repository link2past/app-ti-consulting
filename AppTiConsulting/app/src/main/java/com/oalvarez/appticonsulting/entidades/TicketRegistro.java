package com.oalvarez.appticonsulting.entidades;

import java.util.Date;

/**
 * Created by oalvarez on 14/12/2016.
 */

public class TicketRegistro {

    private int _nroTicket;
    private int _idEstadoTicket;
    private EstadoTicket _estadoTicket;
    private String _idUsuario;
    private Usuario _usuario;
    private Date _fechaHoraRegistro;
    private String _idUsuarioAsignado;
    private Usuario _usuarioAsignado;
    private String _observacion;

    public int get_nroTicket() {
        return _nroTicket;
    }

    public void set_nroTicket(int _nroTicket) {
        this._nroTicket = _nroTicket;
    }

    public int get_idEstadoTicket() {
        return _idEstadoTicket;
    }

    public void set_idEstadoTicket(int _idEstadoTicket) {
        this._idEstadoTicket = _idEstadoTicket;
    }

    public EstadoTicket get_estadoTicket() {
        return _estadoTicket;
    }

    public void set_estadoTicket(EstadoTicket _estadoTicket) {
        this._estadoTicket = _estadoTicket;
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

    public Date get_fechaHoraRegistro() {
        return _fechaHoraRegistro;
    }

    public void set_fechaHoraRegistro(Date _fechaHoraRegistro) {
        this._fechaHoraRegistro = _fechaHoraRegistro;
    }

    public String get_idUsuarioAsignado() {
        return _idUsuarioAsignado;
    }

    public void set_idUsuarioAsignado(String _idUsuarioAsignado) {
        this._idUsuarioAsignado = _idUsuarioAsignado;
    }

    public Usuario get_usuarioAsignado() {
        return _usuarioAsignado;
    }

    public void set_usuarioAsignado(Usuario _usuarioAsignado) {
        this._usuarioAsignado = _usuarioAsignado;
    }

    public String get_observacion() {
        return _observacion;
    }

    public void set_observacion(String _observacion) {
        this._observacion = _observacion;
    }
}
