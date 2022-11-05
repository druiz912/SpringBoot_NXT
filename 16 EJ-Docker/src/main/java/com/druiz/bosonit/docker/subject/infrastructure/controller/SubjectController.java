package com.druiz.bosonit.docker.subject.infrastructure.controller;

import com.druiz.bosonit.docker.subject.application.SubjectService;
import com.druiz.bosonit.docker.subject.infrastructure.dto.output.SubjectOutputDto;
import com.druiz.bosonit.docker.subject.infrastructure.dto.input.SubjectInputDto;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("subject")
public class SubjectController {

    private final SubjectService studentCourseService;

    public SubjectController(SubjectService studentCourseService) {
        this.studentCourseService = studentCourseService;
    }

    @PostMapping
    public SubjectOutputDto createSubject(@RequestBody SubjectInputDto subjectInputDto) throws Exception {
        return studentCourseService.addSubject(subjectInputDto);
    }


    @GetMapping("{id}")
    public SubjectOutputDto findById(@PathVariable(name = "id") String id) throws Exception {
        return studentCourseService.findById(id);
    }
    @PutMapping("{id}")
    public SubjectOutputDto updateSubject(@PathVariable(name = "id") String id, @RequestBody SubjectInputDto subjectInputDto) throws Exception {
        return studentCourseService.updateSubject(id, subjectInputDto);
    }
}
