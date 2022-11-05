package com.druiz.bosonit.docker.person.infrastructure.controller.dto.output;


import com.druiz.bosonit.docker.person.domain.Person;
import com.druiz.bosonit.docker.teacher.infrastucture.dto.output.TeacherOutputDto;
import lombok.Data;

import java.io.Serializable;

@Data
public class PersonaProfesorOutputDto extends PersonaOutputDto implements Serializable {
    private TeacherOutputDto teacherOutputDto;

    public PersonaProfesorOutputDto(Person person) {
        super(person);
        if (person == null) return;
        teacherOutputDto = new TeacherOutputDto(person.getTeacher());
    }
}
