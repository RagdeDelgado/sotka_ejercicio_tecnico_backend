package com.api.crud.services;

import com.api.crud.model.MovimientoModel;
import com.api.crud.repositories.IMovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MovimientoService {

    @Autowired
    IMovimientoRepository movimientoRepository;

    public ArrayList<MovimientoModel> getMovimientos() {
        return (ArrayList<MovimientoModel>) movimientoRepository.findAll();
    }

    public MovimientoModel saveMovimiento(MovimientoModel movimiento) {
        return movimientoRepository.save(movimiento);
    }

    public Optional<MovimientoModel> getById(Long id) {
        return movimientoRepository.findById(id);
    }

    public MovimientoModel updateById(MovimientoModel request, Long id) {
        MovimientoModel movimiento = movimientoRepository.findById(id).get();

        movimiento.setFecha(request.getFecha());
        movimiento.setTipoMovimiento(request.getTipoMovimiento());
        movimiento.setValor(request.getSaldo());
        movimiento.setSaldo(request.getSaldo());
        movimiento.setSaldoDisponible(request.getSaldoDisponible());

        movimientoRepository.save(movimiento);

        return movimiento;
    }

    public  Boolean deleteMovimiento(Long id) {
        try{
            movimientoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}