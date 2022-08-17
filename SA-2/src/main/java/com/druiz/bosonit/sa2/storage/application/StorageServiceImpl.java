package com.druiz.bosonit.sa2.storage.application;

import com.druiz.bosonit.sa2.storage.domain.StorageFile;
import com.druiz.bosonit.sa2.storage.infrastructure.exceptions.StorageException;
import com.druiz.bosonit.sa2.storage.infrastructure.exceptions.StorageFileNotFoundException;
import com.druiz.bosonit.sa2.storage.infrastructure.repo.FileRepository;
import com.druiz.bosonit.sa2.storage.properties.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.stream.Stream;

@Service
public class StorageServiceImpl implements StorageService {
    //Variable global para la ruta de la carpeta
    private final Path rootLocation;

    @Autowired
    public StorageServiceImpl(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }
    @Autowired
    FileRepository fileRepo;

    @Override
    public StorageFile saveData(MultipartFile file, String categoria) {
        StorageFile archivo = new StorageFile(); //Entidad con los campos id,nombre,fechaSubida,categoria, url
        archivo.setCategoria(categoria);
        archivo.setNombre(file.getOriginalFilename());
        archivo.setFechaSubida(new Date());
        //Asignacion de url para guardar el archivo//
        archivo.setUrl(this.rootLocation.resolve(file.getOriginalFilename()).toString());
        Path test = this.rootLocation.resolve(file.getOriginalFilename());
        try {
            Resource resource = new UrlResource(test.toUri());
            System.out.println(resource+ " " +"¿El archivo existe?" + resource.exists());
            if(resource.exists() || resource.isReadable()){
                System.out.println("El archivo existe y es legible");
                archivo.setUrl(resource.toString());
            }else{
                System.out.println("El "+ archivo.getNombre() + " no ha podido ser guardado, no existe o es ilegible");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        archivo.setId(fileRepo.save(archivo).getId());
        return archivo;
    }

    @Override
    public ResponseEntity<?> getByName(String nombre) {
        try {
            return new ResponseEntity<>(fileRepo.findByNombre(nombre), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> findById(int id) {
        try {
            return new ResponseEntity<>(fileRepo.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> uploadFileType(MultipartFile file, String tipo, String categoria) {
        String nombreArchivo = file.getOriginalFilename();
        String[] trozos = nombreArchivo.split("\\.");
        //Comprobaciones
        System.out.println(file.getOriginalFilename().toString());
        System.out.println(trozos);
        //
        try {
            if(trozos[1].equals(tipo)) {
                return new ResponseEntity<>(this.saveData(file,categoria), HttpStatus.OK);
            }
            else {
                throw new Exception("La extensión no coincide");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    ////////////////////////////////////////////////////////////////
    @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException
                        ("El siguiente archivo no ha sido guardado: "+ file.getOriginalFilename() + " porqué está vacío");
            }
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
        } catch (IOException e) {
            throw new StorageException("No se pudo almacenar el archivo" + file.getOriginalFilename(), e.getCause());
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        } catch (IOException e) {
            throw new StorageException("Error al leer los archivos almacenados", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException("\n" +
                        "No se pudo leer el archivo: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("\n" +
                    "No se pudo leer el archivo: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }


    @Override
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new StorageException("No se ha inicializado storage", e.getCause());
        }
    }
}
