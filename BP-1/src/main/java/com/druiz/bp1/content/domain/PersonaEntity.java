package com.druiz.bp1.content.domain;

import com.druiz.bp1.content.infrastructure.controller.dto.input.PersonaInputDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "persona", uniqueConstraints = {@UniqueConstraint(columnNames = {"id_persona"})})
public class PersonaEntity implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id_persona;
    @Column(nullable = false)
    String usuario;

    @Column(nullable = false)
    String password;

    @Column(nullable = false)
    String name;

    private String surname;
    private String company_email;
    private String personal_email;
    private String email;
    private String city;
    private boolean active;
    private Date created_date;
    private String imagen_url;
    private Date termination_date;

    public PersonaEntity(PersonaInputDto persona) {
        if (persona == null) return;
        setId_persona(persona.getId_persona());
        setUsuario(persona.getUsuario());
        setName(persona.getName());
        setPassword(persona.getPassword());
        setSurname(persona.getSurname());
        setCompany_email(persona.getCompany_email());
        setPersonal_email(persona.getCompany_email());
        setCity(persona.getCity());
        setActive(persona.isActive());
        setCreated_date(persona.getCreated_date());
        setImagen_url(persona.getImagen_url());
        setTermination_date(persona.getTermination_date());
    }

    public void update(PersonaInputDto persona) {
        if (persona == null) return;
        id_persona = persona.getId_persona();
        usuario = persona.getUsuario();
        password = persona.getPassword();
        name = persona.getName();
        surname = persona.getSurname();
        company_email = persona.getCompany_email();
        personal_email = persona.getPersonal_email();
        city = persona.getCity();
        created_date = persona.getCreated_date();
        imagen_url = persona.getImagen_url();
        termination_date = persona.getTermination_date();
    }



}

