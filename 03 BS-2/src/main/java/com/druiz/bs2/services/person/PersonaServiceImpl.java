package com.druiz.bs2.services.person;

import com.druiz.bs2.models.Persona;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService {

    Persona p = new Persona();


    @Override
    public int getEdad() {
        return p.getEdad();
    }

    @Override
    public void setNombre(String nombre) {
        p.setNombre(nombre);
    }

    @Override
    public void setCiudad(String ciudad) {
        p.setCiudad(ciudad);
    }

    @Override
    public void setEdad(int edad) {
        p.setEdad(edad);
    }
}
