package com.bosonit.exercises.bs8.application.useCase;

import com.bosonit.exercises.bs8.application.port.IDeletePersonPort;
import com.bosonit.exercises.bs8.domain.Person;
import com.bosonit.exercises.bs8.infrastructure.repository.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

@Service
public class DeletePersonUseCase implements IDeletePersonPort {
    @Autowired
    IPersonRepository iPersonRepository;

    @Override
    public void deletePerson(int id) throws Exception {
        Person person = iPersonRepository.findById(id)
                .orElseThrow(() -> new HttpMediaTypeNotAcceptableException("No user found with ID " + id));
        iPersonRepository.delete(person);
    }
}
