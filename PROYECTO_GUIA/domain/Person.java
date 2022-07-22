package com.bosonit.exercises.bs8.domain;

import com.bosonit.exercises.bs8.infrastructure.dto.input.PersonInputDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idPerson;

    @Column(nullable = false)
    private String user;
    @Column(name="contrasena",nullable = false)
    @NotNull
    private String password;
    @Column
    @NotNull
    private String name;
    @Column
    private String surname;
    @Column
    @NotNull
    private String companyEmail;
    @Column
    @NotNull
    private String personalEmail;
    @Column
    @NotNull
    private String city;
    @Column
    @NotNull
    private boolean active;
    @Column
    @NotNull
    private Date createdDate;
    @Column
    private String imageUrl;
    @Column
    private Date terminationDate;

    public Person(PersonInputDTO personInputDTO) throws Exception {
        if (personInputDTO.getUser() != null
                && personInputDTO.getUser().length() >= 6
                && personInputDTO.getUser().length() <= 10) {
            setUser(personInputDTO.getUser());
        } else {
            throw new Exception("The user field must have between 6 and 10 characters.");
        }
        if (personInputDTO.getName() == null) {
            throw new Exception("The name field cannot be empty.");
        } else {
            setName(personInputDTO.getName());
        }
        if (personInputDTO.getPassword() == null) {
            throw new Exception("Password field cannot be empty.");
        } else {
            setPassword(personInputDTO.getPassword());
        }
        if (personInputDTO.getCompanyEmail() == null) {
            throw new Exception("The company email field cannot be empty.");
        } else {
            setCompanyEmail(personInputDTO.getCompanyEmail());
        }
        if (personInputDTO.getPersonalEmail() == null) {
            throw new Exception("The personal email field cannot be empty.");
        } else {
            setPersonalEmail(personInputDTO.getPersonalEmail());
        }
        if (personInputDTO.getCity() == null) {
            throw new Exception("The countryside of the city cannot be empty.");
        } else {
            setCity(personInputDTO.getCity());
        }
        setCreatedDate(new Date());
        setActive(true);
        setImageUrl(personInputDTO.getImageUrl());
        setSurname(personInputDTO.getSurname());
    }

    public void update(PersonInputDTO personInputDTO) throws Exception {
        if (personInputDTO.getUser() != null
                && personInputDTO.getUser().length() >= 6
                && personInputDTO.getUser().length() <= 10) {
            setUser(personInputDTO.getUser());
        }
        if (personInputDTO.getName() != null) {
            setName(personInputDTO.getName());
        }
        if (personInputDTO.getSurname() != null) {
            setSurname(personInputDTO.getSurname());
        }
        if (personInputDTO.getPassword() != null) {
            setPassword(personInputDTO.getPassword());
        }
        if (personInputDTO.getCompanyEmail() != null) {
            setCompanyEmail(personInputDTO.getCompanyEmail());
        }
        if (personInputDTO.getPersonalEmail() != null) {
            setPersonalEmail(personInputDTO.getPersonalEmail());
        }
        if (personInputDTO.getCity() != null) {
            setCity(personInputDTO.getCity());
        }
        if (personInputDTO.getImageUrl() != null) {
            setImageUrl(personInputDTO.getImageUrl());
        }
    }
}
