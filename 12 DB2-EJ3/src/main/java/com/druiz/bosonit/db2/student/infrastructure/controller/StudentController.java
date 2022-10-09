package com.druiz.bosonit.db2.student.infrastructure.controller;


import com.druiz.bosonit.db2.student.application.port.StudentService;
import com.druiz.bosonit.db2.subject.infrastructure.controller.dto.input.SubjectInputDtoAlone;
import com.druiz.bosonit.db2.student.infrastructure.controller.dto.input.StudentInputDto;
import com.druiz.bosonit.db2.student.infrastructure.controller.dto.output.StudentOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping
    public StudentOutputDto createStudent(@RequestBody @Valid StudentInputDto studentInputDto) throws Exception {
        return studentService.createStudent(studentInputDto);
    }

    //Añadir una asignatura o varias para un estudiante
    @PostMapping("course/add/{id}")
    public StudentOutputDto addCourse(@PathVariable String id, @RequestBody @Valid SubjectInputDtoAlone listaAsignaturas){
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
