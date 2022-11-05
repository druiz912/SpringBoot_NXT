package com.druiz.bosonit.docker.student.infrastructure.controller.dto.input;

import com.druiz.bosonit.docker.subject.domain.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInputDto {
    private String id;
    private String idPerson;
    private String numHoursWeek;
    private String comments;
    private String branch;
    private List<Subject> subjects;
}
