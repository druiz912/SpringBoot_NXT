package com.druiz.bs3.models;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class Persona {
    private String nombre = "Daniel";
    private String apellido = "Ruiz";
    private int edad = 22;
    private String poblacion = "Jerez de la Frontera, Cadiz";

    @Bean
    public String getNombre() {return nombre;}
    @Bean
    public String getApellido() {return apellido;}

    @Bean
    public String getPoblacion(){return poblacion;}

    @Bean
    public int getEdad() {return edad;}
}