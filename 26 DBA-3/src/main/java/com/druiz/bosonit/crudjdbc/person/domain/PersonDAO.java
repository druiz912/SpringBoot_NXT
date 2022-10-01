package com.druiz.bosonit.crudjdbc.person.domain;

import java.util.List;
import java.util.Optional;

public interface PersonDAO {

    List<Person> allPerson();
    int insertPerson(Person person);
    int deletePerson(int id);
    Optional<Person> selectPersonById(int id);
    // TODO: Update

}
