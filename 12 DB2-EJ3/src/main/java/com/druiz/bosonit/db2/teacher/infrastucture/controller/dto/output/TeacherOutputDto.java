package com.druiz.bosonit.db2.teacher.infrastucture.controller.dto.output;

import com.druiz.bosonit.db2.teacher.domain.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherOutputDto implements Serializable {
    private String idTeacher;
    private String idPerson;
    private String comments;
    private String branch;

    public TeacherOutputDto(Teacher teacher) {
        if (teacher == null) return;
        idTeacher = teacher.getIdTeacher();
        idPerson = teacher.getPersona().getIdPerson();
        comments = teacher.getComments();
        branch = teacher.getBranch();
    }
}
