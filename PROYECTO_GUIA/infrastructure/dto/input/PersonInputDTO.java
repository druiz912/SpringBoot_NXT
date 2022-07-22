package com.bosonit.exercises.bs8.infrastructure.dto.input;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class PersonInputDTO {
    private int idPerson;
    private String user;
    @NotNull
    private String password;
    @Length()
    private String name;
    private String surname;
    private String companyEmail;
    private String personalEmail;
    private String city;
    private boolean active;
    private Date createDate;
    private String imageUrl;
    private Date terminationDate;
}
