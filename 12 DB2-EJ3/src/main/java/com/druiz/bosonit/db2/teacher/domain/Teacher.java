package com.druiz.bosonit.db2.teacher.domain;

import com.druiz.bosonit.db2.person.domain.Person;
import com.druiz.bosonit.db2.subject.domain.Subject;
import com.druiz.bosonit.db2.teacher.infrastucture.controller.dto.input.TeacherInputDto;
import com.druiz.bosonit.db2.utils.sequences.StringPrefixedSequenceIdGenerator;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "teachers")
@NoArgsConstructor
@AllArgsConstructor
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
    private String idTeacher;

    @OneToOne
    @JoinColumn(name = "personID")
    Person person;

    @OneToMany
    @JoinColumn(name = "subjectID")
    List<Subject> subjects;

    @Column(name = "comentarios")
    private String comments;
    @Column(name = "rama", nullable = false)
    private String branch;

    public Teacher(TeacherInputDto teacherInputDTO, Person personEntity){
        comments = teacherInputDTO.getComentarios();
        branch = teacherInputDTO.getBranch();
        person = personEntity;
    }

    public void update(TeacherInputDto teacherInputDTO) {
        comments = teacherInputDTO.getComentarios();
        branch = teacherInputDTO.getBranch();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Teacher teacher = (Teacher) o;
        return idTeacher != null && Objects.equals(idTeacher, teacher.idTeacher);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "idTeacher='" + idTeacher + '\'' +
                ", person=" + person +
                ", comments='" + comments + '\'' +
                ", branch='" + branch + '\'' +
                '}';
    }
}
