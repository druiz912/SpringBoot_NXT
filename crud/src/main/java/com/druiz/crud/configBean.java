package com.druiz.crud;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class configBean {
    @Bean
    @Qualifier("bean1")
    public PersonaService getBean() {
        PersonaService pService = new PersonaServiceImpl();
        return pService;
    }
}
