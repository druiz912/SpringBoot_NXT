package com.druiz.bosonit.db2.person.infrastructure.controller.dto.output;


import com.druiz.bosonit.db2.person.domain.Person;
import com.druiz.bosonit.db2.teacher.infrastucture.controller.dto.output.TeacherOutputDto;
import lombok.Data;

import java.io.Serializable;

@Data
public class PersonProfesorOutputDto extends PersonOutputDto implements Serializable {
    private TeacherOutputDto teacherOutputDto;

    public PersonProfesorOutputDto(Person person) {
        super(person);
        if (person == null) return;
        teacherOutputDto = new TeacherOutputDto(person.getProfesor());
    }
}
