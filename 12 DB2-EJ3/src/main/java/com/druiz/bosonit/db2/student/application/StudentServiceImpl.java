package com.druiz.bosonit.db2.student.application;

import com.druiz.bosonit.db2.person.domain.Person;
import com.druiz.bosonit.db2.person.infrastructure.repository.PersonRepo;
import com.druiz.bosonit.db2.subject.domain.Subject;
import com.druiz.bosonit.db2.subject.infrastructure.controller.dto.input.SubjectInputDtoAlone;
import com.druiz.bosonit.db2.subject.infrastructure.repo.SubjectRepo;
import com.druiz.bosonit.db2.utils.exceptions.NotFoundException;
import com.druiz.bosonit.db2.utils.exceptions.UnprocesableException;
import com.druiz.bosonit.db2.student.application.port.StudentService;
import com.druiz.bosonit.db2.student.domain.Student;
import com.druiz.bosonit.db2.student.infrastructure.controller.dto.input.StudentInputDto;
import com.druiz.bosonit.db2.student.infrastructure.controller.dto.output.StudentOutputDto;
import com.druiz.bosonit.db2.student.infrastructure.controller.dto.output.StudentOutputDtoFull;
import com.druiz.bosonit.db2.student.infrastructure.controller.dto.output.StudentOutputDtoSimple;
import com.druiz.bosonit.db2.student.infrastructure.repo.StudentRepo;
import com.druiz.bosonit.db2.utils.mappers.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepo repoStudent;

    @Autowired
    PersonRepo repoPersona;

    @Autowired
    SubjectRepo repoCourse;

    @Autowired
    StudentMapper studentMapper;

    @Override
    public StudentOutputDto createStudent(StudentInputDto stuInputDto) {
        List<Subject> listSubject = new ArrayList<>();
        Person person = repoPersona.findById(stuInputDto.getIdPerson())
                .orElseThrow(() -> new NotFoundException("No se ha encontrado el id:"+ stuInputDto.getIdPerson()));
        // Mapstruct
        Student student = studentMapper.toEntity(stuInputDto);
        student.setPersona(person);
        stuInputDto.getEstudios().forEach(estudios -> {
            Subject subjectStudent = repoCourse.findById(estudios.getIdSubject())
                    .orElseThrow(()-> new NotFoundException("No se encontraron los estudios para el estudiante"));
            listSubject.add(subjectStudent);
        });
        student.setEstudios(listSubject);
        repoStudent.save(student);
        return studentMapper.toOutput(student);
    }

    @Override
    public StudentOutputDto getStudentById(String id, String outType) throws NotFoundException, UnprocesableException {
        Student student = repoStudent.findById(id)
                .orElseThrow(() -> new NotFoundException("No sé encontro la id"));
        if (outType.equalsIgnoreCase("simple"))
            return new StudentOutputDtoSimple(student);
        else
            return new StudentOutputDtoFull(student);
    }

    @Override
    public List<StudentOutputDto> getAllStudents() {
        List<StudentOutputDto> listaStudentOutputDto = new ArrayList<>();
        repoStudent.findAll().forEach(studentEntity -> listaStudentOutputDto.add(studentMapper.toOutput(studentEntity)));
            return listaStudentOutputDto;
    }

    @Override
    public StudentOutputDto addCourse(String id, SubjectInputDtoAlone listaAsignaturas) {
        Student student = repoStudent.findById(id).orElseThrow(()-> new NotFoundException("Estudiante no encontrado"));
        listaAsignaturas.getId().forEach(asignaturas -> {
            Subject subject = repoCourse.findById(asignaturas).orElseThrow(
                    ()-> new NotFoundException("No se ha encontrado la id:"+listaAsignaturas.getId()));
            student.addEstudios(subject);
            // actualizamos estudiante con las asignaturas
            repoStudent.save(student);
        } );
        return studentMapper.toOutput(student);
    }

    @Override
        public List<StudentOutputDto> getStudentByBranch (String branch){
           List<StudentOutputDto> listaStudentOutputDto = new ArrayList<>();
           repoStudent.findByBranch(branch)
                   .forEach(studentEntity -> listaStudentOutputDto.add(studentMapper.toOutput(studentEntity)));
           return listaStudentOutputDto;
        }

        @Override
        public StudentOutputDto updateStudentById (StudentInputDto stuInputDto, String id) {
            Student student = repoStudent.findById(id)
                    .orElseThrow(() -> new NotFoundException("No se ha podido actualizar el estudiante con el ID: " + id));
            student.update(stuInputDto);
            repoStudent.save(student);
            return studentMapper.toOutput(student);
        }

        @Override
        public void deleteStudentById (String id) {
            repoStudent.findById(id)
                    .orElseThrow(() -> new NotFoundException("No se ha podido encontrar el Estudiante con ID: " + id));
            repoStudent.deleteById(id);
        }
    }


