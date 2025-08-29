package com.api.crud.controllers;

import com.api.crud.model.PersonaModel;
import com.api.crud.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping(path = "/Get")
    public ArrayList<PersonaModel> getPersonas(){
        return this.personaService.getPersonas();
    }

    @PostMapping(path = "/Post")
    public PersonaModel savePersona(@RequestBody PersonaModel persona){
        return this.personaService.savePersona(persona);
    }

    @GetMapping(path = "/Get/{id}")
    public Optional<PersonaModel> getPersonaById(@PathVariable("id") Long id){
        return this.personaService.getById(id);
    }

    @PutMapping(path = "/Put/{id}")
    public PersonaModel updatePersonaById(@RequestBody PersonaModel request ,@PathVariable Long id){
        return this.personaService.updateById(request, id);
    }


    @DeleteMapping(path = "/Delete/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean ok = this.personaService.deletePersona(id);

        if (ok){
            return "Persona con id " + id + " delete! ";
        }else{
            return "Error, tenemos un problema generando el delete a la persona con id  " + id;
        }

    }
}
