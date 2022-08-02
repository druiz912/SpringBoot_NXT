package com.druiz.bs8.content.infrastructure.repository;

import com.druiz.bs8.content.domain.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface IPersonaRepoJPA extends JpaRepository<PersonaEntity, Integer> {
    List<PersonaEntity> findByName(String username);
}
