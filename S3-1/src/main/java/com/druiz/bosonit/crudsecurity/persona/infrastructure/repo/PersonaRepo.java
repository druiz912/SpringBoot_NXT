package com.druiz.bosonit.crudsecurity.persona.infrastructure.repo;

import com.druiz.bosonit.crudsecurity.persona.domain.PersonaEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface PersonaRepo extends CrudRepository<PersonaEntity, Integer> {
    /* Se devuelve la entidad, un dto será cambiado en el futuro una entidad no... Bueno sí, pero tú me entiendes*/
    List<PersonaEntity> findByName(String name);

    PersonaEntity findByUserPersona(String username);

    boolean existsByName(String name);
}

