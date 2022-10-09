package com.druiz.bosonit.db2.teacher.infrastucture.controller;

import com.druiz.bosonit.db2.teacher.application.port.TeacherService;
import com.druiz.bosonit.db2.teacher.infrastucture.controller.dto.input.TeacherInputDto;
import com.druiz.bosonit.db2.teacher.infrastucture.controller.dto.output.TeacherOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @PostMapping
    public TeacherOutputDto addProfe (@RequestBody @Valid TeacherInputDto teacherInputDto) throws Exception {
        return teacherService.addProfe(teacherInputDto);
    }

    @GetMapping("/id/{id}")
    public TeacherOutputDto getProfesorByID(@PathVariable(name = "id") String id) throws Exception {
        return teacherService.getProfesorByID(id);
    }

    @GetMapping("all")
    public List<TeacherOutputDto> getAllProfesores() {
        return teacherService.getAllProfesores();
    }

    @GetMapping("branch/{branch}")
    public List<TeacherOutputDto> getProfesorByBranch(@PathVariable(name = "branch") String branch) {
        return teacherService.getProfesorByBranch(branch);
    }
    @PutMapping("{id}")
    public TeacherOutputDto updateProfesor(@PathVariable(name = "id") String id, @RequestBody @Valid TeacherInputDto teacherInputDTO) throws Exception {
        return teacherService.updateProfesor(id, teacherInputDTO);
    }

    @DeleteMapping("{id}")
    public void deleteProfesorByID(@PathVariable(name = "id") String id) throws Exception {
        teacherService.deleteProfesorByID(id);
    }
}

