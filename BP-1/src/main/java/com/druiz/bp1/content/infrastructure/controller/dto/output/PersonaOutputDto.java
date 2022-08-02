package com.druiz.bp1.content.infrastructure.controller.dto.output;
import com.druiz.bp1.content.domain.PersonaEntity;
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

    public PersonaOutputDto(PersonaEntity persona)
    {
        this.id_persona = persona.getId_persona();
        this.usuario = persona.getUsuario();
        this.name = persona.getName();
        this.surname = persona.getSurname();
        this.company_email = persona.getCompany_email();
        this.personal_email = persona.getPersonal_email();
        this.city = persona.getCity();
        this.active = persona.isActive();
        this.created_date = persona.getCreated_date();
        this.imagen_url = persona.getImagen_url();
        this.termination_date = persona.getTermination_date();
    }


}
