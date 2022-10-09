package com.druiz.bosonit.db2.utils.mappers;

import com.druiz.bosonit.db2.teacher.domain.Teacher;
import com.druiz.bosonit.db2.teacher.infrastucture.controller.dto.input.TeacherInputDto;
import com.druiz.bosonit.db2.teacher.infrastucture.controller.dto.output.TeacherOutputDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    Teacher toEntity (TeacherInputDto teacherInputDto);
    TeacherOutputDto toOutput (Teacher teacher);


}
