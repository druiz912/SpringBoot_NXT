package com.druiz.bp1.content.infrastructure.repository;

import com.druiz.bp1.content.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PersonRepo extends JpaRepository<Person, Integer> {
    List<Person> findByName(String username);
}
