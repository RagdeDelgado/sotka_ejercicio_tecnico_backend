package com.api.crud.repositories;

import com.api.crud.model.MovimientoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface IMovimientoRepository extends JpaRepository<MovimientoModel, Long> {

    List<MovimientoModel> findByCuentaIdAndFechaBetweenOrderByFechaAsc(Long cuentaId, LocalDate desde, LocalDate hasta);

    @Query("select coalesce(sum(m.valor), 0) " +
            "from MovimientoModel m " +
            "where m.cuenta.id = :cuentaId and m.fecha < :desde")
    BigDecimal sumValorBefore(@Param("cuentaId") Long cuentaId, @Param("desde") LocalDate desde);
}