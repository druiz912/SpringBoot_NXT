package com.druiz.crud;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {
    List<Persona> listaPersonas = new ArrayList<>();

    @Override
    public void addPersona(Persona p) {
        listaPersonas.add(p);
    }

    @Override
    public List<Persona> getListaPersonas() {
        return listaPersonas;
    }

    public Persona getByID(int id) {
        return listaPersonas.get(id);
    }

    public Persona getByName(String nombre) {
        Persona persona = listaPersonas.stream().filter(per -> nombre.equals(per.getNombre())).findAny().orElse(null);
        return persona;
    }
}
