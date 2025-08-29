package com.api.crud.model;

import org.junit.jupiter.api.Test;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Prueba unitaria básica para validar el contrato JPA de la entidad ClienteModel.
 * - Debe estar anotada con @Entity
 * - Debe tener constructor sin argumentos
 * - Debe tener un campo anotado con @Id (en la clase o en una superclase)
 */
class ClienteModelTest {

    @Test
    void cumpleContratoJpaBasico() throws Exception {
        Class<ClienteModel> clazz = ClienteModel.class;

        // 1) Anotación @Entity presente
        assertTrue(clazz.isAnnotationPresent(Entity.class),
                "ClienteModel debe estar anotada con @Entity");

        // 2) Constructor sin argumentos
        boolean tieneCtorSinArgs = Arrays.stream(clazz.getDeclaredConstructors())
                .anyMatch(c -> c.getParameterCount() == 0);
        assertTrue(tieneCtorSinArgs,
                "ClienteModel debe tener un constructor sin argumentos (requerido por JPA)");

        // 3) Existencia de un campo con @Id (en la clase o superclases)
        assertTrue(tieneCampoIdAnotado(clazz),
                "ClienteModel debe tener un campo anotado con @Id (propio o heredado)");

        // 4) Instanciable con el constructor por defecto
        Constructor<ClienteModel> ctor = (Constructor<ClienteModel>) Arrays.stream(clazz.getDeclaredConstructors())
                .filter(c -> c.getParameterCount() == 0)
                .findFirst()
                .orElseThrow(() -> new AssertionError("No se encontró constructor sin argumentos"));
        if (!ctor.canAccess(null)) {
            ctor.setAccessible(true);
        }
        ClienteModel instancia = ctor.newInstance();
        assertNotNull(instancia, "Debe poder crearse una instancia de ClienteModel con el constructor por defecto");
    }

    private boolean tieneCampoIdAnotado(Class<?> type) {
        Class<?> actual = type;
        while (actual != null && actual != Object.class) {
            for (Field f : actual.getDeclaredFields()) {
                if (f.isAnnotationPresent(Id.class)) {
                    return true;
                }
            }
            actual = actual.getSuperclass();
        }
        return false;
    }
}