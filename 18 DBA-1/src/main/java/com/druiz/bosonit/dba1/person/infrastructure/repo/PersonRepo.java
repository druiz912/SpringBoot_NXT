package com.druiz.bosonit.dba1.person.infrastructure.repo;

import com.druiz.bosonit.dba1.person.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepo extends JpaRepository<Person, Integer> {

    public List<Person> findByName(String name);
}

