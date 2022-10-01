package com.druiz.docker.student.infrastructure.dto.output;

import com.druiz.docker.studentCourse.domain.StudentCourseEntity;
import com.druiz.docker.studentCourse.infrastructure.dto.output.StudentCourseOutputDto;
import com.druiz.docker.student.domain.StudentEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class StudentOutputDto implements Serializable {
    private String id_student;
    private String id_persona;
    private List<StudentCourseOutputDto> estudios = new ArrayList<>();
    private int num_hours_week;
    private String comments;
    private String branch;

    public StudentOutputDto(StudentEntity estudiante) {
        if (estudiante == null) return;
        this.id_student = estudiante.getId_student();
        this.id_persona = estudiante.getPersona().getId_persona();
        if (estudiante.getEstudios() != null) {
            for (StudentCourseEntity studentCourse : estudiante.getEstudios()) { //Conversion de StudentEnt
                estudios.add(new StudentCourseOutputDto(studentCourse));
            }
            this.num_hours_week = estudiante.getNum_hours_week();
            this.comments = estudiante.getComments();
            this.branch = estudiante.getBranch();
        }


    }
}
