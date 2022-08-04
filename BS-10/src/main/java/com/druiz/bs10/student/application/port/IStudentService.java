package com.druiz.bs10.student.application.port;


import com.druiz.bs10.student.infrastructure.dto.input.StudentInputDto;
import com.druiz.bs10.student.infrastructure.dto.output.StudentOutputDto;
import com.druiz.bs10.studentCourse.infrastructure.dto.input.StudentCourseInputDtoAlone;

import java.util.List;

public interface IStudentService {

StudentOutputDto createStudent (StudentInputDto stuInputDto) throws Exception;
StudentOutputDto getStudentById (String id, String outType) throws Exception;
List<StudentOutputDto> getAllStudents();

StudentOutputDto addCourse(String id, StudentCourseInputDtoAlone listaAsignaturas);
List<StudentOutputDto> getStudentByBranch(String branch);
StudentOutputDto updateStudentById(StudentInputDto stuInputDto, String id) throws Exception;
void deleteStudentById (String id) throws Exception;

}
