package com.druiz.bosonit.mongodb.persona.domain;

import com.druiz.bosonit.mongodb.persona.infrastructure.dto.input.PersonaInputDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "personas")
public class PersonaEntity {

    @Id
    @NotNull
    private int id;
    private String name;
    private String surname;
    private String address;
    //@Field("edad")
    private int age;

    public PersonaEntity(PersonaInputDto pInputDto){
        if (pInputDto == null)
            return;
        id = pInputDto.id();
        name = pInputDto.name();
        surname = pInputDto.surname();
        address= pInputDto.address();
        age = pInputDto.age();
    }

}
