package com.druiz.bosonit.docker.subject.application;

import com.druiz.bosonit.docker.utils.exceptions.NotFoundException;
import com.druiz.bosonit.docker.subject.domain.Subject;
import com.druiz.bosonit.docker.subject.infrastructure.repo.SubjectRepo;
import com.druiz.bosonit.docker.subject.infrastructure.dto.input.SubjectInputDto;
import com.druiz.bosonit.docker.subject.infrastructure.dto.output.SubjectOutputDto;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepo repo;

    public SubjectServiceImpl(SubjectRepo repo) {
        this.repo = repo;
    }

    @Override
    public SubjectOutputDto addSubject(SubjectInputDto dto) {
        Subject subject = new Subject(dto);
        repo.save(subject);
        return new SubjectOutputDto(subject);
    }

    @Override
    public SubjectOutputDto findById(String id) throws NotFoundException {
        Subject subject = repo.findById(id).orElseThrow(
                () -> new NotFoundException("No se ha encontrado el ID: " + id));
        return new SubjectOutputDto(subject);
    }

    @Override
    public SubjectOutputDto updateSubject(String id, SubjectInputDto dto) throws NotFoundException {
        Subject subject = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado el ID: " + id));
        subject.update(dto);
        return new SubjectOutputDto(repo.save(subject));
    }

    @Override
    public String deleteById(String id) {
        if(repo.findById(id).isPresent()) {
            repo.deleteById(id);
            return "El id: " + id + " ha sido eliminado.";
        } else {
            throw new NotFoundException("No se ha encontrado el ID: " + id);
        }

    }

}
