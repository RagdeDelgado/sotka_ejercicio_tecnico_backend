package com.api.crud.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.jpa.show-sql=false",
        "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect"
})
class IMovimientoRepositoryIT {

    @Autowired
    private IMovimientoRepository movimientoRepository;

    @Test
    void sumValorBefore_sinRegistros_devuelveCero() {
        Long cuentaId = 1L;
        LocalDate fechaCorte = LocalDate.now();

        BigDecimal total = movimientoRepository.sumValorBefore(cuentaId, fechaCorte);

        assertNotNull(total, "El resultado no debe ser null");
        assertEquals(0, total.compareTo(BigDecimal.ZERO), "Para una cuenta sin movimientos debe devolver 0");
    }

    @Test
    void findByCuentaIdAndFechaBetween_sinRegistros_devuelveListaVacia() {
        Long cuentaId = 1L;
        LocalDate desde = LocalDate.now().minusDays(7);
        LocalDate hasta = LocalDate.now();

        List<?> movimientos = movimientoRepository
                .findByCuentaIdAndFechaBetweenOrderByFechaAsc(cuentaId, desde, hasta);

        assertNotNull(movimientos, "La lista no debe ser null");
        assertTrue(movimientos.isEmpty(), "Sin datos, la lista debe estar vacía");
    }
}