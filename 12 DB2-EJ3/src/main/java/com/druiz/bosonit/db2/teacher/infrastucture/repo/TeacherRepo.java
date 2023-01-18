package com.druiz.bosonit.db2.teacher.infrastucture.repo;

import com.druiz.bosonit.db2.teacher.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherRepo extends JpaRepository<Teacher, String> {
    List<Teacher> findByBranch(String branch);

    @Query(value = "SELECT * FROM TEACHERS s WHERE s.PERSONID= ?", nativeQuery = true)
    public TeacherEntity getPersonQuery(Integer personID);

}
