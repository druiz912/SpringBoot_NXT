package com.druiz.docker.persona.infrastructure.repository;

import com.druiz.docker.persona.domain.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface IPersonaRepoJPA extends JpaRepository<PersonaEntity, String> {
    List<PersonaEntity> findByName(String username);
}
