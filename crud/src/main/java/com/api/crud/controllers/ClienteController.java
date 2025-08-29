package com.api.crud.controllers;

import com.api.crud.model.ClienteModel;
import com.api.crud.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(path = "/Get")
    public ArrayList<ClienteModel> getClientes(){
        return this.clienteService.getClientes();
    }

    @PostMapping(path = "/Post")
    public ClienteModel saveCliente(@RequestBody ClienteModel cliente){
        return this.clienteService.saveCliente(cliente);
    }

    @GetMapping(path = "/Get/{id}")
    public Optional<ClienteModel> getClienteById(@PathVariable("id") Long id){
        return this.clienteService.getById(id);
    }

    @PutMapping(path = "/Put/{id}")
    public ClienteModel updateClienteById(@RequestBody ClienteModel request ,@PathVariable Long id){
        return this.clienteService.updateById(request, id);
    }


    @DeleteMapping(path = "/Delete/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean ok = this.clienteService.deleteCliente(id);

        if (ok){
            return "Cliente con id " + id + " delete! ";
        }else{
            return "Error, tenemos un problema generando el delete al cliente con id  " + id;
        }

    }
}
