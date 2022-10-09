package com.druiz.bosonit.db2.teacher.infrastucture.controller.dto.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherInputDto implements Serializable {
    private String idPerson;
    private String comentarios;
    private String branch;
}
