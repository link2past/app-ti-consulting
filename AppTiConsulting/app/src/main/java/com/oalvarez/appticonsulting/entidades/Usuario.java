package com.oalvarez.appticonsulting.entidades;

import java.util.Date;

/**
 * Created by oalvarez on 14/12/2016.
 */

public class Usuario {
    private String _usuario;
    private String _contraseña;
    private String _nombre;
    private Date _fechaCreacion;
    private int _diasSolicitudCambio;
    private Date _fechaUltimoCambio;
    private String _requiereCambioClave;
    private int _idEstado;
    private int _idTipoUsuario;
    private TipoUsuario _tipoUsuario;
    private String _email;

    public String get_usuario() {
        return _usuario;
    }

    public void set_usuario(String _usuario) {
        this._usuario = _usuario;
    }

    public String get_contraseña() {
        return _contraseña;
    }

    public void set_contraseña(String _contraseña) {
        this._contraseña = _contraseña;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public Date get_fechaCreacion() {
        return _fechaCreacion;
    }

    public void set_fechaCreacion(Date _fechaCreacion) {
        this._fechaCreacion = _fechaCreacion;
    }

    public int get_diasSolicitudCambio() {
        return _diasSolicitudCambio;
    }

    public void set_diasSolicitudCambio(int _diasSolicitudCambio) {
        this._diasSolicitudCambio = _diasSolicitudCambio;
    }

    public Date get_fechaUltimoCambio() {
        return _fechaUltimoCambio;
    }

    public void set_fechaUltimoCambio(Date _fechaUltimoCambio) {
        this._fechaUltimoCambio = _fechaUltimoCambio;
    }

    public String get_requiereCambioClave() {
        return _requiereCambioClave;
    }

    public void set_requiereCambioClave(String _requiereCambioClave) {
        this._requiereCambioClave = _requiereCambioClave;
    }

    public int get_idEstado() {
        return _idEstado;
    }

    public void set_idEstado(int _idEstado) {
        this._idEstado = _idEstado;
    }

    public int get_idTipoUsuario() {
        return _idTipoUsuario;
    }

    public void set_idTipoUsuario(int _idTipoUsuario) {
        this._idTipoUsuario = _idTipoUsuario;
    }

    public TipoUsuario get_tipoUsuario() {
        return _tipoUsuario;
    }

    public void set_tipoUsuario(TipoUsuario _tipoUsuario) {
        this._tipoUsuario = _tipoUsuario;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }
}
