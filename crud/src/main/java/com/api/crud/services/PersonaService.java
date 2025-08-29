package com.api.crud.services;

import com.api.crud.model.PersonaModel;
import com.api.crud.repositories.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    IPersonaRepository personaRepository;

    public ArrayList<PersonaModel> getPersonas() {
        return (ArrayList<PersonaModel>) personaRepository.findAll();
    }
    public PersonaModel savePersona(PersonaModel persona) {
        return personaRepository.save(persona);
    }

    public Optional<PersonaModel> getById(Long id) {
        return personaRepository.findById(id);
    }

    public PersonaModel updateById(PersonaModel request, Long id) {
        PersonaModel persona = personaRepository.findById(id).get();

        persona.setNombre(request.getNombre());
        persona.setGenero(request.getGenero());
        persona.setEdad(request.getEdad());
        persona.setIdentificacion(request.getIdentificacion());
        persona.setDireccion(request.getDireccion());
        persona.setTelefono(request.getTelefono());

        personaRepository.save(persona);

        return persona;
    }

    public  Boolean deletePersona(Long id) {
        try{
            personaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}