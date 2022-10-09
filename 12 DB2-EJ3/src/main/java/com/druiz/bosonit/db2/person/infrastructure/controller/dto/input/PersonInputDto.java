package com.druiz.bosonit.db2.person.infrastructure.controller.dto.input;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
public class PersonInputDto  {
    @NotEmpty
    private String user;
    @NotNull
    private String password;
    @NotEmpty
    private String name;
    private String surname;
    @NotEmpty
    @Email(message = "Bad syntax in mail (example -> admin@gmail.com")
    private String companyMail;
    @NotEmpty
    @Email(message = "Bad syntax in mail (example -> admin@gmail.com")
    private String personalMail;
    @NotEmpty
    private String city;
    private Boolean active;
    @NotNull
    private Date createdDate;
    private String imagenUrl;
    private Date terminationDate;
}