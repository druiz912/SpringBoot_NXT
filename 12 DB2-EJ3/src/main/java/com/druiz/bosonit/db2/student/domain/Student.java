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
@ToString
@RequiredArgsConstructor
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
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ToString.Exclude
    private Person persona;

    @ManyToOne
    @JoinColumn(name = "profesorEntity")
    private Teacher teacher;


    @Column(name = "horas_por_semana", nullable = false)
    private Integer numHoursWeek;

    @Column(name = "comentarios")
    private String comments;


    @Column(name = "rama", nullable = false)
    private String branch;


    //EXPLICACION EMAR
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudios")
    @ToString.Exclude
    private List<Subject> estudios = new ArrayList<>();

    public void addEstudios (Subject studentCourse){
        estudios.add(studentCourse);
    }


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
    public int hashCode() {
        return getClass().hashCode();
    }
}
