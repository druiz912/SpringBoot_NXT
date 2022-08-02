package com.druiz.crud;

import java.util.List;

public interface PersonaService {


    void addPersona(Persona p);

    List<Persona> getListaPersonas();

    Persona getByID(int id);

    Persona getByName(String nombre);

}
