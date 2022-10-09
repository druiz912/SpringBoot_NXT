package com.druiz.bosonit.db2.teacher.application;

import com.druiz.bosonit.db2.person.domain.Person;
import com.druiz.bosonit.db2.person.infrastructure.repository.PersonRepo;
import com.druiz.bosonit.db2.teacher.infrastucture.controller.dto.input.TeacherInputDto;
import com.druiz.bosonit.db2.teacher.infrastucture.controller.dto.output.TeacherOutputDto;
import com.druiz.bosonit.db2.utils.exceptions.NotFoundException;
import com.druiz.bosonit.db2.utils.exceptions.UnprocesableException;
import com.druiz.bosonit.db2.teacher.application.port.TeacherService;
import com.druiz.bosonit.db2.teacher.domain.Teacher;
import com.druiz.bosonit.db2.teacher.infrastucture.repo.TeacherRepo;
import com.druiz.bosonit.db2.utils.mappers.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    PersonRepo personRepo;
    @Autowired
    TeacherRepo teacherRepo;
    @Autowired
    TeacherMapper teacherMapper;


    @Override
    public TeacherOutputDto addProfe(TeacherInputDto teacherInput) {
        try{
            Person person = personRepo.findById(teacherInput.getIdPerson()).orElseThrow(
                    () -> new NotFoundException("No se encuentra el id: " + teacherInput.getIdPerson()));
            Teacher teacher = teacherMapper.toEntity(teacherInput);
            // Asignamos al profesor la persona
            teacher.setPersona(person);
            teacherRepo.save(teacher);
            return teacherMapper.toOutput(teacher);
        }catch (Exception e){
            throw new UnprocesableException(e.getMessage());
        }

    }

    @Override
    public TeacherOutputDto getProfesorByID(String id) {
        Teacher teacher = teacherRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado al Profesor con ID: " + id));
        return teacherMapper.toOutput(teacher);
    }

    @Override
    public List<TeacherOutputDto> getAllProfesores() {
        List<TeacherOutputDto> listTeacherOutputDto = new ArrayList<>();
        teacherRepo.findAll();
        teacherRepo.findAll().forEach(teacherInDB -> {
            TeacherOutputDto teacherOutputDTO = teacherMapper.toOutput(teacherInDB);
            listTeacherOutputDto.add(teacherOutputDTO);
        });
        return listTeacherOutputDto;

    }

    @Override
    public List<TeacherOutputDto> getProfesorByBranch(String branch) {
        List<TeacherOutputDto> listTeacherOutputDto = new ArrayList<>();
        if (teacherRepo.findByBranch(branch) != null) {
            teacherRepo.findByBranch(branch).forEach(teacherInDB -> {
                TeacherOutputDto teacherOutputDTO = teacherMapper.toOutput(teacherInDB);
                listTeacherOutputDto.add(teacherOutputDTO);
            });
            return listTeacherOutputDto;

        } else {
           return null; //cambiar
        }
    }

    @Override
    public TeacherOutputDto updateProfesor(String id, TeacherInputDto profeInputDto) {
        try{
            Teacher teacher = teacherRepo.findById(id).orElseThrow(
                    () -> new NotFoundException("No se ha podido actualizar al Pofesor con ID: " + id));
            teacher.update(profeInputDto);
            teacherRepo.save(teacher);
            return teacherMapper.toOutput(teacher);
        }catch(Exception e){
            throw new UnprocesableException(e.getMessage());
        }

    }

    @Override
    public void deleteProfesorByID(String id) {
        teacherRepo.findById(id).orElseThrow(
                () -> new NotFoundException("No se ha encontrado al Profesor con el ID: " + id));
        teacherRepo.deleteById(id);

    }
}
