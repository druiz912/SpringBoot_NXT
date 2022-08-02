package com.druiz.ej3.studentCourse.infrastructure.repo;


import com.druiz.ej3.studentCourse.domain.StudentCourseEntity;
import com.druiz.ej3.studentCourse.infrastructure.dto.output.StudentCourseOutputDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IStudentCourseRepo extends JpaRepository<StudentCourseEntity, String> {
}
