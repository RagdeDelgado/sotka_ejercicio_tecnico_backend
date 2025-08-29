package com.api.crud.repositories;

import com.api.crud.model.PersonaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonaRepository extends JpaRepository<PersonaModel, Long> {

}
