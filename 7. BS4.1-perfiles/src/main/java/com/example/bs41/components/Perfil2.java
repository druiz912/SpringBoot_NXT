package com.example.bs41.components;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Primary
@Profile("perfil2")
public class Perfil2 implements Perfiles{

    private String perfil = "perfil 2";
    
    @Override
    public String miFuncion() {
        return "Hola desde el "+this.perfil;
    }
}
