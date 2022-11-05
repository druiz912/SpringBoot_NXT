package com.druiz.bosonit.docker.teacher.domain;

import com.druiz.bosonit.docker.person.domain.Person;
import com.druiz.bosonit.docker.teacher.infrastucture.dto.input.TeacherInputDto;
import com.druiz.bosonit.docker.utils.sequences.StringPrefixedSequenceIdGenerator;
import com.druiz.bosonit.docker.subject.domain.Subject;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "teacher")
@NoArgsConstructor
public class Teacher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profesorSeq")
    @GenericGenerator(

            name = "profesorSeq",
            strategy = "StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "PRO"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d")
            })
    private String id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Person person;

    @OneToMany(mappedBy = "idTeacher", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_person", referencedColumnName = "id")
    private Set<Subject> courseStudent = new HashSet<>();
    @Column(name = "comentarios")
    private String comments;
    @Column(name = "rama", nullable = false)
    private String branch;

    public Teacher(TeacherInputDto dto) {
        id = dto.getId();
        comments = dto.getComments();
        branch = dto.getBranch();
    }

    public void update(TeacherInputDto dto) {
        comments = dto.getComments();
        branch = dto.getBranch();
    }

}
