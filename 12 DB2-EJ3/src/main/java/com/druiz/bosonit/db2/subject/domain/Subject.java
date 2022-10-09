package com.druiz.bosonit.db2.subject.domain;

import com.druiz.bosonit.db2.teacher.domain.Teacher;
import com.druiz.bosonit.db2.utils.sequences.StringPrefixedSequenceIdGenerator;
import com.druiz.bosonit.db2.student.domain.Student;
import com.druiz.bosonit.db2.subject.infrastructure.controller.dto.input.SubjectInputDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor")
    private Teacher profesor;

    //EXPLICACION OSCAR
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Student> listaEstudiantes = new ArrayList<>();

    @Column(name = "name")
    private String name;

    @Column(name = "comentarios")
    private String comment;

    @Column(name = "initial_date", nullable = false)
    private Date initialDate;

    @Column(name = "finish_date", nullable = false)
    private Date finishDate;

    public void update(SubjectInputDto subjectInputDto) {
        if (subjectInputDto == null) return;
        comment = subjectInputDto.getComment();
        initialDate = subjectInputDto.getInitialDate();
        finishDate = subjectInputDto.getFinishDate();
    }
}
