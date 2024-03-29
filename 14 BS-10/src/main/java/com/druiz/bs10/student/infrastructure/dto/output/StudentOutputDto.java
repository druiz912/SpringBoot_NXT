package com.druiz.bs10.student.infrastructure.dto.output;

import com.druiz.bs10.studentCourse.domain.StudentCourseEntity;
import com.druiz.bs10.student.domain.StudentEntity;
import com.druiz.bs10.studentCourse.infrastructure.dto.output.StudentCourseOutputDto;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class StudentOutputDto implements Serializable {
    private String id_student;
    private String id_persona;
    private List<StudentCourseOutputDto> asignaturas = new ArrayList<>();
    private int num_hours_week;
    private String coments;
    private String branch;

    public StudentOutputDto(StudentEntity estudiante) {
        if (estudiante == null) return;
        this.id_student = estudiante.getId_student();
        this.id_persona = estudiante.getPersona().getId_persona();
        if (estudiante.getEstudios() != null) {
            for (StudentCourseEntity studentCourse : estudiante.getEstudios()) { //Conversion de StudentEnt
                asignaturas.add(new StudentCourseOutputDto(studentCourse));
            }
            this.num_hours_week = estudiante.getNum_hours_week();
            this.coments = estudiante.getComents();
            this.branch = estudiante.getBranch();
        }


    }
}
