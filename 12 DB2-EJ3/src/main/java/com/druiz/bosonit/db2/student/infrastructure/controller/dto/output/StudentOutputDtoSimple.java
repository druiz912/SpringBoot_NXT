package com.druiz.bosonit.db2.student.infrastructure.controller.dto.output;

import com.druiz.bosonit.db2.student.domain.Student;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
public class StudentOutputDtoSimple extends StudentOutputDto implements Serializable {

    String idPerson;
    String id;
    int numHoursWeek;
    String comments;
    String idProfesor;
    String branch;

    public StudentOutputDtoSimple(Student estudiante) {
        super(estudiante);if (estudiante == null) return;
    }

}
