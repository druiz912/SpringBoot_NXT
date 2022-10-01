package com.druiz.bs2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.druiz.bs2.services.person.PersonaService;
import com.druiz.bs2.services.person.PersonaServiceImpl;
	@SpringBootApplication
	public class Bs2Application {
		public static void main(String[] args) {
			SpringApplication.run(Bs2Application.class, args);

		}

		@Bean
		@Qualifier("getDataPersona")
		public PersonaService getPersonaService() {
			return new PersonaServiceImpl();
		}

		@Bean
		@Qualifier("bean1")
		public PersonaService getPersonaServiceBean1() {
			PersonaService personaService = new PersonaServiceImpl();
			personaService.setNombre("bean1");
			return personaService;
		}

		@Bean
		@Qualifier("bean2")
		public PersonaService getPersonaServiceBean2() {
			PersonaService personaService = new PersonaServiceImpl();
			personaService.setNombre("bean2");
			return personaService;
		}

		@Bean
		@Qualifier("bean3")
		public PersonaService getPersonaServiceBean3() {
			PersonaService personaService = new PersonaServiceImpl();
			personaService.setNombre("bean3");
			return personaService;
		}

	}

