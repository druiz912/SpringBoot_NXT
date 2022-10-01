package com.druiz.bs12.studentCourse.application;

import com.druiz.bs12.studentCourse.domain.StudentCourseEntity;
import com.druiz.bs12.studentCourse.infrastructure.repo.IStudentCourseRepo;
import com.druiz.bs12.exceptions.NotFoundException;
import com.druiz.bs12.student.infrastructure.repo.IStudentRepo;
import com.druiz.bs12.studentCourse.infrastructure.dto.input.StudentCourseInputDto;
import com.druiz.bs12.studentCourse.infrastructure.dto.output.StudentCourseOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentCourseServiceImpl implements IStudentCourseService {

    @Autowired
    IStudentCourseRepo studentCourseRepo;
    @Autowired
    IStudentRepo studentRepo;

    @Override
    public StudentCourseOutputDto createCourse(StudentCourseInputDto studentCourseInputDto) throws Exception {
        StudentCourseEntity studentCourse = new StudentCourseEntity(studentCourseInputDto);
        studentCourseRepo.save(studentCourse);
        return new StudentCourseOutputDto(studentCourse);
    }

    @Override
    public StudentCourseOutputDto findById(String id) throws Exception {
        StudentCourseEntity studentCourse = studentCourseRepo.findById(id)
                .orElseThrow(() -> new Exception("No se ha encontrado el ID: " + id));
        return new StudentCourseOutputDto(studentCourse);
    }

    @Override
    public StudentCourseOutputDto updateCourse(String id, StudentCourseInputDto studentCourseInputDto) throws Exception {
        StudentCourseEntity studentCourse = studentCourseRepo.findById(id)
                .orElseThrow(() -> new Exception("No se ha encontrado el ID: " + id));
        studentCourse.update(studentCourseInputDto);
        return new StudentCourseOutputDto(studentCourseRepo.save(studentCourse));
    }

    @Override
    public void deleteById(String id) {
        studentCourseRepo.findById(id).orElseThrow(() -> new NotFoundException("No se ha encontrado el ID: " + id));
        studentCourseRepo.deleteById(id);
    }

}
