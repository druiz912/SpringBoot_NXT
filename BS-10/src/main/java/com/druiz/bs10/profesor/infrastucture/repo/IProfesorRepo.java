package com.druiz.bs10.profesor.infrastucture.repo;

import com.druiz.bs10.profesor.domain.ProfesorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProfesorRepo extends JpaRepository<ProfesorEntity, String> {
    List<ProfesorEntity> findByBranch(String branch);
}
