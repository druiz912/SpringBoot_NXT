package com.druiz.bosonit.docker.student.infrastructure.controller;


import com.druiz.bosonit.docker.student.application.port.StudentService;
import com.druiz.bosonit.docker.subject.infrastructure.dto.input.SubjectInputDtoAlone;
import com.druiz.bosonit.docker.student.infrastructure.controller.dto.input.StudentInputDto;
import com.druiz.bosonit.docker.student.infrastructure.controller.dto.output.StudentOutputDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {



    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public StudentOutputDto createStudent(@RequestBody StudentInputDto studentInputDto) throws Exception {
        return studentService.createStudent(studentInputDto);
    }

    //Añadir una asignatura o varias para un estudiante
    @PostMapping("subject/add/{id}")
    public StudentOutputDto addCourse(@PathVariable String id, @RequestBody SubjectInputDtoAlone listaAsignaturas){
        return studentService.addCourse(id,listaAsignaturas) ;
    }

    @GetMapping("id/{id}")
    public StudentOutputDto getStudentByID(
            @PathVariable(name = "id") String id,
            @RequestParam(defaultValue = "simple", required = false) String outputType) throws Exception {
        return studentService.getStudentById(id, outputType);

    }

    @GetMapping
    public List<StudentOutputDto> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("branch/{branch}")
    public List<StudentOutputDto> getStudentByBranch(@PathVariable(name = "branch") String branch) {
        return studentService.getStudentByBranch(branch);
    }

    @PutMapping("{id}")
    public StudentOutputDto updateByID(
            @RequestBody StudentInputDto studentInputDTO,
            @PathVariable(name = "id") String id) throws Exception {
        return studentService.updateStudentById(studentInputDTO, id);
    }

}
