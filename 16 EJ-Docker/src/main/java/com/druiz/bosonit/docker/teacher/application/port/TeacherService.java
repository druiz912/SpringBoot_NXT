package com.druiz.bosonit.docker.teacher.application.port;

import com.druiz.bosonit.docker.teacher.infrastucture.dto.input.TeacherInputDto;
import com.druiz.bosonit.docker.teacher.infrastucture.dto.output.TeacherOutputDto;

import java.util.List;

public interface TeacherService {
    //Create
    TeacherOutputDto addTeacher(TeacherInputDto profeInputDto) throws Exception;

    //Read
    TeacherOutputDto findTeacherById(String id) throws Exception;
    List<TeacherOutputDto> findAllTeachers();
    List<TeacherOutputDto> findTeacherByBranch(String branch);

    //Update
    TeacherOutputDto updateTeacher(String id, TeacherInputDto profeInputDto) throws Exception;
    //Delete
    void deleteTeacherById(String id) throws Exception;

}
