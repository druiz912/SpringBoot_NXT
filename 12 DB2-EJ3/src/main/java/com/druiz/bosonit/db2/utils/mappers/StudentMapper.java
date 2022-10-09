package com.druiz.bosonit.db2.utils.mappers;

import com.druiz.bosonit.db2.student.domain.Student;
import com.druiz.bosonit.db2.student.infrastructure.controller.dto.input.StudentInputDto;
import com.druiz.bosonit.db2.student.infrastructure.controller.dto.output.StudentOutputDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student toEntity(StudentInputDto studentInputDto);
    StudentOutputDto toOutput(Student student);
}
