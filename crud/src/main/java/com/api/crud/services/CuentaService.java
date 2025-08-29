package com.api.crud.services;

import com.api.crud.model.CuentaModel;
import com.api.crud.repositories.ICuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CuentaService {

    @Autowired
    ICuentaRepository cuentaRepository;

    public ArrayList<CuentaModel> getCuentas() {
        return (ArrayList<CuentaModel>) cuentaRepository.findAll();
    }
public CuentaModel saveCuenta(CuentaModel cuenta) {
    try {
        return cuentaRepository.save(cuenta);
    } catch (org.springframework.dao.DataIntegrityViolationException ex) {
        // Ocurre cuando se intenta guardar un numero_cuenta duplicado (restricción única)
        throw new IllegalArgumentException("Ya existe una cuenta con ese número. Verifica e intenta nuevamente.", ex);
    }
}

    public Optional<CuentaModel> getById(Long id) {
        return cuentaRepository.findById(id);
    }

    public CuentaModel updateById(CuentaModel request, Long id) {
        CuentaModel cuenta = cuentaRepository.findById(id).get();

        //cuenta.setNumeroCuenta(request.getNumeroCuenta());
        cuenta.setTipoCuenta(request.getTipoCuenta());
        cuenta.setSaldoInicial(request.getSaldoInicial());
        cuenta.setEstado(request.getEstado());

        cuentaRepository.save(cuenta);

        return cuenta;
    }

    public  Boolean deleteCuenta(Long id) {
        try{
            cuentaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}