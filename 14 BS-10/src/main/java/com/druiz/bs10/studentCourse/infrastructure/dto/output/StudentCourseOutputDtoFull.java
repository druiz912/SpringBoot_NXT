package com.druiz.bs10.studentCourse.infrastructure.dto.output;

import com.druiz.bs10.profesor.domain.ProfesorEntity;
import com.druiz.bs10.student.domain.StudentEntity;
import lombok.Data;

@Data
public class StudentCourseOutputDtoFull extends StudentCourseOutputDto {

    private ProfesorEntity profesor;
    private StudentEntity student;

   //

}
