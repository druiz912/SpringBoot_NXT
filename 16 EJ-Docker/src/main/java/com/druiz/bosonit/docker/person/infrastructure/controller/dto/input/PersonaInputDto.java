package com.druiz.bosonit.docker.person.infrastructure.controller.dto.input;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaInputDto {
    private String id;
    @NotEmpty
    private String user;
    @NotNull
    private String password;
    private String name;
    private String surname;
    @Email(message = "Bad syntax mail!")
    private String companyMail;
    @Email(message = "Bad syntax mail!")
    private String personalMail;
    @NotEmpty(message = "Citi cannot be empty!")
    private String city;
    private Boolean active;
    private LocalDate createdDate;
    private String imageUrl;
    private LocalDate terminationDate;
}