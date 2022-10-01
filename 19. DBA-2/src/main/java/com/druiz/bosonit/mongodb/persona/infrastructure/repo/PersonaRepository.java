package com.druiz.bosonit.mongodb.persona.infrastructure.repo;

import com.druiz.bosonit.mongodb.persona.domain.PersonaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PersonaRepository extends MongoRepository<PersonaEntity, Integer> {
    //MongoRepository --> equivalente a JPA
}
