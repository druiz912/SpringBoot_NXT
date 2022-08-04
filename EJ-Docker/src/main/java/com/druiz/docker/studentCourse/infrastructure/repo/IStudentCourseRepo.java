package com.druiz.docker.studentCourse.infrastructure.repo;


import com.druiz.docker.studentCourse.domain.StudentCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentCourseRepo extends JpaRepository<StudentCourseEntity, String> {
}
