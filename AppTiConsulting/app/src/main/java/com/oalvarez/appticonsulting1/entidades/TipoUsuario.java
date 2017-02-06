package com.oalvarez.appticonsulting1.entidades;

import com.google.gson.annotations.SerializedName;

/**
 * Created by oalvarez on 14/12/2016.
 */

public class TipoUsuario {

    @SerializedName("_idTipoUsuario")
    private int _idTipoUsuario;
    @SerializedName("_descripcion")
    private String _descripcion;
    @SerializedName("_idEstado")
    private int _idEstado;
    @SerializedName("_usuarioCreacion")
    private String _usuarioCreacion;
    @SerializedName("_usuarioModificacion")
    private String _usuarioModificacion;

    public int get_idTipoUsuario() {
        return _idTipoUsuario;
    }

    public void set_idTipoUsuario(int _idTipoUsuario) {
        this._idTipoUsuario = _idTipoUsuario;
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
