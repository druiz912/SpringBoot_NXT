package com.druiz.bosonit.sa2.storage.application;

import com.druiz.bosonit.sa2.storage.domain.StorageFile;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {
    StorageFile saveData(MultipartFile file, String categoria);
    ResponseEntity<?> getByName(String nombre);
    ResponseEntity<?> findById(int id);
    ResponseEntity<?> uploadFileType(MultipartFile file, String tipo, String categoria) throws Exception;
    /////////////////////////
    void init();
    void store(MultipartFile file);
    Stream<Path> loadAll();
    Path load(String filename);
    Resource loadAsResource(String filename);
    void deleteAll();

}
