package com.druiz.ej3.student.infrastructure.dto.input;

import com.druiz.ej3.studentCourse.domain.StudentCourseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class StudentInputDto implements Serializable {
    private String id_student;
    private String id_persona;
    private String num_hours_week;
    private String comentarios;
    private String branch;
}
