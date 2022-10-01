package com.druiz.bs8.content.domain;

import com.druiz.bs8.content.infrastructure.controller.dto.input.PersonaInputDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "persona", uniqueConstraints = {@UniqueConstraint(columnNames = {"id_persona"})})
public class PersonaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Persona")
    private Integer id_persona;

    @Size(min = 6, max = 10)
    @Column(name = "Usuario", nullable = false)
    private String usuario;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Surname", nullable = false)
    private String surname;
    @Column(name = "Company_email", nullable = false)
    private String company_email;

    @Column(name = "Personal_email", nullable = false)
    private String personal_email;

    @Column(name = "Ciudad", nullable = false)
    private String city;

    @Column(name = "Activo", nullable = false)
    private Boolean active;

    @Column(name = "Created_Date", nullable = false)
    private Date created_date;

    @Column(name = "Imagen_URL")
    private String imagen_url;

    @Column(name = "Date")
    private Date termination_date;

    public PersonaEntity(PersonaInputDto pInputDto) throws Exception {
        if (pInputDto.getUsuario() != null
                && pInputDto.getUsuario().length() >= 6
                && pInputDto.getUsuario().length() <= 10) {
            setUsuario(pInputDto.getUsuario());
        } else {
            throw new Exception("El usuario debe tener entre 6 y 10 caracteres.");
        }
        if (pInputDto.getName() == null) {
            throw new Exception("The name field cannot be empty.");
        } else {
            setName(pInputDto.getName());
        }
        if (pInputDto.getPassword() == null) {
            throw new Exception("Password field cannot be empty.");
        } else {
            setPassword(pInputDto.getPassword());
        }
        if (pInputDto.getCompany_email() == null) {
            throw new Exception("The company email field cannot be empty.");
        } else {
            setCompany_email(pInputDto.getCompany_email());
        }
        if (pInputDto.getPersonal_email() == null) {
            throw new Exception("The personal email field cannot be empty.");
        } else {
            setPersonal_email(pInputDto.getPersonal_email());
        }
        if (pInputDto.getCity() == null) {
            throw new Exception("The countryside of the city cannot be empty.");
        } else {
            setCity(pInputDto.getCity());
        }
        setCreated_date(new Date());
        setActive(true);
        setImagen_url(pInputDto.getImagen_url());
        setSurname(pInputDto.getSurname());
    }

    public void update(PersonaInputDto pInputDto) throws Exception {
        if (pInputDto.getUsuario() != null
                && pInputDto.getUsuario().length() >= 6
                && pInputDto.getUsuario().length() <= 10) {
            setUsuario(pInputDto.getUsuario());
        }
        if (pInputDto.getName() != null) {
            setName(pInputDto.getName());
        }
        if (pInputDto.getSurname() != null) {
            setSurname(pInputDto.getSurname());
        }
        if (pInputDto.getPassword() != null) {
            setPassword(pInputDto.getPassword());
        }
        if (pInputDto.getCompany_email() != null) {
            setCompany_email(pInputDto.getCompany_email());
        }
        if (pInputDto.getPersonal_email() != null) {
            setPersonal_email(pInputDto.getPersonal_email());
        }
        if (pInputDto.getCity() != null) {
            setCity(pInputDto.getCity());
        }
        if (pInputDto.getImagen_url() != null) {
            setImagen_url(pInputDto.getImagen_url());
        }
    }
}

