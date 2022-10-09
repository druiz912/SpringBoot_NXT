package com.druiz.bosonit.db2.student.application.port;


import com.druiz.bosonit.db2.subject.infrastructure.controller.dto.input.SubjectInputDtoAlone;
import com.druiz.bosonit.db2.student.infrastructure.controller.dto.input.StudentInputDto;
import com.druiz.bosonit.db2.student.infrastructure.controller.dto.output.StudentOutputDto;

import java.util.List;

public interface StudentService {

StudentOutputDto createStudent (StudentInputDto stuInputDto);
StudentOutputDto getStudentById (String id, String outType) ;
List<StudentOutputDto> getAllStudents();

StudentOutputDto addCourse(String id, SubjectInputDtoAlone listaAsignaturas);
List<StudentOutputDto> getStudentByBranch(String branch);
StudentOutputDto updateStudentById(StudentInputDto stuInputDto, String id);
void deleteStudentById (String id);

}
