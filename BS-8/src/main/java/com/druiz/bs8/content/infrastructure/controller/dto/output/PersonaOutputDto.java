package com.druiz.bs8.content.infrastructure.controller.dto.output;

import com.druiz.bs8.content.domain.PersonaEntity;
import com.sun.istack.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PersonaOutputDto implements Serializable {
    private int id_persona;
    private String usuario;
    @NotNull
    private String password;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String email;
    private String city;
    private boolean active;
    private Date created_date;
    private String imagen_url;
    private Date termination_date;

    public PersonaOutputDto(PersonaEntity persona) {
        if (persona == null) {
            System.out.println("La clase persona del output esta incompleta");
            return;
        }
        setId_persona(persona.getId_persona());
        setName(persona.getName());
        setPassword(persona.getPassword());
        setName(persona.getName());
        setSurname(persona.getSurname());
        setCompany_email(persona.getCompany_email());
        setPersonal_email(persona.getPersonal_email());
        setCity(persona.getCity());
        //setActive(persona.get);
        setCreated_date(persona.getCreated_date());
        setImagen_url(persona.getImagen_url());
        setTermination_date(persona.getTermination_date());
    }


}
