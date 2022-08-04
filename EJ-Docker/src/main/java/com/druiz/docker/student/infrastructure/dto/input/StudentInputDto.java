package com.druiz.docker.student.infrastructure.dto.input;

import com.druiz.docker.studentCourse.domain.StudentCourseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class StudentInputDto implements Serializable {
    private String id_student;
    private String id_persona;
    private String num_hours_week;
    private String comments;
    private String branch;
    private List<StudentCourseEntity> estudios;
}
