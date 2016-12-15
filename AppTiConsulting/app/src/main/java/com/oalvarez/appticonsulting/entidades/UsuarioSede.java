package com.oalvarez.appticonsulting.entidades;

/**
 * Created by oalvarez on 14/12/2016.
 */

public class UsuarioSede {

    private int _idUsuarioSede;
    private String _nombre;
    private int _idEstado;
    private Estado _estado;
    private int _idAreaUsuarioSede;
    private AreaUsuarioSede _areaUsuarioSede;
    private int _idCliente;
    private Cliente _cliente;
    private int _idSede;
    private SedeCliente _sede;

    public int get_idUsuarioSede() {
        return _idUsuarioSede;
    }

    public void set_idUsuarioSede(int _idUsuarioSede) {
        this._idUsuarioSede = _idUsuarioSede;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
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

    public int get_idAreaUsuarioSede() {
        return _idAreaUsuarioSede;
    }

    public void set_idAreaUsuarioSede(int _idAreaUsuarioSede) {
        this._idAreaUsuarioSede = _idAreaUsuarioSede;
    }

    public AreaUsuarioSede get_areaUsuarioSede() {
        return _areaUsuarioSede;
    }

    public void set_areaUsuarioSede(AreaUsuarioSede _areaUsuarioSede) {
        this._areaUsuarioSede = _areaUsuarioSede;
    }

    public int get_idCliente() {
        return _idCliente;
    }

    public void set_idCliente(int _idCliente) {
        this._idCliente = _idCliente;
    }

    public Cliente get_cliente() {
        return _cliente;
    }

    public void set_cliente(Cliente _cliente) {
        this._cliente = _cliente;
    }

    public int get_idSede() {
        return _idSede;
    }

    public void set_idSede(int _idSede) {
        this._idSede = _idSede;
    }

    public SedeCliente get_sede() {
        return _sede;
    }

    public void set_sede(SedeCliente _sede) {
        this._sede = _sede;
    }
}
