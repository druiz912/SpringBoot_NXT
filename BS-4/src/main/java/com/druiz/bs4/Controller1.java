package com.druiz.bs4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@ComponentScan
public class Controller1 {
    @Autowired
    myProperties myproperties;

    @Bean
    public void init(){
        myproperties.toString();
    }

    @GetMapping("/valores")
    public String getProperties() {
        return "Valor de var1: " +myproperties.getVar1()+ " valor de my.var2 es: " + myproperties.getMyVar2();
    }

    @GetMapping("/var3")
    public String getVar3() {
        return "Var3: " + myproperties.getvar3();
    }
}
