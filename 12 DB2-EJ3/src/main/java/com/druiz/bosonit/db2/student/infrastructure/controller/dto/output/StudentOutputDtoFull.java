package com.druiz.bosonit.db2.student.infrastructure.controller.dto.output;

import com.druiz.bosonit.db2.person.infrastructure.controller.dto.output.PersonOutputDto;
import com.druiz.bosonit.db2.student.domain.Student;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
public class StudentOutputDtoFull extends StudentOutputDto implements Serializable {

    private PersonOutputDto personOutputDTO;

    public StudentOutputDtoFull(Student estudiante) {
        super(estudiante);
        if (estudiante == null) return;
        personOutputDTO = new PersonOutputDto(estudiante.getPersona());
    }
}
