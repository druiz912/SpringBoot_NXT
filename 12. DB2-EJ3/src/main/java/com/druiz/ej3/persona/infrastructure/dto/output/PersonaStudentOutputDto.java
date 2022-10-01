package com.druiz.ej3.persona.infrastructure.dto.output;

import com.druiz.ej3.persona.domain.PersonaEntity;
import com.druiz.ej3.student.infrastructure.dto.output.StudentOutputDto;
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
