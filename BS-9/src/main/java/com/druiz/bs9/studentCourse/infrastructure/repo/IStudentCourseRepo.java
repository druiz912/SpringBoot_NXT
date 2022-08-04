package com.druiz.bs9.studentCourse.infrastructure.repo;


import com.druiz.bs9.studentCourse.domain.StudentCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentCourseRepo extends JpaRepository<StudentCourseEntity, String> {
}
