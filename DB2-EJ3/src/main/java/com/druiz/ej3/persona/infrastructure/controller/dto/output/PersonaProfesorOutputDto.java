package com.druiz.ej3.persona.infrastructure.controller.dto.output;


import com.druiz.ej3.persona.domain.PersonaEntity;
import com.druiz.ej3.profesor.infrastucture.dto.output.ProfesorOutputDto;
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
