package com.druiz.bosonit.docker.subject.infrastructure.repo;


import com.druiz.bosonit.docker.subject.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepo extends JpaRepository<Subject, String> {
}
