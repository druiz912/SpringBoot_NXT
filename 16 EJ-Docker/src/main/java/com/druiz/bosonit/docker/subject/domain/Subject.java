package com.druiz.bosonit.docker.subject.domain;

import com.druiz.bosonit.docker.student.domain.Student;
import com.druiz.bosonit.docker.subject.infrastructure.dto.input.SubjectInputDto;
import com.druiz.bosonit.docker.teacher.domain.Teacher;
import com.druiz.bosonit.docker.utils.sequences.StringPrefixedSequenceIdGenerator;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "asignaturas")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "courseSeq")
    @GenericGenerator(

            name = "courseSeq",
            strategy = "StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "JKL"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d")
            })
    private String id;  // [pk, increment]

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor", referencedColumnName = "id")
    private Teacher idTeacher;

    //EXPLICACION OSCAR
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Student> listaEstudiantes = new ArrayList<>();

    @Column(name = "comentarios")
    private String comments;

    @Column(name = "initial_date", nullable = false)
    private LocalDate initialDate;

    @Column(name = "finish_date", nullable = false)
    private LocalDate finishDate;

    public Subject(SubjectInputDto subjectInputDto) {
        if (subjectInputDto == null) return;
        id = subjectInputDto.getId();
        comments = subjectInputDto.getComment();
        initialDate = subjectInputDto.getInitialDate();
        finishDate = subjectInputDto.getFinishDate();
    }

    public void update(SubjectInputDto subjectInputDto) {
        if (subjectInputDto == null) return;
        //asignatura = studentCourseInputDto.getAsignatura();
        comments = subjectInputDto.getComment();
        initialDate = subjectInputDto.getInitialDate();
        finishDate = subjectInputDto.getFinishDate();
    }

    public void addEstudios(Subject subject) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Subject subject = (Subject) o;
        return id != null && Objects.equals(id, subject.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
