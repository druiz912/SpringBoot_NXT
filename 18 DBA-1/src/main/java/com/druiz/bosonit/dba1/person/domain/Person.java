package com.druiz.bosonit.dba1.person.domain;

import javax.persistence.*;

import com.druiz.bosonit.dba1.person.infrastructure.controllers.dto.PersonInputDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="person")
@Data
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    int id;

    @Column
    String user;

    @Column
    String name;

    @Column
    String surname;

    @Column
    String address;

    @Column
    String email;

    @Column(name="created_date")
    LocalDate created;

    public void updateEntity(PersonInputDto dto){
        setUser(dto.user_persona());
        setName(dto.name());
        setSurname(dto.surname());
        setAddress(dto.address());
        setEmail(dto.email());
        setCreated(dto.created());
    }

    public Person(PersonInputDto personInputDTO){
        if (personInputDTO == null)
            return;
        user = personInputDTO.user_persona();
        name = personInputDTO.name();
        surname = personInputDTO.surname();
        address = personInputDTO.address();
        email = personInputDTO.email();
        created = personInputDTO.created();
    }
}
