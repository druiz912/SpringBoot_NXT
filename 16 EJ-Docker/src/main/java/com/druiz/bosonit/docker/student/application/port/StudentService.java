package com.druiz.bosonit.docker.student.application.port;


import com.druiz.bosonit.docker.subject.infrastructure.dto.input.SubjectInputDtoAlone;
import com.druiz.bosonit.docker.student.infrastructure.controller.dto.input.StudentInputDto;
import com.druiz.bosonit.docker.student.infrastructure.controller.dto.output.StudentOutputDto;

import java.util.List;

public interface StudentService {

StudentOutputDto createStudent (StudentInputDto stuInputDto) throws Exception;
StudentOutputDto getStudentById (String id, String outType) throws Exception;
List<StudentOutputDto> getAllStudents();
List<StudentOutputDto> getStudentByBranch(String branch);
StudentOutputDto updateStudentById(StudentInputDto stuInputDto, String id) throws Exception;
String deleteStudentById (String id) throws Exception;

    //AÑADIR UNA O MAS ASIGNATURAS
    StudentOutputDto addCourse(String id, SubjectInputDtoAlone listaAsignaturas);
}
