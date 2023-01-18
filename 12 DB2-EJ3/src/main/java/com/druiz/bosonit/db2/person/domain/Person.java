package com.druiz.bosonit.db2.person.domain;

import com.druiz.bosonit.db2.utils.exceptions.UnprocesableException;
import com.druiz.bosonit.db2.person.infrastructure.controller.dto.input.PersonInputDto;
import com.druiz.bosonit.db2.teacher.domain.Teacher;
import com.druiz.bosonit.db2.utils.sequences.StringPrefixedSequenceIdGenerator;
import com.druiz.bosonit.db2.student.domain.Student;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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
    private String idPerson;

    @Size(min = 6, max = 10)
    @Column(name = "usuario", nullable = false)
    private String user;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "company_mail", nullable = false)
    private String companyMail;

    @Column(name = "personal_mail", nullable = false)
    private String personalMail;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "activo")
    private Boolean active;

    @Column(name = "created_Date", nullable = false)
    private Date createdDate;

    @Column(name = "imagen_url")
    private String imagenUrl;

    @Column(name = "termination_date")
    private Date terminationDate;



    public Person(PersonInputDto pInputDto) {
        try {
            user = pInputDto.getUser();
            password = pInputDto.getPassword();
            name = pInputDto.getName();
            surname = pInputDto.getSurname();
            companyMail = pInputDto.getCompanyMail();
            personalMail = pInputDto.getPersonalMail();
            city = pInputDto.getCity();
            active = pInputDto.getActive();
            createdDate = pInputDto.getCreatedDate();
            imagenUrl = pInputDto.getImagenUrl();
            terminationDate = pInputDto.getTerminationDate();
        }catch(Exception e){
            throw new UnprocesableException(e.getMessage());
        }
    }

    public void update(PersonInputDto pInputDto) {
        try {
            user = pInputDto.getUser();
            password = pInputDto.getPassword();
            name = pInputDto.getName();
            surname = pInputDto.getSurname();
            companyMail = pInputDto.getCompanyMail();
            personalMail = pInputDto.getPersonalMail();
            city = pInputDto.getCity();
            active = pInputDto.getActive();
            createdDate = pInputDto.getCreatedDate();
            imagenUrl = pInputDto.getImagenUrl();
            terminationDate = pInputDto.getTerminationDate();
        }catch (Exception e){
            throw new UnprocesableException("No se ha podido completar la conversión de datos");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Person that = (Person) o;
        return idPerson != null && Objects.equals(idPerson, that.idPerson);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

