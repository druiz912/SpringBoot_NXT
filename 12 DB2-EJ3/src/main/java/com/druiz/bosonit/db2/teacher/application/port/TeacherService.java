package com.druiz.bosonit.db2.teacher.application.port;

import com.druiz.bosonit.db2.teacher.infrastucture.controller.dto.input.TeacherInputDto;
import com.druiz.bosonit.db2.teacher.infrastucture.controller.dto.output.TeacherOutputDto;

import java.util.List;

public interface TeacherService {
    List<TeacherOutputDto> getAllTeachers();

    TeacherOutputDto getTeacherByID(Integer id);

    TeacherOutputDto postTeacher(TeacherInputDto teacherInputDTO);

}
