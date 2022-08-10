package com.druiz.bosonit.dba1.persona.infrastructure.repo;

import com.druiz.bosonit.dba1.persona.domain.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.List;

public interface PersonaRepo extends JpaRepository<PersonaEntity, Integer> {

    public List<PersonaEntity> getData(HashMap<String, Object> conditions);
}

