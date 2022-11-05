package com.druiz.bosonit.docker.student.infrastructure.controller.dto.output;

import com.druiz.bosonit.docker.person.infrastructure.controller.dto.output.PersonaOutputDto;
import com.druiz.bosonit.docker.student.domain.Student;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
public class StudentOutputDtoFull extends StudentOutputDto implements Serializable {

    private PersonaOutputDto personaOutputDTO;

    public StudentOutputDtoFull(Student estudiante) {
        super(estudiante);
        if (estudiante == null) return;
        personaOutputDTO = new PersonaOutputDto(estudiante.getIdPerson());
    }
}
