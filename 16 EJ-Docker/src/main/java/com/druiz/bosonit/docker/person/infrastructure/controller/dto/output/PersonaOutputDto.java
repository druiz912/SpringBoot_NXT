package com.druiz.bosonit.docker.person.infrastructure.controller.dto.output;

import com.druiz.bosonit.docker.person.domain.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaOutputDto implements Serializable {
    private String id;
    private String usuario;
    private String password;
    private String name;
    private String surname;
    private String companyMail;
    private String personalMail;
    private String email;
    private String city;
    private Boolean active;
    private Date createdDate;
    private String imageUrl;
    private Date terminationDate;

    public PersonaOutputDto(Person persona) { //Convertir
        if (persona == null) {
            System.out.println("La clase 'PersonaOutputDto' esta incompleta");
            return;
        } else {
            setId(persona.getId());
            setName(persona.getName());
            setPassword(persona.getPassword());
            setName(persona.getName());
            setSurname(persona.getSurname());
            setCompanyMail(persona.getCompanyMail());
            setPersonalMail(persona.getPersonalMail());
            setCity(persona.getCity());
            setActive(persona.getActive());
            setCreatedDate(persona.getCreatedDate());
            setImageUrl(persona.getImageUrl());
            setTerminationDate(persona.getTerminationDate());
        }
    }

}
