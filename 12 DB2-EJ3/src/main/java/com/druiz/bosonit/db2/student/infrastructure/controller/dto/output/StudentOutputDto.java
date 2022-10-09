package com.druiz.bosonit.db2.student.infrastructure.controller.dto.output;

import com.druiz.bosonit.db2.student.domain.Student;
import com.druiz.bosonit.db2.subject.domain.Subject;
import com.druiz.bosonit.db2.subject.infrastructure.controller.dto.output.SubjectOutputDto;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class StudentOutputDto implements Serializable {
    private String idStudent;
    private String idPerson;
    private List<SubjectOutputDto> asignaturas = new ArrayList<>();
    private int numHoursWeek;
    private String comments;
    private String branch;

    public StudentOutputDto(Student estudiante) {
        if (estudiante == null) return;
        this.idStudent = estudiante.getIdStudent();
        this.idPerson = estudiante.getPersona().getIdPerson();
        if (estudiante.getEstudios() != null) {
            for (Subject studentCourse : estudiante.getEstudios()) { //Conversion de StudentEnt
                asignaturas.add(new SubjectOutputDto(studentCourse));
            }
            this.numHoursWeek = estudiante.getNumHoursWeek();
            this.comments = estudiante.getComments();
            this.branch = estudiante.getBranch();
        }


    }
}
