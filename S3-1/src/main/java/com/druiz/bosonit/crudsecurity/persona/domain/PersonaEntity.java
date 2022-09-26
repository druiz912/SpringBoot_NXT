package com.druiz.bosonit.crudsecurity.persona.domain;

import com.druiz.bosonit.crudsecurity.persona.infrastructure.dtos.PersonaInputDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PERSONAS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String userPersona;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private String surname;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String rol;
    private Date createdDate;

    private Date lastModified;

    public PersonaEntity(PersonaInputDto pInputDto) {
        if (pInputDto == null) return;
        userPersona = pInputDto.getUser();
        password = pInputDto.getPassword();
        name = pInputDto.getName();
        surname = pInputDto.getSurname();
        email = pInputDto.getEmail();
        city = pInputDto.getCity();
        createdDate = new Date();
        rol = "USER";
    }

    public void updateEntity(PersonaInputDto pInputDto) {
        setUserPersona(pInputDto.getUser());
        setPassword(pInputDto.getPassword());
        setName(pInputDto.getName());
        setSurname(pInputDto.getSurname());
        setEmail(pInputDto.getEmail());
        setCity(pInputDto.getCity());
        setLastModified(new Date());
    }

}
