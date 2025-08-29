package com.api.crud.controllers;

import com.api.crud.model.CuentaModel;
import com.api.crud.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping(path = "/Get")
    public ArrayList<CuentaModel> getCuentas(){
        return this.cuentaService.getCuentas();
    }

    @PostMapping(path = "/Post")
    public CuentaModel saveCuenta(@RequestBody CuentaModel cuenta){
        return this.cuentaService.saveCuenta(cuenta);
    }

    @GetMapping(path = "/Get/{id}")
    public Optional<CuentaModel> getCuentaById(@PathVariable("id") Long id){
        return this.cuentaService.getById(id);
    }


    @PutMapping(path = "/Put/{id}")
    public CuentaModel updateCuentaById(@RequestBody CuentaModel request ,@PathVariable Long id){
        return this.cuentaService.updateById(request, id);
    }

    @DeleteMapping(path = "/Delete/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean ok = this.cuentaService.deleteCuenta(id);

        if (ok){
            return "User with id " + id + "delete! ";
        }else{
            return "Error, we have a problem cant't delete user with id  " + id;
        }

    }
}
