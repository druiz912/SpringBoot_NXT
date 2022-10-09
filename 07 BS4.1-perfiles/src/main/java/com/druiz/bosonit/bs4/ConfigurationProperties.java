package com.druiz.bosonit.bs4;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("myconfiguration.properties")
public class ConfigurationProperties {

    @Value("${valor1}")
    private String valor1;

    @Value("${valor2}")
    private String valor2;

   public String getValor2(){
       return valor2;
   }

   public String getValor1(){
       return valor1;
   }

}
