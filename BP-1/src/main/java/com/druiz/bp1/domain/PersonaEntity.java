package com.druiz.bp1.domain;

import com.druiz.bp1.infrastructure.controller.dto.input.PersonaInputDto;
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

    public PersonaEntity(PersonaInputDto persona) {
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
        if (company_email == null) {
            throw new RuntimeException("El email no puede ser null");
        }
        if (personal_email == null) {
            throw new RuntimeException("El email no puede ser null");
        }
        if (city == null) {
            throw new RuntimeException("La ciudad no puede ser null");
        }
        if (created_date == null) {
            throw new RuntimeException("La fecha no puede ser null");
        }

    }
}

