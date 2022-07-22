package com.bosonit.exercises.bs8.application.port;

import com.bosonit.exercises.bs8.infrastructure.dto.output.PersonOutputDTO;

import java.util.List;

public interface IGetPersonPort {
    PersonOutputDTO getPersonId(int id) throws Exception;
    List<PersonOutputDTO> getPersonName(String user) throws Exception;
    List<PersonOutputDTO> getPersons();
}
