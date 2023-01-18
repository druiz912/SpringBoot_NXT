package com.druiz.bosonit.db2.teacher.application;

import com.druiz.bosonit.db2.person.domain.Person;
import com.druiz.bosonit.db2.person.infrastructure.repository.PersonRepo;
import com.druiz.bosonit.db2.student.infrastructure.repo.StudentRepo;
import com.druiz.bosonit.db2.teacher.infrastucture.controller.dto.input.TeacherInputDto;
import com.druiz.bosonit.db2.teacher.infrastucture.controller.dto.output.TeacherOutputDto;
import com.druiz.bosonit.db2.utils.exceptions.NotFoundException;
import com.druiz.bosonit.db2.utils.exceptions.UnprocesableException;
import com.druiz.bosonit.db2.teacher.application.port.TeacherService;
import com.druiz.bosonit.db2.teacher.domain.Teacher;
import com.druiz.bosonit.db2.teacher.infrastucture.repo.TeacherRepo;
import com.druiz.bosonit.db2.utils.mappers.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepo teacherRepository;

    @Autowired
    StudentRepo studentRepository;

    @Autowired
    PersonRepo personRepository;

    @Override
    public List<TeacherOutputDto> getAllTeachers() {
        List<TeacherOutputDto> teacherOutputDTOList = new ArrayList<>();
        for(Teacher teacherEntity : teacherRepository.findAll()){
            teacherOutputDTOList.add(new TeacherOutputDto(teacherEntity));
        }
        return teacherOutputDTOList;
    }

    @Override
    public TeacherOutputDto getTeacherByID(String id) {
        if(!teacherRepository.existsById(id))
            throw new RuntimeException("Teacher with id: " + id + " doesnt exists.");

        TeacherOutputDto teacherOutputDTO = new TeacherOutputDto(teacherRepository.findById(id).orElse(null));
        return teacherOutputDTO;
    }

    @Override
    public TeacherOutputDto postTeacher(TeacherInputDto teacherInputDTO) throws  RuntimeException{
        Person personEntity = personRepository.findById(teacherInputDTO.getIdPerson()).orElse(null);

        if(personEntity==null ||
                teacherRepository.getPersonQuery(teacherInputDTO.getIdPerson()) != null ||
                studentRepository.getPersonQuery(teacherInputDTO.getIdPerson()) != null
        )
            throw new RuntimeException("Teacher object must have a correct person reference.");

        Teacher teacherEntity = new Teacher(teacherInputDTO, personEntity);
        teacherRepository.save(teacherEntity);

        return new TeacherOutputDto(teacherEntity);
    }
}
