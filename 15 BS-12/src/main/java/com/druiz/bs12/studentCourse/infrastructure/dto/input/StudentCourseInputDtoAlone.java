package com.druiz.bs12.studentCourse.infrastructure.dto.input;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class StudentCourseInputDtoAlone implements Serializable {
    private List<String> id = new ArrayList<>();
}
