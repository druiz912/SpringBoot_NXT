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
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
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
    private String idTeacher;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_persona")
    @ToString.Exclude
    private Person persona;

    @OneToMany(mappedBy = "profesor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Subject> courseStudent = new HashSet<>();
    @Column(name = "comentarios")
    private String comments;
    @Column(name = "rama", nullable = false)
    private String branch;


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
}
