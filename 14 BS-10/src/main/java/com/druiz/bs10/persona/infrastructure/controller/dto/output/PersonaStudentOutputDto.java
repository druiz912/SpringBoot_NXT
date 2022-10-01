package com.druiz.bs10.persona.infrastructure.controller.dto.output;

import com.druiz.bs10.persona.domain.PersonaEntity;
import com.druiz.bs10.student.infrastructure.dto.output.StudentOutputDto;
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
