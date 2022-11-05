package com.druiz.bosonit.dba1.person.application;


import com.druiz.bosonit.dba1.person.domain.Person;
import com.druiz.bosonit.dba1.person.infrastructure.controllers.dto.PersonInputDto;
import com.druiz.bosonit.dba1.person.infrastructure.controllers.dto.PersonOutputDto;
import com.druiz.bosonit.dba1.person.infrastructure.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    static final int page = 10;

    @Autowired
    PersonRepo personRepo;

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public PersonOutputDto addPerson(PersonInputDto dto) {
        Person person = new Person(dto);
        personRepo.save(person);
        return new PersonOutputDto(person);
    }
    @Override
    public List<PersonOutputDto> findAllPersons(int pageNumber) {
        List<PersonOutputDto> listOutput;
        /** USO DE CRITERIA BUILDER **/
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> query = cb.createQuery(Person.class);
        Root<Person> personaEntityRoot = query.from(Person.class);
        CriteriaQuery<Person> select = query.select(personaEntityRoot);
        TypedQuery<Person> typedQuery = entityManager.createQuery(select);
        typedQuery.setFirstResult((pageNumber*10)-10);
        typedQuery.setMaxResults(page);
        List<Person> paginatedPersons = typedQuery.getResultList();

        listOutput = paginatedPersons.stream().map(PersonOutputDto::new).collect(Collectors.toList());
        return listOutput;
    }

    @Override
    public boolean existsPerson(int id) {
        return personRepo.existsById(id);
    }

    @Override
    public PersonOutputDto findPersonById(int id) {
        Person p = personRepo.findById(id).orElseThrow();
        return new PersonOutputDto(p);
    }

    @Override
    public List<PersonOutputDto> findPersonByName(String name) {
        List<PersonOutputDto> listaNombres = new ArrayList<>();

        for (Person p: personRepo.findByName(name)){
            PersonOutputDto outputDtoFor = new PersonOutputDto(p);
            listaNombres.add(outputDtoFor);
        }
        return listaNombres;
    }

    @Override
    public List<PersonOutputDto> criteriaQuery(Optional<String> name, Optional<String> user,
                                               Optional<String> surname, Optional<String>address,
                                               Optional<Date> creationDate, String dateCondition,
                                               Optional<String> sorting) {
        //Instanciando CriteriaBuilder y asociando PersonaEntity a CriteriaBuilder y a Root
        List<PersonOutputDto> listaPersonOutputDto = new ArrayList<>();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> query = cb.createQuery(Person.class);
        Root<Person> personRoot = query.from(Person.class);
        //Predicates --> Condicionales de la query
        List<Predicate> predicates = new ArrayList<>();
        name.ifPresent(s -> predicates.add(cb.like(personRoot.get("name"), s)));
        surname.ifPresent(s -> predicates.add(cb.like(personRoot.get("surname"), s)));
        user.ifPresent(s -> predicates.add(cb.like(personRoot.get("user_persona"), s)));

        //fecha
        if (creationDate.isPresent()){
            switch (dateCondition){
                case "pre":
                    predicates.add(cb.lessThan(personRoot.get("createdDate"), creationDate.get()));
                    break;

                case "pos":
                    predicates.add(cb.greaterThan(personRoot.get("createdDate"), creationDate.get()));
                    break;

                default:
                    predicates.add(cb.equal(personRoot.get("createdDate"), creationDate.get()));
            }
        }
        //Order Bys
        if(sorting.isPresent()){
            switch (sorting.get()) {
                case "user":
                    query.select(personRoot).where(cb.and(predicates.toArray
                            (new Predicate[predicates.size()]))).orderBy(cb.asc(personRoot.get("user")));
                    break;
                case "name":
                    query.select(personRoot).where(cb.and(predicates.toArray
                            (new Predicate[predicates.size()]))).orderBy(cb.asc(personRoot.get("name")));
                    break;
                case "surname":
                    query.select(personRoot).where(cb.and(predicates.toArray
                            (new Predicate[predicates.size()]))).orderBy(cb.asc(personRoot.get("surname")));
            }
        } else query.select(personRoot).where(cb.and(predicates.toArray
                (new Predicate[predicates.size()])));

        entityManager.createQuery(query).getResultList().forEach(p -> listaPersonOutputDto.add(new PersonOutputDto(p)));
        return listaPersonOutputDto;
    }



    @Override
    public PersonOutputDto updatePerson(int id, PersonInputDto dto) {

        if(personRepo.findById(id).isPresent()){
            Person person = new Person(dto);
            person.updateEntity(dto);
            personRepo.save(person);
            return new PersonOutputDto(person);
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public PersonOutputDto deletePerson(int id) {
        PersonOutputDto personOutputDto = findPersonById(id);
        personRepo.deleteById(id);
        return personOutputDto;
    }
}



