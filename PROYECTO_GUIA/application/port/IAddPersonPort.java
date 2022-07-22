package com.bosonit.exercises.bs8.application.port;

import com.bosonit.exercises.bs8.infrastructure.dto.input.PersonInputDTO;
import com.bosonit.exercises.bs8.infrastructure.dto.output.PersonOutputDTO;

public interface IAddPersonPort {
    PersonOutputDTO addPerson(PersonInputDTO personInputDTO) throws Exception;
}
