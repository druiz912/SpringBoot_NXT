package com.druiz.docker.persona.domain;

import com.druiz.docker.exceptions.UnprocesableException;
import com.druiz.docker.persona.infrastructure.controller.dto.input.PersonaInputDto;
import com.druiz.docker.profesor.domain.ProfesorEntity;
import com.druiz.docker.shared.sequences.StringPrefixedSequenceIdGenerator;
import com.druiz.docker.student.domain.StudentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "persona")
public class PersonaEntity implements Serializable {
    @Id

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personaseq")

    @GenericGenerator(

            name = "personaseq",

            strategy = "com.druiz.docker.shared.sequences.StringPrefixedSequenceIdGenerator",

            parameters = {

                    @org.hibernate.annotations.Parameter(
                            name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),

                    @org.hibernate.annotations.Parameter(
                            name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "PER"),

                    @org.hibernate.annotations.Parameter(
                            name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%02d")

            })
    private String id_persona;

    // RELACION CON Student 1:1
    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private StudentEntity student;

    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ProfesorEntity profesor;

    @Size(min = 6, max = 10)
    @Column(name = "Usuario", nullable = false)
    private String usuario;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Surname", nullable = false)
    private String surname;

    @Column(name = "Company_email", nullable = false)
    private String company_email;

    @Column(name = "Personal_email", nullable = false)
    private String personal_email;

    @Column(name = "Ciudad", nullable = false)
    private String city;

    @Column(name = "Activo", nullable = true)
    private Boolean active;

    @Column(name = "Created_Date", nullable = false)
    private Date created_date;

    @Column(name = "Imagen_URL")
    private String imagen_url;

    @Column(name = "Date")
    private Date termination_date;



    public PersonaEntity(PersonaInputDto pInputDto) {
        try {
            id_persona = pInputDto.getId_persona();
            usuario = pInputDto.getUsuario();
            password = pInputDto.getPassword();
            name = pInputDto.getName();
            surname = pInputDto.getSurname();
            company_email = pInputDto.getCompany_email();
            personal_email = pInputDto.getPersonal_email();
            city = pInputDto.getCity();
            active = pInputDto.getActive();
            created_date = pInputDto.getCreated_date();
            imagen_url = pInputDto.getImagen_url();
            termination_date = pInputDto.getTermination_date();
        }catch(Exception e){
            throw new UnprocesableException(e.getMessage());
        }
    }

    public void update(PersonaInputDto pInputDto) {
        try {
            id_persona = pInputDto.getId_persona();
            usuario = pInputDto.getUsuario();
            password = pInputDto.getPassword();
            name = pInputDto.getName();
            surname = pInputDto.getSurname();
            company_email = pInputDto.getCompany_email();
            personal_email = pInputDto.getPersonal_email();
            city = pInputDto.getCity();
            active = pInputDto.getActive();
            created_date = pInputDto.getCreated_date();
            imagen_url = pInputDto.getImagen_url();
            termination_date = pInputDto.getTermination_date();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }

}

