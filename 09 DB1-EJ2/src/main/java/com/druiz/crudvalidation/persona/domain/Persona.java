package com.druiz.crudvalidation.persona.domain;

import com.druiz.crudvalidation.persona.infrastructure.controller.dtos.PersonaInputDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "persona", uniqueConstraints = {@UniqueConstraint(columnNames = {"id_persona"})})
public class Persona implements Serializable {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String usuario;

    @Column(nullable = false)
    String password;

    @Column(nullable = false)
    String name;

    String surname;

    @Column(nullable = false)
    String companyMail;

    @Column(nullable = false)
    String personalMail;

    @Column(nullable = false)
    String city;

    @Column(nullable = false)
    boolean active;

    Date createdDate;

    String imageUrl;

    Date terminationDate;

    public Persona(){

    }

    public Persona(PersonaInputDto personaInputDTO){
        if (personaInputDTO == null)
            return;
        usuario = personaInputDTO.getUsuario();
        password = personaInputDTO.getPassword();
        name = personaInputDTO.getName();
        surname = personaInputDTO.getSurname();
        companyMail = personaInputDTO.getCompanyMail();
        personalMail = personaInputDTO.getPersonalMail();
        city = personaInputDTO.getCity();
        active = personaInputDTO.isActive();
        createdDate = personaInputDTO.getCreatedDate();
        imageUrl = personaInputDTO.getImageUrl();
        terminationDate = personaInputDTO.getTerminationDate();
    }

    public void updateEntity(PersonaInputDto personaInputDTO){
        setUsuario(personaInputDTO.getUsuario());
        setPassword(personaInputDTO.getPassword());
        setName(personaInputDTO.getName());
        setSurname(personaInputDTO.getSurname());
        setCompanyMail(personaInputDTO.getCompanyMail());
        setPersonalMail(personaInputDTO.getPersonalMail());
        setCity(personaInputDTO.getCity());
        setActive(personaInputDTO.isActive());
        setCreatedDate(personaInputDTO.getCreatedDate());
        setImageUrl(personaInputDTO.getImageUrl());
        setTerminationDate(personaInputDTO.getTerminationDate());
    }

    public void Validador() throws Exception {

        if (usuario == null || usuario.length() < 6 || usuario.length() > 10) {
            throw new RuntimeException("El usuario no puede ser null, y tiene que tener entre 6 y 10 caracteres");
        }
        if (password == null) {
            throw new RuntimeException("El password no puede ser null");
        }
        if (name == null) {
            throw new RuntimeException("El nombre no puede ser null");
        }
        if (companyMail == null) {
            throw new RuntimeException("El email no puede ser null");
        }
        if (personalMail == null) {
            throw new RuntimeException("El email no puede ser null");
        }
        if (city == null) {
            throw new RuntimeException("La ciudad no puede ser null");
        }
        if (createdDate == null) {
            throw new RuntimeException("La fecha no puede ser null");
        }

    }
}
