package com.api.crud.controllers;

import com.api.crud.model.MovimientoModel;
import com.api.crud.services.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

// imports existentes...

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/movimientos") // si ya tienes un @RequestMapping distinto, conserva el existente
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @GetMapping(path = "/Get")
    public ArrayList<MovimientoModel> getMovimientos(){
        return this.movimientoService.getMovimientos();
    }

    @PostMapping(path = "/Post")
    public MovimientoModel saveMovimiento(@RequestBody MovimientoModel movimiento){
        Double saldo = movimiento.getSaldo();
        Double valor = movimiento.getValor();

        // Si no cuenta con saldo (saldo <= 0 o nulo), se alerta con el mensaje solicitado
        if (saldo == null || saldo <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Saldo no disponible");
        }

        // Calcular saldoDisponible = saldo - valor (si ambos vienen informados)
        if (saldo != null && valor != null) {
            movimiento.setSaldoDisponible(saldo + valor);
        }

        return this.movimientoService.saveMovimiento(movimiento);
    }

    @GetMapping(path = "/Get/{id}")
    public Optional<MovimientoModel> getMovimientoById(@PathVariable("id") Long id){
        return this.movimientoService.getById(id);
    }

    @PutMapping(path = "/Put/{id}")
    public MovimientoModel updateMovimientoById(@RequestBody MovimientoModel request ,@PathVariable Long id){
        return this.movimientoService.updateById(request, id);
    }

    @DeleteMapping(path = "/Delete/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean ok = this.movimientoService.deleteMovimiento(id);

        if (ok){
            return "Movimiento con id " + id + " delete! ";
        }else{
            return "Error, tenemos un problema generando el delete al movimiento con id  " + id;
        }
    }

}