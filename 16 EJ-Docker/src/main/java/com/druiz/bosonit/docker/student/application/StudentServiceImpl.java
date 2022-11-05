package com.druiz.bosonit.docker.student.application;

import com.druiz.bosonit.docker.utils.exceptions.NotFoundException;
import com.druiz.bosonit.docker.utils.exceptions.UnprocesableException;
import com.druiz.bosonit.docker.person.domain.Person;
import com.druiz.bosonit.docker.subject.infrastructure.repo.SubjectRepo;
import com.druiz.bosonit.docker.student.application.port.StudentService;
import com.druiz.bosonit.docker.student.infrastructure.controller.dto.input.StudentInputDto;
import com.druiz.bosonit.docker.student.infrastructure.controller.dto.output.StudentOutputDto;
import com.druiz.bosonit.docker.student.infrastructure.controller.dto.output.StudentOutputDtoFull;
import com.druiz.bosonit.docker.student.infrastructure.controller.dto.output.StudentOutputDtoSimple;
import com.druiz.bosonit.docker.student.infrastructure.repo.StudentRepo;
import com.druiz.bosonit.docker.subject.domain.Subject;
import com.druiz.bosonit.docker.subject.infrastructure.dto.input.SubjectInputDtoAlone;
import com.druiz.bosonit.docker.person.infrastructure.repository.PersonRepo;
import com.druiz.bosonit.docker.student.domain.Student;
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

    @Override
    public StudentOutputDto createStudent(StudentInputDto stuInputDto) {
        List<Subject> ListCourse = new ArrayList<>();
        Person person = repoPersona.findById(stuInputDto.getIdPerson())
                .orElseThrow(() -> new NotFoundException("No existe el id en la tabla Persona:"+ stuInputDto.getIdPerson()));
        Student student = new Student(stuInputDto);
        student.setIdPerson(person);
        stuInputDto.getSubjects().forEach(estudios -> {
            Subject asignaturaStudent = repoCourse.findById(estudios.getId())
                    .orElseThrow();
            ListCourse.add(asignaturaStudent);
        });
        student.setEstudios(ListCourse);
        repoStudent.save(student);
        return new StudentOutputDto(student);
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
            Student student = repoStudent.findById(id)
                    .orElseThrow(() -> new NotFoundException("No se ha podido actualizar el estudiante con el ID: " + id));
            student.update(stuInputDto);
            repoStudent.save(student);
            return new StudentOutputDto(student);
        }

        @Override
        public String deleteStudentById (String id) {
            if(repoStudent.findById(id).isPresent()){
                repoStudent.deleteById(id);
            } else{
                throw new NotFoundException("No se ha podido borrar el Estudiante con ID: " + id);
            }
            return "No se ha podido realizar la consulta";
        }

        //AÑADIR UNA O MAS ASIGNATURAS
        @Override
        public StudentOutputDto addCourse(String id, SubjectInputDtoAlone listaIdAsignaturas) {
            Student student = repoStudent.findById(id).orElseThrow(
                    ()-> new NotFoundException("No se encuentra el Id del estudiante: "+ repoStudent.findById(id)));
            listaIdAsignaturas.getId().forEach(listaId -> {
                Subject subject = repoCourse.findById(listaId).orElseThrow(
                        ()-> new NotFoundException("No se ha encontrado el siguiente id:"+listaIdAsignaturas.getId()));
                subject.addEstudios(subject);
                repoStudent.save(student);
            } );
            return new StudentOutputDto(student);
        }
    }


