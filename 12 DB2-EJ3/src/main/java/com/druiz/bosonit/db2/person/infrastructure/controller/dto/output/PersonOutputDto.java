package com.druiz.bosonit.db2.person.infrastructure.controller.dto.output;

import com.druiz.bosonit.db2.person.domain.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonOutputDto implements Serializable {
    private String idPerson;
    private String user;
    private String password;
    private String name;
    private String surname;
    private String companyMail;
    private String personalMail;
    private String city;
    private Boolean active;
    private Date createdDate;
    private String imagenUrl;
    private Date terminationDate;

    public PersonOutputDto(Person persona) { //Convertir
        if (persona == null) {
            System.err.println("La clase 'PersonaOutputDto' esta incompleta");
        } else {
            setIdPerson(persona.getIdPerson());
            setName(persona.getName());
            setPassword(persona.getPassword());
            setName(persona.getName());
            setSurname(persona.getSurname());
            setCompanyMail(persona.getCompanyMail());
            setPersonalMail(persona.getPersonalMail());
            setCity(persona.getCity());
            setActive(persona.getActive());
            setCreatedDate(persona.getCreatedDate());
            setImagenUrl(persona.getImagenUrl());
            setTerminationDate(persona.getTerminationDate());
        }
    }

}
