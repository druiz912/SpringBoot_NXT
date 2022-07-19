package com.example.bs41.controller;

import com.example.bs41.bean.MyConfigurationBean;
import com.example.bs41.components.Perfiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Value("${url}")
    private String url;

    @Value("${password}")
    private String password;

    @Autowired
    private MyConfigurationBean myConfigurationBean;

    @Autowired
    private Perfiles perfiles;

    @GetMapping("/parametros")
    public String getParameters() {
        return "El valor de url es: "+this.url+", el valor de password es: " +this.password;
    }

    @GetMapping("/miconfiguracion")
    public MyConfigurationBean getMiConfiguracion() {
        return this.myConfigurationBean;
    }

    @GetMapping("/perfil")
    public String getPerfil() {
        return this.perfiles.miFuncion();
    }
}
