package com.druiz.bosonit.docker.teacher.infrastucture.dto.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherInputDto implements Serializable {
    private String id;
    private String idPerson;
    private String comments;
    private String branch;
}
