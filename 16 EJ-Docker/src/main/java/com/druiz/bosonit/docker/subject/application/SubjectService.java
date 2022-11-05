package com.druiz.bosonit.docker.subject.application;

import com.druiz.bosonit.docker.subject.infrastructure.dto.output.SubjectOutputDto;
import com.druiz.bosonit.docker.subject.infrastructure.dto.input.SubjectInputDto;
import com.druiz.bosonit.docker.utils.exceptions.NotFoundException;


public interface SubjectService {
    SubjectOutputDto addSubject(SubjectInputDto dto);

    String deleteById(String id);

    SubjectOutputDto findById(String id) throws NotFoundException;

    SubjectOutputDto updateSubject(String id, SubjectInputDto dto);
}

