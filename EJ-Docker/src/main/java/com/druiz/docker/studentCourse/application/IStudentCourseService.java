package com.druiz.docker.studentCourse.application;

import com.druiz.docker.studentCourse.infrastructure.dto.output.StudentCourseOutputDto;
import com.druiz.docker.studentCourse.infrastructure.dto.input.StudentCourseInputDto;


public interface IStudentCourseService {
    StudentCourseOutputDto createCourse(StudentCourseInputDto studentCourseInputDto) throws Exception;

    void deleteById(String id) throws Exception;

    StudentCourseOutputDto findById(String id) throws Exception;

    StudentCourseOutputDto updateCourse(String id, StudentCourseInputDto studentCourseInputDto) throws Exception;
}

