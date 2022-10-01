package com.druiz.bs2.services;


import com.druiz.bs2.models.Ciudad;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CiudadServiceImpl implements CiudadService{
        List<Ciudad> listaCiudades= new ArrayList<>();

        @Override
        public void addCiudad(Ciudad ciudad)
        {
            listaCiudades.add(ciudad);
        }

        @Override
        public List<Ciudad> getCiudades()
        {
            return listaCiudades;
        }
    }
