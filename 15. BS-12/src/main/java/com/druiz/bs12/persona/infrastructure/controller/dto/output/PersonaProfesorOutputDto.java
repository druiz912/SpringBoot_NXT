package com.druiz.bs12.persona.infrastructure.controller.dto.output;


import com.druiz.bs12.profesor.infrastucture.dto.output.ProfesorOutputDto;
import com.druiz.bs12.persona.domain.PersonaEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class PersonaProfesorOutputDto extends PersonaOutputDto implements Serializable {
    private ProfesorOutputDto profesorOutputDto;

    public PersonaProfesorOutputDto(PersonaEntity personaEntity) {
        super(personaEntity);
        if (personaEntity == null) return;
        profesorOutputDto = new ProfesorOutputDto(personaEntity.getProfesor());
    }
}
