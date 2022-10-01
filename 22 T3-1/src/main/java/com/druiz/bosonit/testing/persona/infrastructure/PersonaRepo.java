package com.druiz.bosonit.testing.persona.infrastructure;


import com.druiz.bosonit.testing.persona.domain.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonaRepo extends JpaRepository<PersonaEntity, Integer> {
}
