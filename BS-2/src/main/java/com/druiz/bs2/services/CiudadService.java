package com.druiz.bs2.services;

import com.druiz.bs2.models.Ciudad;

import java.util.List;

public interface CiudadService {
    void addCiudad(Ciudad ciudad);
    List<Ciudad> getCiudades();
}
