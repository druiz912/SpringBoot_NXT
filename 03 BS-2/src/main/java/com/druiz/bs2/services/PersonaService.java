package com.druiz.bs2.services;

import com.druiz.bs2.models.Persona;

public interface PersonaService {
    String getNombre();

    String getPoblacion();

    int getEdad();

    void setNombre(String nombre);

    void setPoblacion(String poblacion);

    void setEdad(int edad);
}
