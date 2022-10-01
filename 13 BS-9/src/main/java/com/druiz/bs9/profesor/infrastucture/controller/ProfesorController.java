package com.druiz.bs9.profesor.infrastucture.controller;

import com.druiz.bs9.profesor.application.port.IProfesorService;
import com.druiz.bs9.profesor.infrastucture.dto.output.ProfesorOutputDto;
import com.druiz.bs9.profesor.infrastucture.dto.input.ProfesorInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("profesor")
public class ProfesorController {

    @Autowired
    IProfesorService profeService;

    @PostMapping
    public ProfesorOutputDto addProfe (@RequestBody ProfesorInputDto profesorInputDto) throws Exception {
        return profeService.addProfe(profesorInputDto);
    }

    @GetMapping("/id/{id}")
    public ProfesorOutputDto getProfesorByID(@PathVariable(name = "id") String id) throws Exception {
        return profeService.getProfesorByID(id);
    }

    @GetMapping("all")
    public List<ProfesorOutputDto> getAllProfesores() {
        return profeService.getAllProfesores();
    }

    @GetMapping("branch/{branch}")
    public List<ProfesorOutputDto> getProfesorByBranch(@PathVariable(name = "branch") String branch) {
        return profeService.getProfesorByBranch(branch);
    }
    @PutMapping("{id}")
    public ProfesorOutputDto updateProfesor(@PathVariable(name = "id") String id, @RequestBody ProfesorInputDto profesorInputDTO) throws Exception {
        return profeService.updateProfesor(id, profesorInputDTO);
    }

    @DeleteMapping("{id}")
    public void deleteProfesorByID(@PathVariable(name = "id") String id) throws Exception {
        profeService.deleteProfesorByID(id);
    }
}

