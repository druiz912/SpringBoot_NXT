package com.druiz.bs2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.druiz.bs2.services.PersonaService;
import com.druiz.bs2.services.PersonaServiceImpl;
	@SpringBootApplication
	public class Bs2Application {
		public static void main(String[] args) {
			SpringApplication.run(Bs2Application.class, args);

		}

		@Bean
		@Qualifier("getDataPersona")
		public PersonaService getPersonaService() {
			PersonaService pService = new PersonaServiceImpl();
			return pService;
		}

		@Bean
		@Qualifier("b1Qualifier")
		public PersonaService getPersonaServiceBean1() {
			PersonaService pService_1 = new PersonaServiceImpl();
			pService_1.setNombre("bean1");
			return pService_1;
		}

		@Bean
		@Qualifier("b2Qualifier")
		public PersonaService getPersonaServiceBean2() {
			PersonaService pService_2 = new PersonaServiceImpl();
			pService_2.setNombre("bean2");
			return pService_2;
		}

		@Bean
		@Qualifier("b3Qualifier")
		public PersonaService getPersonaServiceBean3() {
			PersonaService pService_3 = new PersonaServiceImpl();
			pService_3.setNombre("bean3");
			return pService_3;
		}

		@Bean
		@Qualifier("b4Qualifier")
		public PersonaService getPersonaServiceBean4() {
			PersonaService pService_4 = new PersonaServiceImpl();
			pService_4.setNombre("Parámetro incorrecto");
			return pService_4;
		}
	}

