package com.druiz.bs2.services.city;

import com.druiz.bs2.models.Ciudad;

import java.util.List;

public interface CiudadService {
    void addCiudad(Ciudad ciudad);
    List<Ciudad> getCiudades();
}
