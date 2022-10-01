package com.druiz.docker.profesor.infrastucture.repo;

import com.druiz.docker.profesor.domain.ProfesorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProfesorRepo extends JpaRepository<ProfesorEntity, String> {
    List<ProfesorEntity> findByBranch(String branch);
}
