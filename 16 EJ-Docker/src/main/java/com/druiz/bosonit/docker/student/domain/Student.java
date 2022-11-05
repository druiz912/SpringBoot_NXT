package com.druiz.bosonit.docker.student.domain;

import com.druiz.bosonit.docker.person.domain.Person;
import com.druiz.bosonit.docker.student.infrastructure.controller.dto.input.StudentInputDto;
import com.druiz.bosonit.docker.subject.domain.Subject;
import com.druiz.bosonit.docker.teacher.domain.Teacher;
import com.druiz.bosonit.docker.utils.sequences.StringPrefixedSequenceIdGenerator;
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
    private String id;  // [pk, increment]

    //RELACION 1:1 CON LA TABLA PERSONA
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Person idPerson;

    @ManyToOne
    @JoinColumn(name = "id_teacher", referencedColumnName = "id")
    private Teacher idTeacher;

    @Column(name = "horas_por_semana", nullable = false)
    private Integer numHoursWeek;

    @Column(name = "comentarios")
    private String comments;

    @Column(name = "rama", nullable = false)
    private String branch;

    //EXPLICACION EMAR
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudios")
    private List<Subject> estudios = new ArrayList<>();

    public Student(StudentInputDto stuInputDto) {
        if (stuInputDto == null) return;
        id = stuInputDto.getId();
        numHoursWeek = Integer.valueOf(stuInputDto.getNumHoursWeek());
        comments = stuInputDto.getComments();
        branch = stuInputDto.getBranch();
    }

    public void update(StudentInputDto studentInputDTO) {
        if (studentInputDTO == null) return;
        numHoursWeek = Integer.valueOf(studentInputDTO.getNumHoursWeek());
        comments = studentInputDTO.getComments();
        branch = studentInputDTO.getBranch();
        //estudios = studentInputDTO.getEstudios();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Student student = (Student) o;
        return id != null && Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
