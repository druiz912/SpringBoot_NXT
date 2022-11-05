package com.druiz.bosonit.docker.teacher.infrastucture.dto.output;

import com.druiz.bosonit.docker.teacher.domain.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorOutputDtoSimple {

    String idPerson;
    int idTeacher;
    String comments;
    String branch;

    public ProfesorOutputDtoSimple(Teacher profesor) {
    }
}
