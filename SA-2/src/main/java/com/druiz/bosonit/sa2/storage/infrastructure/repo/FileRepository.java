package com.druiz.bosonit.sa2.storage.infrastructure.repo;

import com.druiz.bosonit.sa2.storage.domain.StorageFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<StorageFile, Integer> {
    @Query("SELECT p FROM StorageFile p where p.nombre = ?1")
    List<StorageFile> findByNombre(String nombre);
}
