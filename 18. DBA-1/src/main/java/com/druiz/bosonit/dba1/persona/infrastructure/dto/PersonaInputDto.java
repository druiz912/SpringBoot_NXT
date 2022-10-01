package com.druiz.bosonit.dba1.persona.infrastructure.dto;

import java.util.Date;

public record PersonaInputDto(
        String user_persona,
        String name,
        String surname,
        String address,
        String email,
        Date created
) {}
