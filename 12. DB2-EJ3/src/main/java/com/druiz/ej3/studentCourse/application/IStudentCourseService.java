package com.druiz.ej3.studentCourse.application;

import com.druiz.ej3.studentCourse.infrastructure.dto.input.StudentCourseInputDto;
import com.druiz.ej3.studentCourse.infrastructure.dto.output.StudentCourseOutputDto;


public interface IStudentCourseService {
    StudentCourseOutputDto createCourse(StudentCourseInputDto studentCourseInputDto) throws Exception;

    void deleteById(String id) throws Exception;

    StudentCourseOutputDto findById(String id) throws Exception;

    StudentCourseOutputDto updateCourse(String id, StudentCourseInputDto studentCourseInputDto) throws Exception;
}

