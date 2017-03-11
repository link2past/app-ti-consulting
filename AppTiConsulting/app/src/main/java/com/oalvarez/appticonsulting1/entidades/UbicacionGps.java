package com.oalvarez.appticonsulting1.entidades;

import java.util.Date;

/**
 * Created by oalvarez on 11/03/2017.
 */

public class UbicacionGps {
    private String _idUsuario;
    private Usuario _usuario;
    private Date _fechaHoraRegistro;
    private String _latitud;
    private String _longitud;

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

    public String get_latitud() {
        return _latitud;
    }

    public void set_latitud(String _latitud) {
        this._latitud = _latitud;
    }

    public String get_longitud() {
        return _longitud;
    }

    public void set_longitud(String _longitud) {
        this._longitud = _longitud;
    }
}
