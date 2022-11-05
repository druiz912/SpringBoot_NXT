package com.druiz.bosonit.docker.person.domain;

import com.druiz.bosonit.docker.utils.exceptions.UnprocesableException;
import com.druiz.bosonit.docker.person.infrastructure.controller.dto.input.PersonaInputDto;
import com.druiz.bosonit.docker.teacher.domain.Teacher;
import com.druiz.bosonit.docker.utils.sequences.StringPrefixedSequenceIdGenerator;
import com.druiz.bosonit.docker.student.domain.Student;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person")
public class Person implements Serializable {
    @Id

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personaseq")

    @GenericGenerator(

            name = "personaseq",

            strategy = "StringPrefixedSequenceIdGenerator",

            parameters = {

                    @org.hibernate.annotations.Parameter(
                            name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),

                    @org.hibernate.annotations.Parameter(
                            name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "PER"),

                    @org.hibernate.annotations.Parameter(
                            name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%02d")

            })
    private String id;

    // RELACION CON Student 1:1
    @OneToOne(mappedBy = "idPerson", cascade = CascadeType.ALL)
    private Student student;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Teacher teacher;

    @Size(min = 6, max = 10)
    @Column(name = "Usuario", nullable = false)
    private String user;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Surname", nullable = false)
    private String surname;

    @Column(name = "Company_email", nullable = false)
    private String companyMail;

    @Column(name = "Personal_email", nullable = false)
    private String personalMail;

    @Column(name = "Ciudad", nullable = false)
    private String city;

    @Column(name = "Activo")
    private Boolean active;

    @Column(name = "Created_Date", nullable = false)
    private LocalDate createdDate;

    @Column(name = "Imagen_URL")
    private String imageUrl;

    @Column(name = "termination_Date")
    private LocalDate terminationDate;

    public Person(PersonaInputDto dto) {
        try {
            id = dto.getId();
            user = dto.getUser();
            password = dto.getPassword();
            name = dto.getName();
            surname = dto.getSurname();
            companyMail = dto.getCompanyMail();
            personalMail = dto.getPersonalMail();
            city = dto.getCity();
            active = dto.getActive();
            createdDate = dto.getCreatedDate();
            imageUrl = dto.getImageUrl();
            terminationDate = dto.getTerminationDate();
        }catch(Exception e){
            throw new UnprocesableException("Error! in constructor!");
        }
    }

    public void update(PersonaInputDto pInputDto) {
        try {
            id = pInputDto.getId();
            user = pInputDto.getUser();
            password = pInputDto.getPassword();
            name = pInputDto.getName();
            surname = pInputDto.getSurname();
            companyMail = pInputDto.getCompanyMail();
            personalMail = pInputDto.getPersonalMail();
            city = pInputDto.getCity();
            active = pInputDto.getActive();
            createdDate = pInputDto.getCreatedDate();
            imageUrl = pInputDto.getImageUrl();
            terminationDate = pInputDto.getTerminationDate();
        }catch (Exception e){
            throw new UnprocesableException("Error! In update!");
        }
    }

}

