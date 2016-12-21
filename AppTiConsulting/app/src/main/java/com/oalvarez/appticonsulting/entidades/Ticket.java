package com.oalvarez.appticonsulting.entidades;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by oalvarez on 14/12/2016.
 */

public class Ticket {

    private int _nroTicket;
    private int _idCliente;
    private Cliente _cliente;
    private int _idSede;
    private SedeCliente _sede;
    private String _fechaTicket;
    private int _idCategoriaProblema;
    private CategoriaProblema _categoriaProblema;
    private int _idNivelUrgencia;
    private NivelUrgencia _nivelUrgencia;
    private String _titulo;
    private String _detalle;
    private String _solucion;
    private String _observaciones;
    private ArrayList<TicketDetalle> _detalleTicket;
    private ArrayList<TicketRegistro> _registrosTicket;
    private Date _fechaDesde;
    private Date _fechaHasta;
    private int _idEstadoTicket;
    private EstadoTicket _estadoTicket;
    private String _idUsuario;
    private String _tiempoTranscurrido;
    private String _usuarioAsignado;
    private String _nroTicketCliente;
    private int _idUsuarioSede;
    private UsuarioSede _usuarioSede;
    private String _idUsuarioAsignado;
    private String _ordenServicio;
    private String _costoCero;
    private String _idMoneda;
    private Double _tarifa;

    public int get_nroTicket() {
        return _nroTicket;
    }

    public void set_nroTicket(int _nroTicket) {
        this._nroTicket = _nroTicket;
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

    public String get_fechaTicket() {
        return _fechaTicket;
    }

    public void set_fechaTicket(String _fechaTicket) {
        this._fechaTicket = _fechaTicket;
    }

    public int get_idCategoriaProblema() {
        return _idCategoriaProblema;
    }

    public void set_idCategoriaProblema(int _idCategoriaProblema) {
        this._idCategoriaProblema = _idCategoriaProblema;
    }

    public CategoriaProblema get_categoriaProblema() {
        return _categoriaProblema;
    }

    public void set_categoriaProblema(CategoriaProblema _categoriaProblema) {
        this._categoriaProblema = _categoriaProblema;
    }

    public int get_idNivelUrgencia() {
        return _idNivelUrgencia;
    }

    public void set_idNivelUrgencia(int _idNivelUrgencia) {
        this._idNivelUrgencia = _idNivelUrgencia;
    }

    public NivelUrgencia get_nivelUrgencia() {
        return _nivelUrgencia;
    }

    public void set_nivelUrgencia(NivelUrgencia _nivelUrgencia) {
        this._nivelUrgencia = _nivelUrgencia;
    }

    public String get_titulo() {
        return _titulo;
    }

    public void set_titulo(String _titulo) {
        this._titulo = _titulo;
    }

    public String get_detalle() {
        return _detalle;
    }

    public void set_detalle(String _detalle) {
        this._detalle = _detalle;
    }

    public String get_solucion() {
        return _solucion;
    }

    public void set_solucion(String _solucion) {
        this._solucion = _solucion;
    }

    public String get_observaciones() {
        return _observaciones;
    }

    public void set_observaciones(String _observaciones) {
        this._observaciones = _observaciones;
    }

    public ArrayList<TicketDetalle> get_detalleTicket() {
        return _detalleTicket;
    }

    public void set_detalleTicket(ArrayList<TicketDetalle> _detalleTicket) {
        this._detalleTicket = _detalleTicket;
    }

    public ArrayList<TicketRegistro> get_registrosTicket() {
        return _registrosTicket;
    }

    public void set_registrosTicket(ArrayList<TicketRegistro> _registrosTicket) {
        this._registrosTicket = _registrosTicket;
    }

    public Date get_fechaDesde() {
        return _fechaDesde;
    }

    public void set_fechaDesde(Date _fechaDesde) {
        this._fechaDesde = _fechaDesde;
    }

    public Date get_fechaHasta() {
        return _fechaHasta;
    }

    public void set_fechaHasta(Date _fechaHasta) {
        this._fechaHasta = _fechaHasta;
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

    public String get_tiempoTranscurrido() {
        return _tiempoTranscurrido;
    }

    public void set_tiempoTranscurrido(String _tiempoTranscurrido) {
        this._tiempoTranscurrido = _tiempoTranscurrido;
    }

    public String get_usuarioAsignado() {
        return _usuarioAsignado;
    }

    public void set_usuarioAsignado(String _usuarioAsignado) {
        this._usuarioAsignado = _usuarioAsignado;
    }

    public String get_nroTicketCliente() {
        return _nroTicketCliente;
    }

    public void set_nroTicketCliente(String _nroTicketCliente) {
        this._nroTicketCliente = _nroTicketCliente;
    }

    public int get_idUsuarioSede() {
        return _idUsuarioSede;
    }

    public void set_idUsuarioSede(int _idUsuarioSede) {
        this._idUsuarioSede = _idUsuarioSede;
    }

    public UsuarioSede get_usuarioSede() {
        return _usuarioSede;
    }

    public void set_usuarioSede(UsuarioSede _usuarioSede) {
        this._usuarioSede = _usuarioSede;
    }

    public String get_idUsuarioAsignado() {
        return _idUsuarioAsignado;
    }

    public void set_idUsuarioAsignado(String _idUsuarioAsignado) {
        this._idUsuarioAsignado = _idUsuarioAsignado;
    }

    public String get_ordenServicio() {
        return _ordenServicio;
    }

    public void set_ordenServicio(String _ordenServicio) {
        this._ordenServicio = _ordenServicio;
    }

    public String get_costoCero() {
        return _costoCero;
    }

    public void set_costoCero(String _costoCero) {
        this._costoCero = _costoCero;
    }

    public String get_idMoneda() {
        return _idMoneda;
    }

    public void set_idMoneda(String _idMoneda) {
        this._idMoneda = _idMoneda;
    }

    public Double get_tarifa() {
        return _tarifa;
    }

    public void set_tarifa(Double _tarifa) {
        this._tarifa = _tarifa;
    }
}
