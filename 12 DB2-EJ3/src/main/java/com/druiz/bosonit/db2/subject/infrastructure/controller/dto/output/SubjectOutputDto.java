package com.druiz.bosonit.db2.subject.infrastructure.controller.dto.output;

import com.druiz.bosonit.db2.subject.domain.Subject;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class SubjectOutputDto implements Serializable {

    private String idSubject;
    private String comment;
    private Date initialDate;
    private Date finishDate;

    public SubjectOutputDto(Subject studentCourse) {
        if (studentCourse == null) return;
        idSubject = studentCourse.getIdSubject();
        comment = studentCourse.getComment();
        initialDate = studentCourse.getInitialDate();
        finishDate = studentCourse.getFinishDate();
    }
}
