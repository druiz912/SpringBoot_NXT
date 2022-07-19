package com.druiz.bs3;

import com.druiz.bs3.models.Persona;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ClaseTerciaria{

    @Bean
    CommandLineRunner conParametros(Persona per){
        return args -> {
            System.out.println("Saludos desde la tercera clase!"+"\n"
                    +"Mi nombre es " + per.getNombre() +" "+per.getApellido() +
                    " tengo " + per.getEdad() + " anyos" + " y vivo en " + per.getPoblacion());
        };
    }
}
