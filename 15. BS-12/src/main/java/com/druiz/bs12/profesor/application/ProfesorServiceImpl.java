package com.druiz.bs12.profesor.application;

import com.druiz.bs12.profesor.application.port.IProfesorService;
import com.druiz.bs12.profesor.infrastucture.dto.output.ProfesorOutputDto;
import com.druiz.bs12.profesor.infrastucture.repo.IProfesorRepo;
import com.druiz.bs12.exceptions.NotFoundException;
import com.druiz.bs12.exceptions.UnprocesableException;
import com.druiz.bs12.persona.domain.PersonaEntity;
import com.druiz.bs12.persona.infrastructure.repository.IPersonaRepoJPA;
import com.druiz.bs12.profesor.domain.ProfesorEntity;
import com.druiz.bs12.profesor.infrastucture.dto.input.ProfesorInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfesorServiceImpl implements IProfesorService {

    @Autowired
    IPersonaRepoJPA personaRepo;
    @Autowired
    IProfesorRepo repoProfesor;



    @Override
    public ProfesorOutputDto addProfe(ProfesorInputDto profeInputDto) {
        try{
            PersonaEntity persona = personaRepo.findById(profeInputDto.getId_persona()).orElseThrow(
                    () -> new RuntimeException("No se encuentra el id: " + profeInputDto.getId_persona()));
            ProfesorEntity profesor = new ProfesorEntity(profeInputDto);
            profesor.setPersona(persona);
            repoProfesor.save(profesor);
            return new ProfesorOutputDto(profesor);
        }catch (Exception e){
            throw new UnprocesableException(e.getMessage());
        }

    }

    @Override
    public ProfesorOutputDto getProfesorByID(String id) {
        ProfesorEntity profesor = repoProfesor.findById(id)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado el Profesor con ID: " + id));
        return new ProfesorOutputDto(profesor);
    }

    @Override
    public List<ProfesorOutputDto> getAllProfesores() {
        List<ProfesorOutputDto> ListProfesorOutputDto = new ArrayList<>();
        if (repoProfesor.findAll() != null) {
            repoProfesor.findAll().forEach(profesorEntity -> {
                ProfesorOutputDto profesorOutputDTO = new ProfesorOutputDto(profesorEntity);
                ListProfesorOutputDto.add(profesorOutputDTO);
            });
            return ListProfesorOutputDto;

        } else {
            List<ProfesorOutputDto> profesorOutputDTOListVacia = new ArrayList<>();
            return ListProfesorOutputDto;
        }
    }

    @Override
    public List<ProfesorOutputDto> getProfesorByBranch(String branch) {
        List<ProfesorOutputDto> ListProfesorOutputDto = new ArrayList<>();
        if (repoProfesor.findByBranch(branch) != null) {
            repoProfesor.findByBranch(branch).forEach(profesorEntity -> {
                ProfesorOutputDto profesorOutputDTO = new ProfesorOutputDto(profesorEntity);
                ListProfesorOutputDto.add(profesorOutputDTO);
            });
            return ListProfesorOutputDto;

        } else {
           return null; //cambiar
        }
    }

    @Override
    public ProfesorOutputDto updateProfesor(String id, ProfesorInputDto profeInputDto) {
        try{
            ProfesorEntity profesor = repoProfesor.findById(id).orElseThrow(
                    () -> new NotFoundException("No se ha podido actualizar el Pofesor con ID: " + id));
            profesor.update(profeInputDto);
            repoProfesor.save(profesor);
            return new ProfesorOutputDto(profesor);
        }catch(Exception e){
            throw new UnprocesableException(e.getMessage());
        }

    }

    @Override
    public void deleteProfesorByID(String id) {
        repoProfesor.findById(id).orElseThrow(
                () -> new NotFoundException("No se ha encontrado el Profesor con el ID: " + id));
        repoProfesor.deleteById(id);

    }
}
