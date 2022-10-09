package com.druiz.bosonit.db2.student.infrastructure.controller.dto.input;

import com.druiz.bosonit.db2.subject.domain.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentInputDto implements Serializable {

    private String idPerson;
    private String numHoursWeek;
    private String comments;
    private String branch;
    private List<Subject> estudios;
}
