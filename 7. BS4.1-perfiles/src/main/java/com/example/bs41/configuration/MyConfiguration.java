package com.example.bs41.configuration;

import com.example.bs41.bean.MyConfigurationBean;
import com.example.bs41.bean.MyConfigurationBeanImp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("miconfiguracion.properties")
public class MyConfiguration {

    @Value("${valor1}")
    private String valor1;

    @Value("${valor2}")
    private String valor2;

    @Bean
    public MyConfigurationBean myconfiguration(){
        return new MyConfigurationBeanImp(this.valor1, this.valor2);
    }

}
