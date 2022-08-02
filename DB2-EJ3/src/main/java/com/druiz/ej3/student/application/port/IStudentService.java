package com.druiz.ej3.student.application.port;


import com.druiz.ej3.student.infrastructure.dto.input.StudentInputDto;
import com.druiz.ej3.student.infrastructure.dto.output.StudentOutputDto;
import com.druiz.ej3.studentCourse.infrastructure.dto.input.StudentCourseInputDtoAlone;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IStudentService {

StudentOutputDto createStudent (StudentInputDto stuInputDto) throws Exception;
StudentOutputDto getStudentById (String id, String outType) throws Exception;
List<StudentOutputDto> getAllStudents();

StudentOutputDto addCourse(String id,StudentCourseInputDtoAlone listaAsignaturas);
List<StudentOutputDto> getStudentByBranch(String branch);
StudentOutputDto updateStudentById(StudentInputDto stuInputDto, String id) throws Exception;
void deleteStudentById (String id) throws Exception;

}
