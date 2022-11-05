package com.druiz.bosonit.dba1.person.infrastructure.controllers.dto;

import java.time.LocalDate;

public record PersonInputDto(
        String user_persona,
        String name,
        String surname,
        String address,
        String email,
        LocalDate created
) {}
