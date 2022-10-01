package com.druiz.docker.student.infrastructure.repo;

import com.druiz.docker.student.domain.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IStudentRepo extends JpaRepository<StudentEntity, String> {

    List<StudentEntity> findByBranch(String branch);
}
