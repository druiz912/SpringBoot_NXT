package com.druiz.bosonit.db2.subject.application;

import com.druiz.bosonit.db2.student.infrastructure.repo.StudentRepo;
import com.druiz.bosonit.db2.subject.domain.Subject;
import com.druiz.bosonit.db2.subject.infrastructure.controller.dto.input.SubjectInputDto;
import com.druiz.bosonit.db2.subject.infrastructure.controller.dto.output.SubjectOutputDto;
import com.druiz.bosonit.db2.subject.infrastructure.repo.SubjectRepo;
import com.druiz.bosonit.db2.utils.exceptions.NotFoundException;
import com.druiz.bosonit.db2.utils.mappers.SubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepo subjectRepo;
    @Autowired
    StudentRepo studentRepo;
    @Autowired
    SubjectMapper subjectMapper;

    @Override
    public SubjectOutputDto createCourse(SubjectInputDto subjectInputDto)  {
        Subject subject = subjectMapper.toEntity(subjectInputDto);
        subjectRepo.save(subject);
        return subjectMapper.toOutput(subject);
    }

    @Override
    public SubjectOutputDto findById(String id) {
        Subject subject = subjectRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado el ID: " + id));
        return new SubjectOutputDto(subject);
    }

    @Override
    public SubjectOutputDto updateCourse(String id, SubjectInputDto subjectInputDto){
        Subject subject = subjectRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado el ID: " + id));
        subject.update(subjectInputDto);
        subjectRepo.save(subject);
        return subjectMapper.toOutput(subject);
    }

    @Override
    public void deleteById(String id) {
        subjectRepo.findById(id).orElseThrow(() -> new NotFoundException("No se ha encontrado el ID: " + id));
        subjectRepo.deleteById(id);
    }
}
