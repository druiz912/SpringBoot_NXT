package com.druiz.bs10.student.infrastructure.dto.output;

import com.druiz.bs10.student.domain.StudentEntity;
import com.druiz.bs10.persona.infrastructure.controller.dto.output.PersonaOutputDto;
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
