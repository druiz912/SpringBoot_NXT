package com.druiz.ej3.profesor.application;

import com.druiz.ej3.persona.domain.PersonaEntity;
import com.druiz.ej3.persona.infrastructure.repository.IPersonaRepoJPA;
import com.druiz.ej3.profesor.application.port.IProfesorService;
import com.druiz.ej3.profesor.domain.ProfesorEntity;
import com.druiz.ej3.profesor.infrastucture.dto.input.ProfesorInputDto;
import com.druiz.ej3.profesor.infrastucture.dto.output.ProfesorOutputDto;
import com.druiz.ej3.profesor.infrastucture.repo.IProfesorRepo;
import com.druiz.ej3.student.infrastructure.repo.IStudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfesorServiceImpl implements IProfesorService {

    @Autowired
    IPersonaRepoJPA personaRepo;
    @Autowired
    IProfesorRepo IProfesorRepo;
    @Autowired
    IStudentRepo studentRepo;


    @Override
    public ProfesorOutputDto addProfe(ProfesorInputDto profeInputDto) throws Exception {
        PersonaEntity persona = personaRepo.findById(profeInputDto.getId_persona())
                .orElseThrow(() -> new Exception("No se ha encontrado el ID"));
        ProfesorEntity profesor = new ProfesorEntity(profeInputDto);
        profesor.setPersona(persona);
        IProfesorRepo.save(profesor);
        return new ProfesorOutputDto(profesor);
    }

    @Override
    public ProfesorOutputDto getProfesorByID(String id) throws Exception {
        ProfesorEntity profesor = IProfesorRepo.findById(id)
                .orElseThrow(() -> new Exception("No se ha encontrado el Profesor con ID: " + id));
        return new ProfesorOutputDto(profesor);
    }

    @Override
    public List<ProfesorOutputDto> getAllProfesores() {
        List<ProfesorOutputDto> profesorOutputDTOList = new ArrayList<>();
        if (IProfesorRepo.findAll() != null) {
            IProfesorRepo.findAll().forEach(profesorEntity -> {
                ProfesorOutputDto profesorOutputDTO = new ProfesorOutputDto(profesorEntity);
                profesorOutputDTOList.add(profesorOutputDTO);
            });
            return profesorOutputDTOList;

        } else {
            List<ProfesorOutputDto> profesorOutputDTOListVacia = new ArrayList<>();
            return profesorOutputDTOList;
        }
    }

    @Override
    public List<ProfesorOutputDto> getProfesorByBranch(String branch) {
        List<ProfesorOutputDto> ListProfesorOutputDto = new ArrayList<>();
        if (IProfesorRepo.findByBranch(branch) != null) {
            IProfesorRepo.findByBranch(branch).forEach(profesorEntity -> {
                ProfesorOutputDto profesorOutputDTO = new ProfesorOutputDto(profesorEntity);
                ListProfesorOutputDto.add(profesorOutputDTO);
            });
            return ListProfesorOutputDto;

        } else {
            List<ProfesorOutputDto> ListEmptyProfesorOutputDto = new ArrayList<>();
            return ListEmptyProfesorOutputDto;
        }
    }

    @Override
    public ProfesorOutputDto updateProfesor(String id, ProfesorInputDto profeInputDto) throws Exception {
        ProfesorEntity profesor = IProfesorRepo.findById(id)
                .orElseThrow(() -> new Exception("No se ha podido actualizar el Pofesor con ID: " + id));
        profesor.update(profeInputDto);
        IProfesorRepo.save(profesor);
        return new ProfesorOutputDto(profesor);
    }

    @Override
    public void deleteProfesorByID(String id) throws Exception {
        ProfesorEntity profesor = IProfesorRepo.findById(id).orElseThrow(() -> new Exception("No se ha encontrado el Profesor con el ID: " + id));
        IProfesorRepo.deleteById(id);

    }
}
