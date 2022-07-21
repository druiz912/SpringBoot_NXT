package com.druiz.crudvalidation.persona.dtos;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PersonaInputDto implements Serializable {
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

    public PersonaInputDto() {
    }
}
