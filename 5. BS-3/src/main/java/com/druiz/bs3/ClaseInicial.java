package com.druiz.bs3;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ClaseInicial {

    @PostConstruct //Se ejecuta despues de la creación del bean
    public void funcion1() {
        System.out.println("Hola desde la clase inicial");
    }
}
