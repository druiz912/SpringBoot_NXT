package com.druiz.bosonit.db2.utils.mappers;

import com.druiz.bosonit.db2.person.domain.Person;
import com.druiz.bosonit.db2.person.infrastructure.controller.dto.input.PersonInputDto;
import com.druiz.bosonit.db2.person.infrastructure.controller.dto.output.PersonOutputDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person toEntity (PersonInputDto personInputDto);

    PersonOutputDto toOutput (Person person);
}
