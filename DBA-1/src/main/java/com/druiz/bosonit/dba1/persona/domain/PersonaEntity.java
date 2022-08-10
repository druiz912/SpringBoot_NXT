package com.druiz.bosonit.dba1.persona.domain;

import javax.persistence.*;

import com.druiz.bosonit.dba1.persona.infrastructure.dto.PersonaInputDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="persona")
@Data
@NoArgsConstructor
public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    int id;

    @Column
    String user_persona;

    @Column
    String name;

    @Column
    String surname;

    @Column
    String address;

    @Column
    String email;

    @Column(name="created_date")
    @Temporal(TemporalType.DATE)
    Date created;

    public void updateEntity(PersonaInputDto personaInputDTO){
        setUser_persona(personaInputDTO.user_persona());
        setName(personaInputDTO.name());
        setSurname(personaInputDTO.surname());
        setAddress(personaInputDTO.address());
        setEmail(personaInputDTO.email());
        setCreated(personaInputDTO.created());
    }

    public PersonaEntity(PersonaInputDto personaInputDTO){
        if (personaInputDTO == null)
            return;
        user_persona = personaInputDTO.user_persona();
        name = personaInputDTO.name();
        surname = personaInputDTO.surname();
        address = personaInputDTO.address();
        email = personaInputDTO.email();
        created = personaInputDTO.created();
    }
}
