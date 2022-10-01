package com.druiz.docker.studentCourse.infrastructure.dto.output;

import com.druiz.docker.studentCourse.domain.StudentCourseEntity;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class StudentCourseOutputDto implements Serializable {

    private String id_asignatura;
    private String comments;
    private Date initial_date;
    private Date finish_date;
    private List<String> estudios;

    public StudentCourseOutputDto(StudentCourseEntity studentCourse) {
        if (studentCourse == null) return;
        id_asignatura = studentCourse.getId_asignatura();
        //estudios = studentCourse.getAsignatura();
        comments = studentCourse.getComments();
        initial_date = studentCourse.getInitial_date();
        finish_date = studentCourse.getFinish_date();
    }
}
