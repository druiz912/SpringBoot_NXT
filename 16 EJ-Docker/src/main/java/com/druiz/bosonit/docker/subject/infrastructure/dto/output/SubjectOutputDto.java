package com.druiz.bosonit.docker.subject.infrastructure.dto.output;

import com.druiz.bosonit.docker.subject.domain.Subject;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class SubjectOutputDto implements Serializable {

    private String id;
    private String comments;
    private LocalDate initialDate;
    private LocalDate finishDate;
    private List<String> subjects;

    public SubjectOutputDto(Subject subject) {
        if (subject == null) return;
        id = subject.getId();
        //estudios = subject.getAsignatura();
        comments = subject.getComments();
        initialDate = subject.getInitialDate();
        finishDate = subject.getFinishDate();
    }
}
