package com.druiz.bosonit.testing;


import com.druiz.bosonit.testing.persona.domain.PersonaEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
 class PersonaEntityTest {

    @Test
    void createPersona(){
        PersonaEntity p = new PersonaEntity
                (1,"Daniel","Ruiz","Huelva","12345679","druiz");
        System.out.println(p);
        Assertions.assertThat(p.getId()).isEqualTo(1);
        Assertions.assertThat(p.getFirstName()).isEqualTo("Daniel");
        Assertions.assertThat(p.getLastName()).isEqualTo("Ruiz");
        Assertions.assertThat(p.getPhone()).isEqualTo("12345679");
        Assertions.assertThat(p.getEmail()).isEqualTo("druiz");
    }
}
