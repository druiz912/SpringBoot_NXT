package com.druiz.bp1.content.application;

import com.druiz.bp1.content.application.port.PersonService;
import com.druiz.bp1.content.domain.Person;
import com.druiz.bp1.content.infrastructure.controller.dto.input.PersonInputDto;
import com.druiz.bp1.content.infrastructure.controller.dto.output.PersonOutputDto;
import com.druiz.bp1.exceptions.NotFoundException;
import com.druiz.bp1.exceptions.UnprocesableException;
import com.druiz.bp1.content.infrastructure.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepo repo;

    @Override
    public PersonOutputDto addPerson(PersonInputDto pInputDto) throws NotFoundException, UnprocesableException{
        this.validate(pInputDto);
        Person person = new Person(pInputDto);
        repo.save(person);
        return new PersonOutputDto(person);
    }

    @Override
    public PersonOutputDto findPersonById(int id) {
       Person person = repo.findById(id).orElseThrow(() -> new NotFoundException("Not found person with id: " + id));
        return new PersonOutputDto(person);
    }

    @Override
    public List<PersonOutputDto> findPersonByName(String name) {
        List<PersonOutputDto> listAuxOutputDto = new ArrayList<>();
        repo.findByName(name).forEach(person -> {
            listAuxOutputDto.add(new PersonOutputDto(person));
        });
        return listAuxOutputDto;
    }

    @Override
    public List<PersonOutputDto> findAllPerson() {
        List<PersonOutputDto> listAuxOutputDto = new ArrayList<>();
        repo.findAll().forEach(person -> {
            PersonOutputDto personOutputDTO = new PersonOutputDto(person);
            listAuxOutputDto.add(personOutputDTO);
        });
        return listAuxOutputDto;
    }


    @Override
    public PersonOutputDto updatePerson(@PathVariable(value = "id") int id, PersonInputDto pInputDto){
        this.validate(pInputDto);
        Person person = repo.findById(id).orElseThrow(()-> new NotFoundException("Not found person with id: " + id));
        person.update(pInputDto);
        repo.save(person);
        return new PersonOutputDto(person);
    }

    @Override
    public void deletePerson(int id) {
        Person personEnt = repo.findById(id).orElseThrow(() -> new NotFoundException("No se ha encontrado el ID"));
        repo.delete(personEnt);
        repo.save(personEnt);
    }

    private void validate(PersonInputDto personInputDTO) throws UnprocesableException{
        String usuario = personInputDTO.getUser();
        if (usuario==null) throw new UnprocesableException("Error: user is null.");
        if (usuario.length()<6 || usuario.length()>10) throw new UnprocesableException("Error: user length must be between 6 and 10 characters");
        if (personInputDTO.getPassword()==null) throw new UnprocesableException("Error: password is null.");
        if (personInputDTO.getName()==null) throw new UnprocesableException("Error: name is null.");
        if (personInputDTO.getCompanyMail()==null) throw new UnprocesableException("Error: Company_email is null.");
        if (personInputDTO.getPersonalMail()==null) throw new UnprocesableException("Error: Personal_email is null.");
        if (personInputDTO.getCity()==null) throw new UnprocesableException("Error: City is null.");
        if (personInputDTO.getCreatedDate()==null) throw new UnprocesableException("Error: Created_date is null");
    }

    }


