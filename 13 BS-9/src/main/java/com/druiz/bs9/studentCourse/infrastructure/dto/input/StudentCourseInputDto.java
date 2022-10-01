package com.druiz.bs9.studentCourse.infrastructure.dto.input;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class StudentCourseInputDto implements Serializable {
    private String id_asignatura;
    private String comment;
    private Date initial_date;
    private Date finish_date;
}
