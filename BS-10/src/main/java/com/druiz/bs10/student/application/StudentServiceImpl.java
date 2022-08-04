package com.druiz.bs10.student.application;

import com.druiz.bs10.studentCourse.domain.StudentCourseEntity;
import com.druiz.bs10.studentCourse.infrastructure.dto.input.StudentCourseInputDtoAlone;
import com.druiz.bs10.exceptions.NotFoundException;
import com.druiz.bs10.exceptions.UnprocesableException;
import com.druiz.bs10.persona.domain.PersonaEntity;
import com.druiz.bs10.persona.infrastructure.repository.IPersonaRepoJPA;
import com.druiz.bs10.student.application.port.IStudentService;
import com.druiz.bs10.student.domain.StudentEntity;
import com.druiz.bs10.student.infrastructure.dto.input.StudentInputDto;
import com.druiz.bs10.student.infrastructure.dto.output.StudentOutputDto;
import com.druiz.bs10.student.infrastructure.dto.output.StudentOutputDtoFull;
import com.druiz.bs10.student.infrastructure.dto.output.StudentOutputDtoSimple;
import com.druiz.bs10.student.infrastructure.repo.IStudentRepo;
import com.druiz.bs10.studentCourse.infrastructure.repo.IStudentCourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    IStudentRepo repoStudent;

    @Autowired
    IPersonaRepoJPA repoPersona;

    @Autowired
    IStudentCourseRepo repoCourse;

    @Override
    public StudentOutputDto createStudent(StudentInputDto stuInputDto) {
        List<StudentCourseEntity> ListCourse = new ArrayList<>();
        PersonaEntity personaEntity = repoPersona.findById(stuInputDto.getId_persona())
                .orElseThrow(() -> new NotFoundException("No se ha encontrado el id:"+ stuInputDto.getId_persona()));
        StudentEntity studentEntity = new StudentEntity(stuInputDto);
        studentEntity.setPersona(personaEntity);
        stuInputDto.getEstudios().forEach(estudios -> {
            StudentCourseEntity asignaturaStudent = repoCourse.findById(estudios.getId_asignatura())
                    .orElseThrow();
            ListCourse.add(asignaturaStudent);
        });
        studentEntity.setEstudios(ListCourse);
        repoStudent.save(studentEntity);
        return new StudentOutputDto(studentEntity);
    }

    @Override
    public StudentOutputDto getStudentById(String id, String outType) throws NotFoundException, UnprocesableException {
        StudentEntity studentEntity = repoStudent.findById(id)
                .orElseThrow(() -> new NotFoundException("No sé encontro la id"));
        if (outType.equalsIgnoreCase("simple"))
            return new StudentOutputDtoSimple(studentEntity);
        else
            return new StudentOutputDtoFull(studentEntity);
    }

    @Override
    public List<StudentOutputDto> getAllStudents() {
        List<StudentOutputDto> listaStudentOutputDto = new ArrayList<>();
        repoStudent.findAll().forEach(studentEntity -> listaStudentOutputDto.add(new StudentOutputDto(studentEntity)));
            return listaStudentOutputDto;
    }

    @Override
    public StudentOutputDto addCourse(String id, StudentCourseInputDtoAlone listaAsignaturas) {
        StudentEntity studentEntity = repoStudent.findById(id).get();
        listaAsignaturas.getId().forEach(asignaturas -> {
            StudentCourseEntity studentCourseEntity = repoCourse.findById(asignaturas).orElseThrow(
                    ()-> new NotFoundException("No se ha encontrado la id:"+listaAsignaturas.getId()));
            studentEntity.addEstudios(studentCourseEntity);
            repoStudent.save(studentEntity);
        } );
        return new StudentOutputDto(studentEntity);
    }

    @Override
        public List<StudentOutputDto> getStudentByBranch (String branch){
           List<StudentOutputDto> listaStudentOutputDto = new ArrayList<>();
           repoStudent.findByBranch(branch)
                   .forEach(studentEntity -> listaStudentOutputDto.add(new StudentOutputDto(studentEntity)));
           return listaStudentOutputDto;
        }

        @Override
        public StudentOutputDto updateStudentById (StudentInputDto stuInputDto, String id) {
            StudentEntity studentEntity = repoStudent.findById(id)
                    .orElseThrow(() -> new NotFoundException("No se ha podido actualizar el estudiante con el ID: " + id));
            studentEntity.update(stuInputDto);
            repoStudent.save(studentEntity);
            return new StudentOutputDto(studentEntity);
        }

        @Override
        public void deleteStudentById (String id) {
            StudentEntity studentEntity = repoStudent.findById(id)
                    .orElseThrow(() -> new NotFoundException("No se ha podido borrar el Estudiante con ID: " + id));
            repoStudent.deleteById(id);
        }
    }


