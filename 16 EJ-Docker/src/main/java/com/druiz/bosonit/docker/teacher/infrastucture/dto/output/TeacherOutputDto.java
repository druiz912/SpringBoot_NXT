package com.druiz.bosonit.docker.teacher.infrastucture.dto.output;

import com.druiz.bosonit.docker.teacher.domain.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherOutputDto implements Serializable {
    private String id;
    private String idPerson;
    private String comentarios;
    private String branch;

    public TeacherOutputDto(Teacher teacher) {
        if (teacher == null) return;
        id = teacher.getId();
        idPerson = teacher.getPerson().getId();
        comentarios = teacher.getComments();
        branch = teacher.getBranch();
    }
}
