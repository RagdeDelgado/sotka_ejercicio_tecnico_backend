package com.api.crud.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class ClienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clienteid;

    @ManyToOne
    @JoinColumn(name = "personaid", referencedColumnName = "id")
    private PersonaModel persona;

    @Column
    private String contrasena;

    @Column
    private String estado;

    public Long getClienteid() {
        return clienteid;
    }

    public void setClienteid(Long clienteid) {
        this.clienteid = clienteid;
    }

    public PersonaModel getPersona() {
        return persona;
    }

    public void setPersona(PersonaModel persona) {
        this.persona = persona;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}