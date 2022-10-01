package com.druiz.crudvalidation.persona.infrastructure.controller.dtos;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PersonaInputDto implements Serializable {
    private String usuario;
    private String password;
    private String name;
    private String surname;
    private String companyMail;
    private String personalMail;
    private String city;
    private boolean active;
    private Date createdDate;
    private String imageUrl;
    private Date terminationDate;

    public PersonaInputDto() {
    }
}
