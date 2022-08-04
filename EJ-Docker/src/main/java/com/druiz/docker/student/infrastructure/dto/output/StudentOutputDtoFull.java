package com.druiz.docker.student.infrastructure.dto.output;

import com.druiz.docker.persona.infrastructure.controller.dto.output.PersonaOutputDto;
import com.druiz.docker.student.domain.StudentEntity;
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
