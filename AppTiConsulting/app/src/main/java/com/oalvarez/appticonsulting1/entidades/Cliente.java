package com.oalvarez.appticonsulting1.entidades;

/**
 * Created by oalvarez on 14/12/2016.
 */

public class Cliente {

    private String _idCliente;
    private String _razonSocial;
    private String _nroDi;
    private String _direccion;
    private String _idDepartamento;
    private String _idProvincia;
    private String _idDistrito;
    private String _telefono;
    private String _email;
    private String _nombreContacto;
    private String _cargoContacto;
    private int _idEstado;
    private Estado _estado;
    private String _idMoneda;
    private Double _tarifaDiurna;
    private Double _tarifaNocturna;

    public String get_idCliente() {
        return _idCliente;
    }

    public void set_idCliente(String _idCliente) {
        this._idCliente = _idCliente;
    }

    public String get_razonSocial() {
        return _razonSocial;
    }

    public void set_razonSocial(String _razonSocial) {
        this._razonSocial = _razonSocial;
    }

    public String get_nroDi() {
        return _nroDi;
    }

    public void set_nroDi(String _nroDi) {
        this._nroDi = _nroDi;
    }

    public String get_direccion() {
        return _direccion;
    }

    public void set_direccion(String _direccion) {
        this._direccion = _direccion;
    }

    public String get_idDepartamento() {
        return _idDepartamento;
    }

    public void set_idDepartamento(String _idDepartamento) {
        this._idDepartamento = _idDepartamento;
    }

    public String get_idProvincia() {
        return _idProvincia;
    }

    public void set_idProvincia(String _idProvincia) {
        this._idProvincia = _idProvincia;
    }

    public String get_idDistrito() {
        return _idDistrito;
    }

    public void set_idDistrito(String _idDistrito) {
        this._idDistrito = _idDistrito;
    }

    public String get_telefono() {
        return _telefono;
    }

    public void set_telefono(String _telefono) {
        this._telefono = _telefono;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_nombreContacto() {
        return _nombreContacto;
    }

    public void set_nombreContacto(String _nombreContacto) {
        this._nombreContacto = _nombreContacto;
    }

    public String get_cargoContacto() {
        return _cargoContacto;
    }

    public void set_cargoContacto(String _cargoContacto) {
        this._cargoContacto = _cargoContacto;
    }

    public int get_idEstado() {
        return _idEstado;
    }

    public void set_idEstado(int _idEstado) {
        this._idEstado = _idEstado;
    }

    public String get_idMoneda() {
        return _idMoneda;
    }

    public void set_idMoneda(String _idMoneda) {
        this._idMoneda = _idMoneda;
    }

    public Double get_tarifaDiurna() {
        return _tarifaDiurna;
    }

    public void set_tarifaDiurna(Double _tarifaDiurna) {
        this._tarifaDiurna = _tarifaDiurna;
    }

    public Double get_tarifaNocturna() {
        return _tarifaNocturna;
    }

    public void set_tarifaNocturna(Double _tarifaNocturna) {
        this._tarifaNocturna = _tarifaNocturna;
    }

    public Estado get_estado() {
        return _estado;
    }

    public void set_estado(Estado _estado) {
        this._estado = _estado;
    }
}
