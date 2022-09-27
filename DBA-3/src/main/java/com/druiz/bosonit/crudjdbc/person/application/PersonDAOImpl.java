package com.druiz.bosonit.crudjdbc.person.application;

import com.druiz.bosonit.crudjdbc.person.domain.Person;
import com.druiz.bosonit.crudjdbc.person.domain.PersonDAO;
import com.druiz.bosonit.crudjdbc.person.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonDAOImpl {


    private final PersonDAO personDAO;

    public PersonDAOImpl(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public List<Person> getPersons() {
        return personDAO.allPerson();
    }

    public void addPerson(Person person) {
        // TODO: check if person exists
        int result = personDAO.insertPerson(person);
        if (result != 1) {
            throw new IllegalStateException("Person can't insert");
        }
    }

    public void deletePerson(Integer id) {
        Optional<Person> person = personDAO.selectPersonById(id);
        person.ifPresentOrElse(persona -> {
            int result = personDAO.deletePerson(id);
            if (result != 1) {
                throw new IllegalStateException("Could not delete person");
            }
        }, () -> {
            throw new NotFoundException(String.format("Person with id %s not found", id));
        });
    }

    public Person getPerson(int id) {
        return personDAO.selectPersonById(id).orElse(null);
    }
}