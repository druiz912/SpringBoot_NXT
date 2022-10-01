package com.druiz.bosonit.dba1.persona.application;


import com.druiz.bosonit.dba1.persona.domain.PersonaEntity;
import com.druiz.bosonit.dba1.persona.infrastructure.dto.PersonaInputDto;
import com.druiz.bosonit.dba1.persona.infrastructure.dto.PersonaOutputDto;
import com.druiz.bosonit.dba1.persona.infrastructure.repo.PersonaRepo;
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
public class PersonaServiceImpl implements PersonaService {

    static final int page = 10;

    @Autowired
    PersonaRepo personaRepo;

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public PersonaOutputDto postPersona(PersonaInputDto personaInputDTO) {
        PersonaEntity personEntity = new PersonaEntity(personaInputDTO);
        personaRepo.save(personEntity);
        PersonaOutputDto personaOutputDto = new PersonaOutputDto(personEntity);
        return personaOutputDto;
    }
    @Override
    public List<PersonaOutputDto> getAllPersonas(int pageNumber) {
        List<PersonaOutputDto> personaOutputDTOList;

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PersonaEntity> query = cb.createQuery(PersonaEntity.class);
        Root<PersonaEntity> personaEntityRoot = query.from(PersonaEntity.class);
        CriteriaQuery<PersonaEntity> select = query.select(personaEntityRoot);
        TypedQuery<PersonaEntity> typedQuery = entityManager.createQuery(select);
        typedQuery.setFirstResult((pageNumber*10)-10);
        typedQuery.setMaxResults(page);
        List<PersonaEntity> paginatedPersons = typedQuery.getResultList();

        personaOutputDTOList = paginatedPersons.stream().map(PersonaOutputDto::new).collect(Collectors.toList());
        return personaOutputDTOList;
    }

    @Override
    public boolean existsPersona(int id) {
        return personaRepo.existsById(id);
    }

    @Override
    public PersonaOutputDto getPersonaByID(int id) {
        PersonaEntity p = personaRepo.findById(id).orElseThrow();
        PersonaOutputDto personaOutput = new PersonaOutputDto(p);
        return personaOutput;
    }

    @Override
    public List<PersonaOutputDto> getPersonasByName(String name) {
        List<PersonaOutputDto> listaNombres = new ArrayList<>();

        for (PersonaEntity p: personaRepo.findByName(name)){
            PersonaOutputDto outputDtoFor = new PersonaOutputDto(p);
            listaNombres.add(outputDtoFor);
        }
        return listaNombres;
    }

    @Override
    public List<PersonaOutputDto> criteriaQuery(Optional<String> name, Optional<String> user,
                                                Optional<String> surname,Optional<String>address,
                                                Optional<Date> creation_date, String dateCondition,
                                                Optional<String> sorting) {
        //Instanciando CriteriaBuilder y asociando PersonaEntity a CriteriaBuilder y a Root
        List<PersonaOutputDto> listaPersonaOutputDto = new ArrayList<>();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PersonaEntity> query = cb.createQuery(PersonaEntity.class);
        Root<PersonaEntity> personaEntityRoot = query.from(PersonaEntity.class);
        //Predicates --> Condicionales de la query
        List<Predicate> predicates = new ArrayList<>();
        name.ifPresent(s -> predicates.add(cb.like(personaEntityRoot.get("name"), s)));
        surname.ifPresent(s -> predicates.add(cb.like(personaEntityRoot.get("surname"), s)));
        user.ifPresent(s -> predicates.add(cb.like(personaEntityRoot.get("user_persona"), s)));

        //fecha
        if (creation_date.isPresent()){
            switch (dateCondition){
                case "pre":
                    predicates.add(cb.lessThan(personaEntityRoot.get("created_date"), creation_date.get()));
                    break;

                case "pos":
                    predicates.add(cb.greaterThan(personaEntityRoot.get("created_date"), creation_date.get()));
                    break;

                default:
                    predicates.add(cb.equal(personaEntityRoot.get("created_date"), creation_date.get()));
            }
        }
        //Order Bys
        if(sorting.isPresent()){
            switch (sorting.get()) {
                case "user":
                    query.select(personaEntityRoot).where(cb.and(predicates.toArray
                            (new Predicate[predicates.size()]))).orderBy(cb.asc(personaEntityRoot.get("user_persona")));
                    break;
                case "name":
                    query.select(personaEntityRoot).where(cb.and(predicates.toArray
                            (new Predicate[predicates.size()]))).orderBy(cb.asc(personaEntityRoot.get("name")));
                    break;
                case "surname":
                    query.select(personaEntityRoot).where(cb.and(predicates.toArray
                            (new Predicate[predicates.size()]))).orderBy(cb.asc(personaEntityRoot.get("surname")));
            }
        } else query.select(personaEntityRoot).where(cb.and(predicates.toArray
                (new Predicate[predicates.size()])));

        entityManager.createQuery(query).getResultList().forEach(personEntity -> {
            listaPersonaOutputDto.add(new PersonaOutputDto(personEntity));
        });
        return listaPersonaOutputDto;
    }



    @Override
    public PersonaOutputDto updatePersona(int id, PersonaInputDto personaInputDTO) {
        PersonaEntity personaEntity = personaRepo.findById(id).orElse(null);
        personaEntity.updateEntity(personaInputDTO);
        personaRepo.save(personaEntity);

        PersonaOutputDto personaOutputDTO = new PersonaOutputDto(personaEntity);
        return personaOutputDTO;
    }

    @Override
    public PersonaOutputDto deletePersona(int id) {
        PersonaOutputDto personaOutputDto = getPersonaByID(id);
        personaRepo.deleteById(id);
        return personaOutputDto;
    }
}



