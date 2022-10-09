package com.druiz.bosonit.db2.student.infrastructure.repo;

import com.druiz.bosonit.db2.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, String> {

    List<Student> findByBranch(String branch);
}
