package com.api.crud.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "movimiento")
public class MovimientoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "numero_cuenta", referencedColumnName = "numero_cuenta")
    private CuentaModel cuenta;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "tipo_movimiento")
    private String tipoMovimiento;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "saldo")
    private Double saldo;

    @Column(name = "saldo_disponible")
    private Double saldoDisponible;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CuentaModel getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaModel cuenta) {
        this.cuenta = cuenta;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(Double saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }
}
