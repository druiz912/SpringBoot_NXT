package com.druiz.bosonit.docker.subject.infrastructure.dto.input;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
public class SubjectInputDto implements Serializable {
    private String id;
    private String comment;
    private LocalDate initialDate;
    private LocalDate finishDate;
}
