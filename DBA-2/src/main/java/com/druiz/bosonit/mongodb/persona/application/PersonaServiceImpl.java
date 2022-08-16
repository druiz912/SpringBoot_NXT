package com.druiz.bosonit.mongodb.persona.application;

import com.druiz.bosonit.mongodb.config.exceptions.UnprocesableException;
import com.druiz.bosonit.mongodb.persona.application.port.PersonaService;
import com.druiz.bosonit.mongodb.persona.domain.PersonaEntity;
import com.druiz.bosonit.mongodb.persona.infrastructure.dto.input.PersonaInputDto;
import com.druiz.bosonit.mongodb.persona.infrastructure.dto.output.PersonaOutputDto;
import com.druiz.bosonit.mongodb.persona.infrastructure.repo.PersonaRepository;
import io.swagger.codegen.v3.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto personaInputDTO) {
        try{
            PersonaEntity personaEntity = new PersonaEntity(personaInputDTO);
            PersonaOutputDto pOutputDto = new PersonaOutputDto(personaEntity);
            mongoTemplate.save(personaEntity,"personas");
            return pOutputDto;
        }catch (Exception e) {
            throw new UnprocesableException(e.getMessage());
        }
    }
    @Override
    public List<PersonaOutputDto> getPersonaByID(int id) {
        List<PersonaOutputDto> personaOutputDTOList = new ArrayList<>();
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        mongoTemplate.find(query, PersonaEntity.class)
                .forEach(persona -> personaOutputDTOList.add(new PersonaOutputDto(persona)));
        return personaOutputDTOList;
    }

    @Override
    public List<PersonaOutputDto> getAllPersonas() {
        List<PersonaOutputDto> personaOutputDTOList = new ArrayList<>();
        mongoTemplate.findAll(PersonaEntity.class, "personas").forEach(persona -> personaOutputDTOList.add(new PersonaOutputDto(persona)));
        return personaOutputDTOList;
    }
    @Override
    public List<PersonaOutputDto> getPersonaByName(String name) {
        List<PersonaOutputDto> personaOutputDTOList = new ArrayList<>();
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        mongoTemplate.find(query, PersonaEntity.class,"personas").forEach(persona -> personaOutputDTOList.add(new PersonaOutputDto(persona)));
        return personaOutputDTOList;
    }

    @Override
    public PersonaOutputDto updatePersona(int id, PersonaInputDto personaInputDTO) {
        try {
            Update update = new Update();
            update.set("id",id);
            update.set("name",personaInputDTO.name());
            update.set("surname",personaInputDTO.surname());
            update.set("address", personaInputDTO.address());
            update.set("age",personaInputDTO.age());
            mongoTemplate.update(PersonaEntity.class);
            return new PersonaOutputDto(new PersonaEntity(personaInputDTO));
        } catch (Exception e){
            e.printStackTrace();
            throw new NotFoundException("No se ha encontrado a la persona con ID: " + id);
        }

    }
    @Override
    public void deleteByID(int id) {
        if (mongoTemplate.exists(Query.query(Criteria.where("id").is(id)), PersonaEntity.class))
            mongoTemplate.remove(Query.query(Criteria.where("id").is(id)), PersonaEntity.class);
        else throw new NotFoundException("No se ha encontrado la Persona con ID: " + id);
    }


}
