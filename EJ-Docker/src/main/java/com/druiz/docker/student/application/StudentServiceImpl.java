package com.druiz.docker.student.application;

import com.druiz.docker.exceptions.NotFoundException;
import com.druiz.docker.exceptions.UnprocesableException;
import com.druiz.docker.student.application.port.IStudentService;
import com.druiz.docker.student.infrastructure.dto.input.StudentInputDto;
import com.druiz.docker.student.infrastructure.dto.output.StudentOutputDto;
import com.druiz.docker.student.infrastructure.dto.output.StudentOutputDtoFull;
import com.druiz.docker.student.infrastructure.dto.output.StudentOutputDtoSimple;
import com.druiz.docker.student.infrastructure.repo.IStudentRepo;
import com.druiz.docker.studentCourse.domain.StudentCourseEntity;
import com.druiz.docker.studentCourse.infrastructure.dto.input.StudentCourseInputDtoAlone;
import com.druiz.docker.studentCourse.infrastructure.repo.IStudentCourseRepo;
import com.druiz.docker.persona.domain.PersonaEntity;
import com.druiz.docker.persona.infrastructure.repository.IPersonaRepoJPA;
import com.druiz.docker.student.domain.StudentEntity;
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
                .orElseThrow(() -> new NotFoundException("No existe el id en la tabla Persona:"+ stuInputDto.getId_persona()));
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

        //AÑADIR UNA O MAS ASIGNATURAS
        @Override
        public StudentOutputDto addCourse(String id, StudentCourseInputDtoAlone listaIdAsignaturas) {
            StudentEntity studentEntity = repoStudent.findById(id).orElseThrow(
                    ()-> new NotFoundException("No se encuentra el Id del estudiante: "+ repoStudent.findById(id)));
            listaIdAsignaturas.getId().forEach(listaId -> {
                StudentCourseEntity studentCourseEntity = repoCourse.findById(listaId).orElseThrow(
                        ()-> new NotFoundException("No se ha encontrado el siguiente id:"+listaIdAsignaturas.getId()));
                studentCourseEntity.addEstudios(studentCourseEntity);
                repoStudent.save(studentEntity);
            } );
            return new StudentOutputDto(studentEntity);
        }
    }


