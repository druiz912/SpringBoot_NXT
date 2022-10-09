package com.druiz.bosonit.db2.teacher.infrastucture.repo;

import com.druiz.bosonit.db2.teacher.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepo extends JpaRepository<Teacher, String> {
    List<Teacher> findByBranch(String branch);
}
