package com.druiz.bosonit.docker.student.infrastructure.controller.dto.output;

import com.druiz.bosonit.docker.student.domain.Student;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
public class StudentOutputDtoSimple extends StudentOutputDto implements Serializable {

    String id_persona;
    String id;
    int num_hours_week;
    String comments;
    String id_profesor;
    String branch;

    public StudentOutputDtoSimple(Student estudiante) {
        super(estudiante);if (estudiante == null) return;
    }

}
