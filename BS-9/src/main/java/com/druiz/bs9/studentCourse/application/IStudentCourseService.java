package com.druiz.bs9.studentCourse.application;

import com.druiz.bs9.studentCourse.infrastructure.dto.output.StudentCourseOutputDto;
import com.druiz.bs9.studentCourse.infrastructure.dto.input.StudentCourseInputDto;


public interface IStudentCourseService {
    StudentCourseOutputDto createCourse(StudentCourseInputDto studentCourseInputDto) throws Exception;

    void deleteById(String id) throws Exception;

    StudentCourseOutputDto findById(String id) throws Exception;

    StudentCourseOutputDto updateCourse(String id, StudentCourseInputDto studentCourseInputDto) throws Exception;
}

