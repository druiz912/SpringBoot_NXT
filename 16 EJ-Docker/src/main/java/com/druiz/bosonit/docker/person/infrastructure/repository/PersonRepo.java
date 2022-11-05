package com.druiz.bosonit.docker.person.infrastructure.repository;

import com.druiz.bosonit.docker.person.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface PersonRepo extends JpaRepository<Person, String> {
    List<Person> findByName(String username);
}
