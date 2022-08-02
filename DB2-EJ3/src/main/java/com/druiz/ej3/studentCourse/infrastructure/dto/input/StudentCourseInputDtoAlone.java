package com.druiz.ej3.studentCourse.infrastructure.dto.input;

import com.druiz.ej3.studentCourse.domain.StudentCourseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class StudentCourseInputDtoAlone implements Serializable {
    private List<String> id = new ArrayList<>();
}
