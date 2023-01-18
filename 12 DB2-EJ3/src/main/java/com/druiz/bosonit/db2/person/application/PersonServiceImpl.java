package com.druiz.bosonit.db2.person.application;

import com.druiz.bosonit.db2.person.application.port.PersonService;
import com.druiz.bosonit.db2.person.domain.Person;
import com.druiz.bosonit.db2.person.infrastructure.controller.dto.input.PersonInputDto;
import com.druiz.bosonit.db2.person.infrastructure.controller.dto.output.PersonOutputDto;
import com.druiz.bosonit.db2.person.infrastructure.repository.PersonRepo;
import com.druiz.bosonit.db2.student.infrastructure.repo.StudentRepo;
import com.druiz.bosonit.db2.teacher.infrastucture.controller.dto.output.TeacherOutputDto;
import com.druiz.bosonit.db2.teacher.infrastucture.repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepo personRepository;

    @Autowired
    StudentRepo studentRepository;

    @Autowired
    TeacherRepo teacherRepository;

    @Autowired
    EntityManager entityManager;

    @Override
    public List<PersonOutputDto> findAllPersons() {
        List<PersonOutputDto> personaOutputDTOList = new ArrayList<>();

        for (Person personEntity : personRepository.findAll()) {
            PersonOutputDto auxOutputDTO = new PersonOutputDto(personEntity);
            personaOutputDTOList.add(auxOutputDTO);
        }

        return personaOutputDTOList;
    }

    @Override
    public boolean existsPerson(int id) {
        return personRepository.existsById(String.valueOf(id));
    }

    @Override
    public PersonOutputDto findPersonById(String id) {
        if (!personRepository.existsById(String.valueOf(id)))
            throw new RuntimeException("Person with id: " + id + " does not exists.");

        Person personEntity = personRepository.findById(String.valueOf(id)).orElse(null);
        return new PersonOutputDto(personEntity);
    }

    @Override
    public List<PersonOutputDto> findPersonByName(String name) {
        List<PersonOutputDto> personaOutputDTOList = new ArrayList<>();

        for (Person personEntity : personRepository.findByName(name)) {
            PersonOutputDto auxOutputDTO = new PersonOutputDto(personEntity);
            personaOutputDTOList.add(auxOutputDTO);
        }

        return personaOutputDTOList;
    }

    @Override
    public List<PersonOutputDto> getPersonWithCriteriaQuery(
            Optional<String> name,
            Optional<String> surname,
            Optional<String> company,
            Optional<String> teacherName) {

        List<PersonOutputDto> personaOutputDTOList = new ArrayList<>();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> query = cb.createQuery(Person.class);
        Root<Person> personaEntityRoot = query.from(Person.class);
        List<Predicate> predicates = new ArrayList<>();
        //
        name.ifPresent(s -> predicates.add(cb.like(personaEntityRoot.get("name"), s)));
        //
        surname.ifPresent(s -> predicates.add(cb.like(personaEntityRoot.get("surname"), s)));
        //
        company.ifPresent(s -> predicates.add(cb.like(personaEntityRoot.get("company_email"), s)));
        //
        query.select(personaEntityRoot).where(cb.and(predicates.toArray(new Predicate[0])));

        entityManager.createQuery(query).getResultList().forEach(
                personEntity -> personaOutputDTOList.add(
                        new PersonOutputDto(personEntity)));
        return personaOutputDTOList;
    }

    @Override
    public PersonOutputDto addPerson(PersonInputDto personInputDTO) throws RuntimeException {
        Person personEntity = new Person(personInputDTO);

        personRepository.save(personEntity);
        return new PersonOutputDto(personEntity);
    }

    @Override
    public PersonOutputDto updatePerson(String id, PersonInputDto personaInputDTO) {
        /*
            We could then simply get the entity from the database,
            make the change, and use the save() method as before.
         */
        Person personInDB = personRepository.findById(String.valueOf(id)).orElse(null);
        personInDB.update(personaInputDTO);
        personRepository.save(personInDB);

        return new PersonOutputDto(personInDB);
    }

    @Override
    public PersonOutputDto deletePerson(String id) {
        PersonOutputDto personaOutputDTO = findPersonById(id);
        personRepository.deleteById(String.valueOf(id));
        return personaOutputDTO;
    }

    public ResponseEntity<?> getTeacherWithTemplate(String id) {
        ResponseEntity<?> responseEntity;
        try {
            responseEntity = new RestTemplate().getForEntity("http://localhost:8080/teacher/" + id, TeacherOutputDto.class);
        } catch (HttpClientErrorException e1) {
            responseEntity = new ResponseEntity<>("HTTP code is not 2XX. Server responded: " + e1.getResponseBodyAsString(), e1.getStatusCode());
        } catch (RestClientException e) {
            responseEntity = new ResponseEntity<>("Server didnt respond: ", HttpStatus.I_AM_A_TEAPOT);
        }
        return responseEntity;
    }
}
