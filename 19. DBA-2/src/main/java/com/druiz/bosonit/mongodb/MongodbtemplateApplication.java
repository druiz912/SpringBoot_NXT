package com.druiz.bosonit.mongodb;

import com.druiz.bosonit.mongodb.persona.domain.PersonaEntity;
import com.druiz.bosonit.mongodb.persona.infrastructure.repo.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class MongodbtemplateApplication {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

	public static void main(String[] args) {SpringApplication.run(MongodbtemplateApplication.class, args);}

/* Persona de prueba al empezar el proyecto probando el servidor
-->Implements CommandLineRunner
	@Override
	public void run(String... args) throws Exception {

		PersonaEntity p1 = new PersonaEntity("Daniel","Ruiz","dani desde el run!",22);
		repo.save(p1);
		System.out.println("*****************************");
		List<PersonaEntity> listaPersonas = repo.findAll();
		for(PersonaEntity p: listaPersonas){
			System.out.println(p.toString());
		}
	}
*/
	}


