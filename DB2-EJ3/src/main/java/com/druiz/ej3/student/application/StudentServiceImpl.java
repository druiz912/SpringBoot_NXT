package com.druiz.ej3.student.application;

import com.druiz.ej3.persona.domain.PersonaEntity;
import com.druiz.ej3.persona.infrastructure.repository.IPersonaRepoJPA;
import com.druiz.ej3.student.application.port.IStudentService;
import com.druiz.ej3.student.domain.StudentEntity;
import com.druiz.ej3.student.infrastructure.dto.input.StudentInputDto;
import com.druiz.ej3.student.infrastructure.dto.output.StudentOutputDto;
import com.druiz.ej3.student.infrastructure.dto.output.StudentOutputDtoFull;
import com.druiz.ej3.student.infrastructure.dto.output.StudentOutputDtoSimple;
import com.druiz.ej3.student.infrastructure.repo.IStudentRepo;
import com.druiz.ej3.studentCourse.domain.StudentCourseEntity;
import com.druiz.ej3.studentCourse.infrastructure.dto.input.StudentCourseInputDtoAlone;
import com.druiz.ej3.studentCourse.infrastructure.dto.output.StudentCourseOutputDto;
import com.druiz.ej3.studentCourse.infrastructure.repo.IStudentCourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    IStudentRepo repoStudent;

    @Autowired
    IPersonaRepoJPA repoPersona;

    @Autowired
    IStudentCourseRepo studentCourseRepo;

    @Override
    public StudentOutputDto createStudent(StudentInputDto stuInputDto) throws Exception {
        List<StudentCourseEntity> ListCourse = new ArrayList<>();
        PersonaEntity personaEntity = repoPersona.findById(stuInputDto.getId_persona())
                .orElseThrow(() -> new Exception("No se ha encontrado el ID de Persona"));
        StudentEntity studentEntity = new StudentEntity(stuInputDto);
        studentEntity.setPersona(personaEntity);


        //studentEntity.setEstudios(ListCourse);
        repoStudent.save(studentEntity);
        return new StudentOutputDto(studentEntity);
    }

    @Override
    public StudentOutputDto getStudentById (String id, String outType) throws Exception {
        StudentEntity studentEntity = repoStudent.findById(id)
                .orElseThrow(() -> new Exception("No se ha encontrado el ID de Estudiante"));
        if (outType.equalsIgnoreCase("simple"))
            return new StudentOutputDtoSimple(studentEntity); //1º parte del ejercicio
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
        listaAsignaturas.getId().forEach(x -> {
            StudentCourseEntity studentCourseEntity = studentCourseRepo.findById(x).get();
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
        public StudentOutputDto updateStudentById (StudentInputDto stuInputDto, String id) throws Exception {
            StudentEntity studentEntity = repoStudent.findById(id)
                    .orElseThrow(() -> new Exception("No se ha podido actualizar el estudiante con el ID: " + id));
            studentEntity.update(stuInputDto);
            repoStudent.save(studentEntity);
            return new StudentOutputDto(studentEntity);
        }

        @Override
        public void deleteStudentById (String id) throws Exception {
            StudentEntity studentEntity = repoStudent.findById(id)
                    .orElseThrow(() -> new Exception("No se ha podido borrar el Estudiante con ID: " + id));
            repoStudent.deleteById(id);
        }
    }


