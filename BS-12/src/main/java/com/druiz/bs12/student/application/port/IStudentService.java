package com.druiz.bs12.student.application.port;


import com.druiz.bs12.studentCourse.infrastructure.dto.input.StudentCourseInputDtoAlone;
import com.druiz.bs12.student.infrastructure.dto.input.StudentInputDto;
import com.druiz.bs12.student.infrastructure.dto.output.StudentOutputDto;

import java.util.List;

public interface IStudentService {

StudentOutputDto createStudent (StudentInputDto stuInputDto) throws Exception;
StudentOutputDto getStudentById (String id, String outType) throws Exception;
List<StudentOutputDto> getAllStudents();
List<StudentOutputDto> getStudentByBranch(String branch);
StudentOutputDto updateStudentById(StudentInputDto stuInputDto, String id) throws Exception;
void deleteStudentById (String id) throws Exception;

    //AÑADIR UNA O MAS ASIGNATURAS
    StudentOutputDto addCourse(String id, StudentCourseInputDtoAlone listaAsignaturas);
}
