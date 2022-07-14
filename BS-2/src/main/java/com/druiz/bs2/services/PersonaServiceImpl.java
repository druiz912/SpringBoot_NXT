package com.druiz.bs2.services;

import com.druiz.bs2.models.Persona;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService {

    Persona p = new Persona();

    @Override
    public String getNombre() {
        return p.getNombre();
    }

    @Override
    public String getPoblacion() {
        return p.getPoblacion();
    }

    @Override
    public int getEdad() {
        return p.getEdad();
    }

    @Override
    public void setNombre(String nombre) {
        p.setNombre(nombre);
    }

    @Override
    public void setPoblacion(String poblacion) {
        p.setPoblacion(poblacion);
    }

    @Override
    public void setEdad(int edad) {
        p.setEdad(edad);
    }
}
