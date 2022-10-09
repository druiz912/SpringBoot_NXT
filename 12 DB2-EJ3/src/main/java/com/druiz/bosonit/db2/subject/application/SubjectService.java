package com.druiz.bosonit.db2.subject.application;

import com.druiz.bosonit.db2.subject.infrastructure.controller.dto.input.SubjectInputDto;
import com.druiz.bosonit.db2.subject.infrastructure.controller.dto.output.SubjectOutputDto;


public interface SubjectService {
    SubjectOutputDto createCourse(SubjectInputDto subjectInputDto);

    void deleteById(String id) ;

    SubjectOutputDto findById(String id) ;

    SubjectOutputDto updateCourse(String id, SubjectInputDto subjectInputDto) ;
}

