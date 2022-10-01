package com.druiz.crudvalidation.persona;

import com.druiz.crudvalidation.persona.dtos.PersonaInputDto;
import com.druiz.crudvalidation.persona.dtos.PersonaOutputDto;
import com.druiz.crudvalidation.persona.interfaces.IPersonaRepo;
import com.druiz.crudvalidation.persona.interfaces.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaServiceImpl implements IPersonaService {

    @Autowired
    IPersonaRepo repo;

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto personaInputDTO) throws Exception {
        Persona persona = new Persona(personaInputDTO);
        repo.save(persona);
        return new PersonaOutputDto(persona);
    }

    @Override
    public PersonaOutputDto getPersonaId(int id) throws Exception {
        Persona persona = repo.findById(id).orElseThrow(() -> new Exception("No person found with ID"));
        return new PersonaOutputDto(persona);
    }

    @Override
    public List<PersonaOutputDto> getPersonaName(String username) throws Exception {
        List<PersonaOutputDto> listaTemporal = new ArrayList<>();
        repo.findByName(username).forEach(persona -> {
            listaTemporal.add(new PersonaOutputDto(persona));
        });
        return listaTemporal;
    }

    @Override
    public List<PersonaOutputDto> getPersonas() {
        List<PersonaOutputDto> listaTemporal = new ArrayList<>();
        repo.findAll().forEach(persona -> {
            PersonaOutputDto personOutputDTO = new PersonaOutputDto(persona);
            listaTemporal.add(personOutputDTO);
        });
        return listaTemporal;
    }


    @Override
    public void updatePersona(int id, PersonaInputDto p) {

        repo.findAll().forEach(persona -> {
            if (persona.getId_persona() == id) {
                persona.setId_persona(p.getId_persona());
                persona.setName(p.getName());
                persona.setSurname(p.getSurname());
                persona.setUsuario(p.getUsuario());
                persona.setPassword(p.getPassword());
                persona.setPersonal_email(p.getPersonal_email());
                persona.setCompany_email(p.getCompany_email());
                persona.setCity(p.getCity());
                persona.setImagen_url(p.getImagen_url());
                persona.setCreated_date(p.getCreated_date());
                persona.setTermination_date(p.getTermination_date());
                persona.setActive(p.isActive());

                repo.saveAndFlush(persona);
            }
        });
    }

    @Override
    public void deletePersona(int id) throws Exception {
        PersonaOutputDto persona;
        persona = getPersonaId(id);
        if(persona == null){
            System.out.println("No se encuentra el identificador: " + id);
        }else{
            repo.deleteById(id);
        }

    }


}
