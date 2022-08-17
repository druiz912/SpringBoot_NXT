package com.druiz.bosonit.sa2.storage.infrastructure.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import com.druiz.bosonit.sa2.storage.application.StorageService;
import com.druiz.bosonit.sa2.storage.infrastructure.exceptions.StorageFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class FileUploadController {

    @Autowired
    private StorageService storageService;
    /////////////////////////////////POST//////////////////////////////////////////////////////
    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "Se ha subido el siguiente archivo: " + file.getOriginalFilename() + "!");

        return "redirect:/"; //formulario
    }
    //Añadir archivo, definiendo el tipo de archivo
    @PostMapping("upload/{tipo}")
    public ResponseEntity<?> uploadFileType(@RequestParam("file") MultipartFile file,
                                            RedirectAttributes redirectAttributes,
                                            @PathVariable String tipo,
                                            @RequestParam(required = false) String categoria) {
        try {
            return storageService.uploadFileType(file,tipo,categoria);
        } catch (Exception e) {
            throw new RuntimeException("La extensión no es válida");
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////GET//////////////////////////////////////////////////////////
    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                        path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                                "serveFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList()));

        return "uploadForm"; //devuelve el html
    }
    //Leer el contenido del archivo indicándole el nombre del archivo
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
    //Buscar por nombre del archivo
    @GetMapping("file/nombre/{nombre}")
    public ResponseEntity<?> getFileByName(@PathVariable String nombre) {
        try {
            return storageService.getByName(nombre);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    //Buscar por id
    @GetMapping("file/id/{id}")
    public ResponseEntity<?> getFileById(@PathVariable int id) {
        try {
            return storageService.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    ////////////////////////////////////////////////////////////////////////

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<?> handleMaxSizeException(MaxUploadSizeExceededException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Comprueba el tamaño de los archivos");
    }

}