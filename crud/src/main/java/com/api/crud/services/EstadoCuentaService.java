package com.api.crud.services;

import com.api.crud.dto.EstadoCuentaDTO;
import com.api.crud.model.CuentaModel;
import com.api.crud.model.MovimientoModel;
import com.api.crud.repositories.ICuentaRepository;
import com.api.crud.repositories.IMovimientoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EstadoCuentaService {

    private final ICuentaRepository cuentaRepository;
    private final IMovimientoRepository movimientoRepository;

    public EstadoCuentaService(ICuentaRepository cuentaRepository, IMovimientoRepository movimientoRepository) {
        this.cuentaRepository = cuentaRepository;
        this.movimientoRepository = movimientoRepository;
    }

    public List<EstadoCuentaDTO> generar(Long clienteId, LocalDate desde, LocalDate hasta) {
        List<CuentaModel> cuentas = cuentaRepository.findByClienteId(clienteId);
        List<EstadoCuentaDTO> resultado = new ArrayList<>();

        for (CuentaModel cuenta : cuentas) {
            BigDecimal saldoPrevio = obtenerSaldoPrevio(cuenta, desde);

            List<MovimientoModel> movimientos = movimientoRepository
                    .findByCuentaIdAndFechaBetweenOrderByFechaAsc(cuenta.getId(), desde, hasta);

            for (MovimientoModel mov : movimientos) {
                BigDecimal movValor = toBigDecimal(mov.getValor());
                BigDecimal saldoDisponible = saldoPrevio.add(movValor);

                EstadoCuentaDTO dto = new EstadoCuentaDTO(
                        mov.getFecha(),
                        cuenta.getCliente() != null ? cuenta.getCliente().getPersona().getNombre() : "",
                        cuenta.getNumeroCuenta(),
                        cuenta.getTipoCuenta(),
                        saldoPrevio,
                        cuenta.getEstado(),
                        movValor,
                        saldoDisponible
                );

                resultado.add(dto);
                saldoPrevio = saldoDisponible;
            }
        }

        return resultado;
    }

    private BigDecimal obtenerSaldoPrevio(CuentaModel cuenta, LocalDate desde) {
        Number saldoInicialRaw = cuenta.getSaldoInicial(); // Puede ser BigDecimal o Double
        BigDecimal saldoInicialCuenta = toBigDecimal(saldoInicialRaw);

        Number acumuladoAnteriorRaw = movimientoRepository.sumValorBefore(cuenta.getId(), desde); // BigDecimal o Double según tu repo
        BigDecimal acumuladoAnterior = toBigDecimal(acumuladoAnteriorRaw);

        return saldoInicialCuenta.add(acumuladoAnterior);
    }

    private static BigDecimal toBigDecimal(Number n) {
        if (n == null) return BigDecimal.ZERO;
        if (n instanceof BigDecimal) return (BigDecimal) n;
        // valueOf evita problemas de precisión comunes de new BigDecimal(double)
        return BigDecimal.valueOf(n.doubleValue());
    }
}