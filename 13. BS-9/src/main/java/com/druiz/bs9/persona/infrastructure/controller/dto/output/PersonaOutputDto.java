package com.druiz.bs9.persona.infrastructure.controller.dto.output;

import com.druiz.bs9.persona.domain.PersonaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaOutputDto implements Serializable {
    private String id_persona;
    private String usuario;
    private String password;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String email;
    private String city;
    private Boolean active;
    private Date created_date;
    private String imagen_url;
    private Date termination_date;

    public PersonaOutputDto(PersonaEntity persona) { //Convertir
        if (persona == null) {
            System.out.println("La clase 'PersonaOutputDto' esta incompleta");
            return;
        } else {
            setId_persona(persona.getId_persona());
            setName(persona.getName());
            setPassword(persona.getPassword());
            setName(persona.getName());
            setSurname(persona.getSurname());
            setCompany_email(persona.getCompany_email());
            setPersonal_email(persona.getPersonal_email());
            setCity(persona.getCity());
            setActive(persona.getActive());
            setCreated_date(persona.getCreated_date());
            setImagen_url(persona.getImagen_url());
            setTermination_date(persona.getTermination_date());
        }
    }

}
