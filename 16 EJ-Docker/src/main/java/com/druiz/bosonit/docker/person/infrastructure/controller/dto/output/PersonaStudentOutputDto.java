package com.druiz.bosonit.docker.person.infrastructure.controller.dto.output;

import com.druiz.bosonit.docker.person.domain.Person;
import com.druiz.bosonit.docker.student.infrastructure.controller.dto.output.StudentOutputDto;
import lombok.Data;

import java.io.Serializable;


@Data
public class PersonaStudentOutputDto extends PersonaOutputDto implements Serializable {
    StudentOutputDto studentOutputDto;
    public PersonaStudentOutputDto(Person person) {
        super(person);
        if (person == null) return;
        studentOutputDto = new StudentOutputDto(person.getStudent());
    }

}
