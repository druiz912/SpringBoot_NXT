package com.druiz.bosonit.docker.teacher.application;

import com.druiz.bosonit.docker.utils.exceptions.NotFoundException;
import com.druiz.bosonit.docker.utils.exceptions.UnprocesableException;
import com.druiz.bosonit.docker.person.domain.Person;
import com.druiz.bosonit.docker.person.infrastructure.repository.PersonRepo;
import com.druiz.bosonit.docker.teacher.domain.Teacher;
import com.druiz.bosonit.docker.teacher.infrastucture.dto.input.TeacherInputDto;
import com.druiz.bosonit.docker.teacher.infrastucture.dto.output.TeacherOutputDto;
import com.druiz.bosonit.docker.teacher.infrastucture.repo.TeacherRepo;
import com.druiz.bosonit.docker.teacher.application.port.TeacherService;
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

    @Override
    public TeacherOutputDto addTeacher(TeacherInputDto dto) {
        try{
            Person persona = personRepo.findById(dto.getIdPerson()).orElseThrow(
                    () -> new RuntimeException("No se encuentra el id: " + dto.getIdPerson()));
            Teacher teacher = new Teacher(dto);
            teacher.setPerson(persona);
            teacherRepo.save(teacher);
            return new TeacherOutputDto(teacher);
        }catch (Exception e){
            throw new UnprocesableException("No se ha podido añadir el profesor");
        }

    }

    @Override
    public TeacherOutputDto findTeacherById(String id) {
        Teacher teacher = teacherRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado el Profesor con ID: " + id));
        return new TeacherOutputDto(teacher);
    }

    @Override
    public List<TeacherOutputDto> findAllTeachers() {
        List<TeacherOutputDto> list = new ArrayList<>();
        teacherRepo.findAll().forEach(t -> {
            TeacherOutputDto teacherOutputDTO = new TeacherOutputDto(t);
            list.add(teacherOutputDTO);
        });
        return list;

    }

    @Override
    public List<TeacherOutputDto> findTeacherByBranch(String branch) {
        List<TeacherOutputDto> listTeacherOutputDto = new ArrayList<>();
        if (teacherRepo.findByBranch(branch) != null) {
            teacherRepo.findByBranch(branch).forEach(profesorEntity -> {
                TeacherOutputDto teacherOutputDTO = new TeacherOutputDto(profesorEntity);
                listTeacherOutputDto.add(teacherOutputDTO);
            });
            return listTeacherOutputDto;

        } else {
           return null; //cambiar
        }
    }

    @Override
    public TeacherOutputDto updateTeacher(String id, TeacherInputDto profeInputDto) {
        try{
            Teacher profesor = teacherRepo.findById(id).orElseThrow(
                    () -> new NotFoundException("No se ha podido actualizar el Pofesor con ID: " + id));
            profesor.update(profeInputDto);
            teacherRepo.save(profesor);
            return new TeacherOutputDto(profesor);
        }catch(Exception e){
            throw new UnprocesableException(e.getMessage());
        }

    }

    @Override
    public void deleteTeacherById(String id) {
        teacherRepo.findById(id).orElseThrow(
                () -> new NotFoundException("No se ha encontrado el Profesor con el ID: " + id));
        teacherRepo.deleteById(id);

    }
}
