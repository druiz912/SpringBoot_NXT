package com.druiz.bosonit.db2.teacher.application.port;

import com.druiz.bosonit.db2.teacher.infrastucture.controller.dto.input.TeacherInputDto;
import com.druiz.bosonit.db2.teacher.infrastucture.controller.dto.output.TeacherOutputDto;

import java.util.List;

public interface TeacherService {
    //Create
    TeacherOutputDto addProfe(TeacherInputDto profeInputDto);
    //Read
    TeacherOutputDto getProfesorByID(String id);
    List<TeacherOutputDto> getAllProfesores();
    List<TeacherOutputDto> getProfesorByBranch(String branch);
    //Update
    TeacherOutputDto updateProfesor(String id, TeacherInputDto profeInputDto);
    //Delete
    void deleteProfesorByID(String id);

}
