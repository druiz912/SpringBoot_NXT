package com.druiz.ej3.student.infrastructure.dto.output;

import com.druiz.ej3.persona.infrastructure.dto.output.PersonaOutputDto;
import com.druiz.ej3.student.domain.StudentEntity;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
public class StudentOutputDtoFull extends StudentOutputDto implements Serializable {

    private PersonaOutputDto personaOutputDTO;

    public StudentOutputDtoFull(StudentEntity estudiante) {
        super(estudiante);
        if (estudiante == null) return;
        personaOutputDTO = new PersonaOutputDto(estudiante.getPersona());
    }
}
