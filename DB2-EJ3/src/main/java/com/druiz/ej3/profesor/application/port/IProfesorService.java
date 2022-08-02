package com.druiz.ej3.profesor.application.port;

import com.druiz.ej3.profesor.infrastucture.dto.input.ProfesorInputDto;
import com.druiz.ej3.profesor.infrastucture.dto.output.ProfesorOutputDto;
import com.druiz.ej3.student.infrastructure.dto.input.StudentInputDto;
import com.druiz.ej3.student.infrastructure.dto.output.StudentOutputDto;

import java.util.List;

public interface IProfesorService {
    //Create
    ProfesorOutputDto addProfe(ProfesorInputDto profeInputDto) throws Exception;

    //Read
    ProfesorOutputDto getProfesorByID(String id) throws Exception;
    List<ProfesorOutputDto> getAllProfesores();
    List<ProfesorOutputDto> getProfesorByBranch(String branch);

    //Update
    ProfesorOutputDto updateProfesor(String id, ProfesorInputDto profeInputDto) throws Exception;
    //Delete
    void deleteProfesorByID(String id) throws Exception;

}
