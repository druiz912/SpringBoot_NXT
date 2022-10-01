package com.druiz.bs12.profesor.infrastucture.repo;

import com.druiz.bs12.profesor.domain.ProfesorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProfesorRepo extends JpaRepository<ProfesorEntity, String> {
    List<ProfesorEntity> findByBranch(String branch);
}
