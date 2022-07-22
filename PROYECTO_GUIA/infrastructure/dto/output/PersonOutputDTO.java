package com.bosonit.exercises.bs8.infrastructure.dto.output;

import com.bosonit.exercises.bs8.domain.Person;
import lombok.Data;

import java.util.Date;

@Data
public class PersonOutputDTO {
    private int idPerson;
    private String user;
    private String password;
    private String name;
    private String surname;
    private String companyEmail;
    private String personalEmail;
    private String city;
    private boolean active;
    private Date createdDate;
    private String imageUrl;
    private Date terminationDate;

    public PersonOutputDTO(Person person) {
        setIdPerson(person.getIdPerson());
        setUser(person.getUser());
        setPassword(person.getPassword());
        setName(person.getName());
        setSurname(person.getSurname());
        setCompanyEmail(person.getCompanyEmail());
        setPersonalEmail(person.getPersonalEmail());
        setCity(person.getCity());
        setActive(person.isActive());
        setCreatedDate(person.getCreatedDate());
        setImageUrl(person.getImageUrl());
        setTerminationDate(person.getTerminationDate());
    }
}
