package com.druiz.bosonit.db2.subject.domain;

import com.druiz.bosonit.db2.student.domain.Student;
import com.druiz.bosonit.db2.subject.infrastructure.controller.dto.input.SubjectInputDto;
import com.druiz.bosonit.db2.teacher.domain.Teacher;
import com.druiz.bosonit.db2.utils.sequences.StringPrefixedSequenceIdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "estudiante_asignatura")
@NoArgsConstructor
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
    private String idSubject;  // [pk, increment]

    @ManyToOne
    @JoinColumn(name = "teacherID")
    Teacher teacher;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "STUDENTS_SUBJECTS",
            joinColumns = @JoinColumn(name = "subjectID"),
            inverseJoinColumns = @JoinColumn(name = "studentID")
    )
    List<Student> students = new ArrayList<>();
    @Column(name = "name")
    private String name;

    @Column(name = "comentarios")
    private String comment;

    @Column(name = "initial_date", nullable = false)
    private LocalDate initialDate;

    @Column(name = "finish_date", nullable = false)
    private LocalDate finishDate;


    public void update(SubjectInputDto subjectInputDto) {
        if (subjectInputDto == null) return;
        comment = subjectInputDto.getComment();
        initialDate = LocalDate.parse(subjectInputDto.getInitialDate().toString());
        finishDate = LocalDate.parse(subjectInputDto.getFinishDate().toString());
    }

    public void enrollStudent(Student studentEntity){
        students.add(studentEntity);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "idSubject='" + idSubject + '\'' +
                ", teacher=" + teacher +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", initialDate=" + initialDate +
                ", finishDate=" + finishDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Subject subject = (Subject) o;
        return idSubject != null && Objects.equals(idSubject, subject.idSubject);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
