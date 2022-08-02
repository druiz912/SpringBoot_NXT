package com.druiz.ej3.student.infrastructure.repo;

import com.druiz.ej3.student.domain.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IStudentRepo extends JpaRepository<StudentEntity, String> {

    List<StudentEntity> findByBranch(String branch);
}
