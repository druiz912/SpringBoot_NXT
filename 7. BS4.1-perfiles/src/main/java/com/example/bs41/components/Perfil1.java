package com.example.bs41.components;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("perfil1")
public class Perfil1 implements Perfiles{

    private String perfil = "perfil 1";

    @Override
    public String miFuncion() {
        return "Hola desde el " + this.perfil;
    }
}
