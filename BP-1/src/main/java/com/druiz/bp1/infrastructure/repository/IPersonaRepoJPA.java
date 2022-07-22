package com.druiz.bp1.infrastructure.repository;

import com.druiz.bp1.domain.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface IPersonaRepoJPA extends JpaRepository<PersonaEntity, Integer> { List<PersonaEntity> findByName(String username);}
