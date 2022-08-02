package com.druiz.ej3.student.infrastructure.dto.output;

import com.druiz.ej3.student.domain.StudentEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class StudentOutputDtoSimple extends StudentOutputDto implements Serializable {
    public StudentOutputDtoSimple(StudentEntity studentEntity) {
        super(studentEntity);
        if (studentEntity == null) return;
    }
}
