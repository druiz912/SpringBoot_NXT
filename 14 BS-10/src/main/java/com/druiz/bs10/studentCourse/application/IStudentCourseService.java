package com.druiz.bs10.studentCourse.application;

import com.druiz.bs10.studentCourse.infrastructure.dto.input.StudentCourseInputDto;
import com.druiz.bs10.studentCourse.infrastructure.dto.output.StudentCourseOutputDto;


public interface IStudentCourseService {
    StudentCourseOutputDto createCourse(StudentCourseInputDto studentCourseInputDto) throws Exception;

    void deleteById(String id) throws Exception;

    StudentCourseOutputDto findById(String id) throws Exception;

    StudentCourseOutputDto updateCourse(String id, StudentCourseInputDto studentCourseInputDto) throws Exception;
}

