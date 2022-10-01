package com.druiz.bosonit.crudsecurity;

import com.druiz.bosonit.crudsecurity.persona.domain.PersonaEntity;
import com.druiz.bosonit.crudsecurity.persona.infrastructure.dtos.PersonaInputDto;
import com.druiz.bosonit.crudsecurity.persona.infrastructure.repo.PersonaRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.List;

@SpringBootApplication
public class CrudSecurityApplication implements CommandLineRunner {
    @Autowired
    PersonaRepo repoPersona;

    private static final Logger LOGGER = LoggerFactory.getLogger(CrudSecurityApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CrudSecurityApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        PersonaInputDto dto = new PersonaInputDto();
        PersonaInputDto dto2 = new PersonaInputDto();
		dto.setUser("druiz912");
		dto.setPassword("ppppp1");
        dto.setName("Daniel");
        dto.setSurname("Ruiz");
        dto.setEmail("daniel.ruiz@bosonit.com");
        dto.setCity("Jerez");
        //
		dto2.setUser("Cris120");
		dto2.setPassword("kias1");
        dto2.setName("Cristian");
        dto2.setSurname("González");
        dto2.setEmail("cristian@bosonit.com");
        dto2.setCity("Madrid");
        PersonaEntity personaEntity1 = new PersonaEntity(dto);
        PersonaEntity personaEntity2 = new PersonaEntity(dto2);
        personaEntity1.setRol("ADMIN");
        personaEntity2.setRol("ADMIN");
        repoPersona.save(personaEntity1);
        repoPersona.save(personaEntity2);
        LOGGER.info("**************PERSONAS***************");
        List<PersonaEntity> listaPersonas = (List<PersonaEntity>) repoPersona.findAll();
        for (PersonaEntity p : listaPersonas) {
            LOGGER.info(p.toString());
        }
    }
}
