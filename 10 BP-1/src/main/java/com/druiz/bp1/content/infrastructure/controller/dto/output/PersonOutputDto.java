package com.druiz.bp1.content.infrastructure.controller.dto.output;
import com.druiz.bp1.content.domain.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonOutputDto {
    private int id;
    private String user;
    private String password;
    private String name;
    private String surname;
    private String companyMail;
    private String personalMail;
    private String city;
    private boolean active;
    private Date createdDate;
    private String imageUrl;
    private Date terminationDate;

    public PersonOutputDto(Person person)
    {
        this.id = person.getId();
        this.user = person.getUser();
        this.name = person.getName();
        this.surname = person.getSurname();
        this.companyMail = person.getCompanyMail();
        this.personalMail = person.getPersonalMail();
        this.city = person.getCity();
        this.active = person.isActive();
        this.createdDate = person.getCreatedDate();
        this.imageUrl = person.getImageUrl();
        this.terminationDate = person.getTerminationDate();
    }


}
