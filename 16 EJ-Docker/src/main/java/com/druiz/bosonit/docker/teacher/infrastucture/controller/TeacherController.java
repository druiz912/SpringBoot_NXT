package com.druiz.bosonit.docker.teacher.infrastucture.controller;

import com.druiz.bosonit.docker.teacher.infrastucture.dto.input.TeacherInputDto;
import com.druiz.bosonit.docker.teacher.infrastucture.dto.output.TeacherOutputDto;
import com.druiz.bosonit.docker.teacher.application.port.TeacherService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public TeacherOutputDto addProfe (@RequestBody @Valid TeacherInputDto teacherInputDto) throws Exception {
        return teacherService.addTeacher(teacherInputDto);
    }

    @GetMapping("/id/{id}")
    public TeacherOutputDto getProfesorByID(@PathVariable(name = "id") String id) throws Exception {
        return teacherService.findTeacherById(id);
    }

    @GetMapping("all")
    public List<TeacherOutputDto> getAllProfesores() {
        return teacherService.findAllTeachers();
    }

    @GetMapping("branch/{branch}")
    public List<TeacherOutputDto> getProfesorByBranch(@PathVariable(name = "branch") String branch) {
        return teacherService.findTeacherByBranch(branch);
    }
    @PutMapping("{id}")
    public TeacherOutputDto updateProfesor(@PathVariable(name = "id") String id, @RequestBody TeacherInputDto teacherInputDTO) throws Exception {
        return teacherService.updateTeacher(id, teacherInputDTO);
    }

    @DeleteMapping("{id}")
    public void deleteProfesorByID(@PathVariable(name = "id") String id) throws Exception {
        teacherService.deleteTeacherById(id);
    }
}

