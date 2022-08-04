package com.druiz.docker.persona.infrastructure.controller.dto.output;

import com.druiz.docker.student.infrastructure.dto.output.StudentOutputDto;
import com.druiz.docker.persona.domain.PersonaEntity;
import lombok.Data;

import java.io.Serializable;


@Data
public class PersonaStudentOutputDto extends PersonaOutputDto implements Serializable {
    StudentOutputDto studentOutputDto;
    public PersonaStudentOutputDto(PersonaEntity personaEntity) {
        super(personaEntity);
        if (personaEntity == null) return;
        studentOutputDto = new StudentOutputDto(personaEntity.getStudent());
    }

}
