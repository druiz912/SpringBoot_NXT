package com.druiz.bosonit.db2.student.domain;

import com.druiz.bosonit.db2.person.domain.Person;
import com.druiz.bosonit.db2.student.infrastructure.controller.dto.input.StudentInputDto;
import com.druiz.bosonit.db2.subject.domain.Subject;
import com.druiz.bosonit.db2.teacher.domain.Teacher;
import com.druiz.bosonit.db2.utils.sequences.StringPrefixedSequenceIdGenerator;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sq")
    @GenericGenerator(

            name = "student_sq",
            strategy = "StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter
                            (name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),

                    @org.hibernate.annotations.Parameter
                            (name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "EST"),

                    @org.hibernate.annotations.Parameter
                            (name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            })
    @Column(name = "id_student")
    private String idStudent;

    //RELACION 1:1 CON LA TABLA PERSONA
    @OneToOne
    @JoinColumn(name = "person_id")
    Person person;

    @Column(name = "horas_por_semana", nullable = false)
    private Integer numHoursWeek;

    @Column(name = "comentarios")
    private String comments;

    @Column(name = "rama", nullable = false)
    private String branch;

    @ManyToMany(mappedBy = "students")
    List<Subject> subjects = new ArrayList<>();


    public void update(StudentInputDto studentInputDTO) {
        if (studentInputDTO == null) return;
        numHoursWeek = Integer.valueOf(studentInputDTO.getNumHoursWeek());
        comments = studentInputDTO.getComments();
        branch = studentInputDTO.getBranch();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Student student = (Student) o;
        return idStudent != null && Objects.equals(idStudent, student.idStudent);
    }

    @Override
    public String toString() {
        return "Student{" +
                "idStudent='" + idStudent + '\'' +
                ", numHoursWeek=" + numHoursWeek +
                ", comments='" + comments + '\'' +
                ", branch='" + branch + '\'' +
                '}';
    }


    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
