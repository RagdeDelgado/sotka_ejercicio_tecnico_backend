package com.api.crud.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EstadoCuentaDTO {

    @JsonProperty("Fecha")
    @JsonFormat(pattern = "d/M/yyyy")
    private LocalDate fecha;

    @JsonProperty("Cliente")
    private String cliente;

    @JsonProperty("Numero Cuenta")
    private String numeroCuenta;

    @JsonProperty("Tipo")
    private String tipo;

    @JsonProperty("Saldo Inicial")
    private BigDecimal saldoInicial;

    @JsonProperty("Estado")
    private String estado;

    @JsonProperty("Movimiento")
    private BigDecimal movimiento;

    @JsonProperty("Saldo Disponible")
    private BigDecimal saldoDisponible;

    public EstadoCuentaDTO() {}

    public EstadoCuentaDTO(LocalDate fecha,
                           String cliente,
                           String numeroCuenta,
                           String tipo,
                           BigDecimal saldoInicial,
                           String estado,
                           BigDecimal movimiento,
                           BigDecimal saldoDisponible) {
        this.fecha = fecha;
        this.cliente = cliente;
        this.numeroCuenta = numeroCuenta;
        this.tipo = tipo;
        this.saldoInicial = saldoInicial;
        this.estado = estado;
        this.movimiento = movimiento;
        this.saldoDisponible = saldoDisponible;
    }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public String getNumeroCuenta() { return numeroCuenta; }
    public void setNumeroCuenta(String numeroCuenta) { this.numeroCuenta = numeroCuenta; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public BigDecimal getSaldoInicial() { return saldoInicial; }
    public void setSaldoInicial(BigDecimal saldoInicial) { this.saldoInicial = saldoInicial; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public BigDecimal getMovimiento() { return movimiento; }
    public void setMovimiento(BigDecimal movimiento) { this.movimiento = movimiento; }
    public BigDecimal getSaldoDisponible() { return saldoDisponible; }
    public void setSaldoDisponible(BigDecimal saldoDisponible) { this.saldoDisponible = saldoDisponible; }
}