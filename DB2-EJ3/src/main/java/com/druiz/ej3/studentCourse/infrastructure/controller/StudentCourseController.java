package com.druiz.ej3.studentCourse.infrastructure.controller;

import com.druiz.ej3.studentCourse.application.IStudentCourseService;
import com.druiz.ej3.studentCourse.infrastructure.dto.input.StudentCourseInputDto;
import com.druiz.ej3.studentCourse.infrastructure.dto.output.StudentCourseOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("studentCourse")
public class StudentCourseController {

    @Autowired
    IStudentCourseService studentCourseService;

    @PostMapping
    public StudentCourseOutputDto createCourse(@RequestBody StudentCourseInputDto studentCourseInputDto) throws Exception {
        return studentCourseService.createCourse(studentCourseInputDto);
    }


    @GetMapping("{id}")
    public StudentCourseOutputDto findById(@PathVariable(name = "id") String id) throws Exception {
        return studentCourseService.findById(id);
    }
    @PutMapping("{id}")
    public StudentCourseOutputDto updateCourse(@PathVariable(name = "id") String id, @RequestBody StudentCourseInputDto studentCourseInputDto ) throws Exception {
        return studentCourseService.updateCourse(id,studentCourseInputDto);
    }
}
