package com.druiz.bosonit.db2.teacher.infrastucture.controller.dto.output;

import com.druiz.bosonit.db2.teacher.domain.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherOutputDtoSimple {

    String idPerson;
    int idTeacher;
    String comments;
    String branch;

    public TeacherOutputDtoSimple(Teacher profesor) {
    }
}
