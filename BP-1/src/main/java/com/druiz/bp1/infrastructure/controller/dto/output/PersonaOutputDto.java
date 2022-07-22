package com.druiz.bp1.infrastructure.controller.dto.output;
import com.druiz.bp1.domain.PersonaEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PersonaOutputDto implements Serializable {
    private int id_persona;
    private String usuario;
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

    public PersonaOutputDto(PersonaEntity personaEntity)
    {
        setId_persona(personaEntity.getId_persona());
        setUsuario(personaEntity.getUsuario());
        setPassword(personaEntity.getPassword());
        setName(personaEntity.getName());
        setSurname(personaEntity.getSurname());
        setCompany_email(personaEntity.getCompany_email());
        setPersonal_email(personaEntity.getPersonal_email());
        setCity(personaEntity.getCity());
        setActive(personaEntity.isActive());
        setCreated_date(personaEntity.getCreated_date());
        setImagen_url(personaEntity.getImagen_url());
        setTermination_date(personaEntity.getTermination_date());
    }


}
