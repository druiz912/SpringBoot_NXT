package com.druiz.bp1.content.domain;

import com.druiz.bp1.content.infrastructure.controller.dto.input.PersonInputDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "persona", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Person implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column(nullable = false)
    private String user;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String companyMail;
    private String personalMail;
    private String city;
    private boolean active;
    private Date createdDate;
    private String imageUrl;
    private Date terminationDate;

    public Person(PersonInputDto persona) {
        if (persona == null) return;
        setUser(persona.getUser());
        setName(persona.getName());
        setPassword(persona.getPassword());
        setSurname(persona.getSurname());
        setCompanyMail(persona.getCompanyMail());
        setPersonalMail(persona.getCompanyMail());
        setCity(persona.getCity());
        setActive(persona.isActive());
        setCreatedDate(persona.getCreatedDate());
        setImageUrl(persona.getImageUrl());
        setTerminationDate(persona.getTerminationDate());
    }

    public void update(PersonInputDto persona) {
        if (persona == null) return;
        user = persona.getUser();
        password = persona.getPassword();
        name = persona.getName();
        surname = persona.getSurname();
        companyMail = persona.getCompanyMail();
        personalMail = persona.getPersonalMail();
        city = persona.getCity();
        createdDate = persona.getCreatedDate();
        imageUrl = persona.getImageUrl();
        terminationDate = persona.getTerminationDate();
    }



}

