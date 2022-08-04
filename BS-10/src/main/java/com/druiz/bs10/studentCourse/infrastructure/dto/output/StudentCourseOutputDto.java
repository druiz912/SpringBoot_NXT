package com.druiz.bs10.studentCourse.infrastructure.dto.output;

import com.druiz.bs10.studentCourse.domain.StudentCourseEntity;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class StudentCourseOutputDto implements Serializable {

    private String id_asignatura;
   // private String asignatura;
    private String comment;
    private Date initial_date;
    private Date finish_date;

    public StudentCourseOutputDto(StudentCourseEntity studentCourse) {
        if (studentCourse == null) return;
        id_asignatura = studentCourse.getId_asignatura();
        //asignatura = studentCourse.getAsignatura();
        comment = studentCourse.getComment();
        initial_date = studentCourse.getInitial_date();
        finish_date = studentCourse.getFinish_date();
    }
}
