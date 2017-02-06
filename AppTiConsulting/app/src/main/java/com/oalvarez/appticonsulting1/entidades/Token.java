package com.oalvarez.appticonsulting1.entidades;

/**
 * Created by oalvarez on 14/12/2016.
 */

public class Token {
    private int _idToken;
    private String _idUsuario;
    private String _token;
    private Usuario _usuario;
    //private Date _fechaCreacion;
    //private Date _fechaVencimiento;

    public int get_idToken() {
        return _idToken;
    }

    public void set_idToken(int _idToken) {
        this._idToken = _idToken;
    }

    public String get_idUsuario() {
        return _idUsuario;
    }

    public void set_idUsuario(String _idUsuario) {
        this._idUsuario = _idUsuario;
    }

    public String get_token() {
        return _token;
    }

    public void set_token(String _token) {
        this._token = _token;
    }

    public Usuario get_usuario() {
        return _usuario;
    }

    public void set_usuario(Usuario _usuario) {
        this._usuario = _usuario;
    }

    /*public Date get_fechaCreacion() {
        return _fechaCreacion;
    }

    public void set_fechaCreacion(Date _fechaCreacion) {
        this._fechaCreacion = _fechaCreacion;
    }

    public Date get_fechaVencimiento() {
        return _fechaVencimiento;
    }

    public void set_fechaVencimiento(Date _fechaVencimiento) {
        this._fechaVencimiento = _fechaVencimiento;
    }*/
}
