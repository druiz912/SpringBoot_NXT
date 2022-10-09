package com.druiz.bosonit.db2.utils.mappers;

import com.druiz.bosonit.db2.subject.domain.Subject;
import com.druiz.bosonit.db2.subject.infrastructure.controller.dto.input.SubjectInputDto;
import com.druiz.bosonit.db2.subject.infrastructure.controller.dto.output.SubjectOutputDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    Subject toEntity(SubjectInputDto subjectInputDto);

    SubjectOutputDto toOutput (Subject subject);

}
