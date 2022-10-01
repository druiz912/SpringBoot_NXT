package com.druiz.bs9.profesor.application.port;

import com.druiz.bs9.profesor.infrastucture.dto.input.ProfesorInputDto;
import com.druiz.bs9.profesor.infrastucture.dto.output.ProfesorOutputDto;

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
