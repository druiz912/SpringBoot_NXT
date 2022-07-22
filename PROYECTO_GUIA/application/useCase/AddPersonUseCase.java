package com.bosonit.exercises.bs8.application.useCase;

import com.bosonit.exercises.bs8.application.port.IAddPersonPort;
import com.bosonit.exercises.bs8.domain.Person;
import com.bosonit.exercises.bs8.infrastructure.dto.input.PersonInputDTO;
import com.bosonit.exercises.bs8.infrastructure.dto.output.PersonOutputDTO;
import com.bosonit.exercises.bs8.infrastructure.repository.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddPersonUseCase implements IAddPersonPort {
    @Autowired
    IPersonRepository iPersonRepository;

    @Override
    public PersonOutputDTO addPerson(PersonInputDTO personInputDTO) throws Exception {
        Person person = new Person(personInputDTO);
        iPersonRepository.save(person);
        return new PersonOutputDTO(person);
    }
}
