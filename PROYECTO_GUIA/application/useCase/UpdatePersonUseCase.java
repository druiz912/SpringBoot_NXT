package com.bosonit.exercises.bs8.application.useCase;

import com.bosonit.exercises.bs8.application.port.IUpdatePersonPort;
import com.bosonit.exercises.bs8.domain.Person;
import com.bosonit.exercises.bs8.infrastructure.dto.input.PersonInputDTO;
import com.bosonit.exercises.bs8.infrastructure.dto.output.PersonOutputDTO;
import com.bosonit.exercises.bs8.infrastructure.repository.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UpdatePersonUseCase implements IUpdatePersonPort {
    @Autowired
    IPersonRepository iPersonRepository;

    @Override
    public PersonOutputDTO updatePerson(int id, PersonInputDTO personInputDTO) throws Exception {
        Person foundPerson = iPersonRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "No user found with ID " + id));
        foundPerson.update(personInputDTO);
        iPersonRepository.save(foundPerson);
        return new PersonOutputDTO(foundPerson);
    }
}
