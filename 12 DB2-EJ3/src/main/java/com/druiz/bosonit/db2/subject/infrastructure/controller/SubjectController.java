package com.druiz.bosonit.db2.subject.infrastructure.controller;

import com.druiz.bosonit.db2.subject.infrastructure.controller.dto.output.SubjectOutputDto;
import com.druiz.bosonit.db2.subject.application.SubjectService;
import com.druiz.bosonit.db2.subject.infrastructure.controller.dto.input.SubjectInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("subject")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @PostMapping
    public SubjectOutputDto createCourse(@RequestBody @Valid SubjectInputDto subjectInputDto) throws Exception {
        return subjectService.createCourse(subjectInputDto);
    }

    @GetMapping("{id}")
    public SubjectOutputDto findById(@PathVariable(name = "id") String id) throws Exception {
        return subjectService.findById(id);
    }
    @PutMapping("{id}")
    public SubjectOutputDto updateCourse(@PathVariable(name = "id") String id, @RequestBody @Valid SubjectInputDto subjectInputDto) throws Exception {
        return subjectService.updateCourse(id, subjectInputDto);
    }
}
