package com.druiz.bs9.student.infrastructure.dto.output;

import com.druiz.bs9.persona.infrastructure.controller.dto.output.PersonaOutputDto;
import com.druiz.bs9.student.domain.StudentEntity;
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
