package com.druiz.bosonit.db2.person.infrastructure.controller.dto.output;

import com.druiz.bosonit.db2.person.domain.Person;
import com.druiz.bosonit.db2.student.infrastructure.controller.dto.output.StudentOutputDto;
import lombok.Data;

import java.io.Serializable;


@Data
public class PersonStudentOutputDto extends PersonOutputDto implements Serializable {
    StudentOutputDto studentOutputDto;
    public PersonStudentOutputDto(Person person) {
        super(person);
        if (person == null) return;
        studentOutputDto = new StudentOutputDto(person.getStudent());
    }

}
