package com.druiz.ej3.student.infrastructure.dto.output;

import com.druiz.ej3.student.domain.StudentEntity;
import com.druiz.ej3.studentCourse.domain.StudentCourseEntity;
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

    public StudentOutputDtoSimple(StudentEntity estudiante) {
        super(estudiante);if (estudiante == null) return;
    }

}
