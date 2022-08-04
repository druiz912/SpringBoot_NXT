package com.druiz.bs12.studentCourse.domain;

import com.druiz.bs12.shared.sequences.StringPrefixedSequenceIdGenerator;
import com.druiz.bs12.profesor.domain.ProfesorEntity;
import com.druiz.bs12.student.domain.StudentEntity;
import com.druiz.bs12.studentCourse.infrastructure.dto.input.StudentCourseInputDto;
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
public class StudentCourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "courseSeq")
    @GenericGenerator(

            name = "courseSeq",
            strategy = "com.druiz.bs12.shared.sequences.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "JKL"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d")
            })
    private String id_asignatura;  // [pk, increment]

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor")
    private ProfesorEntity profesor;

    //EXPLICACION OSCAR
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StudentEntity> listaEstudiantes = new ArrayList<>();

    @Column(name = "comentarios")
    private String comments;

    @Column(name = "initial_date", nullable = false)
    private Date initial_date;

    @Column(name = "finish_date", nullable = false)
    private Date finish_date;

    public StudentCourseEntity(StudentCourseInputDto studentCourseInputDto) {
        if (studentCourseInputDto == null) return;
        id_asignatura = studentCourseInputDto.getId_asignatura();
        comments = studentCourseInputDto.getComment();
        initial_date = studentCourseInputDto.getInitial_date();
        finish_date = studentCourseInputDto.getFinish_date();
    }

    public void update(StudentCourseInputDto studentCourseInputDto) {
        if (studentCourseInputDto == null) return;
        //asignatura = studentCourseInputDto.getAsignatura();
        comments = studentCourseInputDto.getComment();
        initial_date = studentCourseInputDto.getInitial_date();
        finish_date = studentCourseInputDto.getFinish_date();
    }

    public void addEstudios(StudentCourseEntity studentCourseEntity) {
    }
}
