package com.api.crud.services;

import com.api.crud.model.ClienteModel;
import com.api.crud.model.PersonaModel;
import com.api.crud.repositories.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
// imports nuevos
import com.api.crud.model.PersonaModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;

@Service
public class ClienteService {

    @Autowired
    IClienteRepository clienteRepository;

    public ArrayList<ClienteModel> getClientes() {
        return (ArrayList<ClienteModel>) clienteRepository.findAll();
    }
    public ClienteModel saveCliente(ClienteModel cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<ClienteModel> getById(Long id) {
        return clienteRepository.findById(id);
    }

    // campo nuevo para referencias perezosas
    @PersistenceContext
    private EntityManager entityManager;

    // método actualizado
    public ClienteModel updateById(ClienteModel cliente, Long id) {
        ClienteModel existente = this.clienteRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con id: " + id));

        if (cliente.getContrasena() != null) {
            existente.setContrasena(cliente.getContrasena());
        }
        if (cliente.getEstado() != null) {
            existente.setEstado(cliente.getEstado());
        }

        // set del campo personaId -> asigna la relación ManyToOne usando el ID recibido
        if (cliente.getPersona() != null && cliente.getPersona().getId() != null) {
            Long personaId = cliente.getPersona().getId();
            PersonaModel personaRef = entityManager.getReference(PersonaModel.class, personaId);
            existente.setPersona(personaRef);
        }

        return this.clienteRepository.save(existente);
    }

    public  Boolean deleteCliente(Long id) {
        try{
            clienteRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}