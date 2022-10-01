package com.druiz.bosonit.testing;

import com.druiz.bosonit.testing.persona.domain.PersonaEntity;
import com.druiz.bosonit.testing.persona.infrastructure.PersonaRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
 class PersonaRepoTest {

    @Autowired
    PersonaRepo repo;

    @Test
    @Order(1)
    @Rollback(value = false)
     void testPostPersona() {
        PersonaEntity persona = new PersonaEntity
                ("Daniel","Ruiz","Jerez","12345678","correo@gmail.com");
        repo.save(persona);
        Assertions.assertThat(persona.getId()).isPositive();
    }

    @Test
    @Order(2)
     void testGetPersona() {
        PersonaEntity persona = repo.findById(1).get();
        System.out.println("id: " + persona.getId());
        Assertions.assertThat(persona.getId()).isEqualTo(1);
    }

    @Test
    @Order(3)
     void testGetListPersona() {
        List<PersonaEntity> personas = repo.findAll();
        Assertions.assertThat(personas.size()).isNotZero();
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    void testUpdatePersona() {
        PersonaEntity persona = repo.findById(1).get();
        persona.setEmail("daniel.ruiz@bosonit.com");
        PersonaEntity p = repo.save(persona);
        Assertions.assertThat(p.getEmail()).isEqualTo("daniel.ruiz@bosonit.com");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
     void testDeletePersonaByIds() {
        PersonaEntity persona = repo.findById(1).get();
        repo.deleteById(1);
        PersonaEntity personaNull = null;
        Optional<PersonaEntity> optionalPersona = repo.findById(1);
        //Comprobación si ha sido borrado
        if (optionalPersona.isPresent()) {
            personaNull = optionalPersona.get();
        }
        Assertions.assertThat(personaNull).isNull(); //si es null --> 200 OK
    }
}
