package com.druiz.bosonit.db2.subject.infrastructure.controller.dto.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectInputDto implements Serializable {
    private String comment;
    private Date initialDate;
    private Date finishDate;
}
