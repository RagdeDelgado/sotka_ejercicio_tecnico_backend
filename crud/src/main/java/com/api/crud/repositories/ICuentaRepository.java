package com.api.crud.repositories;

import com.api.crud.model.CuentaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ICuentaRepository extends JpaRepository<CuentaModel, Long> {

    @Query("select c from CuentaModel c where c.cliente.clienteid = :clienteId")
    List<CuentaModel> findByClienteId(@Param("clienteId") Long clienteId);
}
