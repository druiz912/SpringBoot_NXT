package com.bosonit.exercises.bs8.application.port;

import com.bosonit.exercises.bs8.infrastructure.dto.input.PersonInputDTO;
import com.bosonit.exercises.bs8.infrastructure.dto.output.PersonOutputDTO;

public interface IUpdatePersonPort {
    PersonOutputDTO updatePerson(int id, PersonInputDTO personInputDTO) throws Exception;
}
