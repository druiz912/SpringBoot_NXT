package com.druiz.ej3.student.domain;

import com.druiz.ej3.persona.domain.PersonaEntity;
import com.druiz.ej3.profesor.domain.ProfesorEntity;
import com.druiz.ej3.shared.sequences.StringPrefixedSequenceIdGenerator;
import com.druiz.ej3.student.infrastructure.dto.input.StudentInputDto;
import com.druiz.ej3.student.infrastructure.dto.output.StudentOutputDto;
import com.druiz.ej3.student.infrastructure.dto.output.StudentOutputDtoSimple;
import com.druiz.ej3.studentCourse.domain.StudentCourseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sq")
    @GenericGenerator(

            name = "student_sq",
            strategy = "com.druiz.ej3.shared.sequences.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter
                            (name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),

                    @org.hibernate.annotations.Parameter
                            (name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "EST"),

                    @org.hibernate.annotations.Parameter
                            (name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            })
    @Column(name = "id_student")
    private String id_student;  // [pk, increment]

    //RELACION 1:1 CON LA TABLA PERSONA
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    private PersonaEntity persona;

    @ManyToOne
    @JoinColumn(name = "profesorEntity")
    private ProfesorEntity profesorEntity;


    @Column(name = "horas_por_semana", nullable = false)
    private Integer num_hours_week;

    @Column(name = "comentarios")
    private String coments;


    @Column(name = "rama", nullable = false)
    private String branch;


    //EXPLICACION EMAR
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudios")
    private List<StudentCourseEntity> estudios = new ArrayList<>();

    public void addEstudios (StudentCourseEntity studentCourse){
        estudios.add(studentCourse);
    }

    public StudentEntity(StudentInputDto stuInputDto) {
        if (stuInputDto == null) return;
        id_student = stuInputDto.getId_student();
        num_hours_week = Integer.valueOf(stuInputDto.getNum_hours_week());
        coments = stuInputDto.getComentarios();
        branch = stuInputDto.getBranch();
    }

    public void update(StudentInputDto studentInputDTO) {
        if (studentInputDTO == null) return;
        num_hours_week = Integer.valueOf(studentInputDTO.getNum_hours_week());
        coments = studentInputDTO.getComentarios();
        branch = studentInputDTO.getBranch();
        //estudios = studentInputDTO.getEstudios();
    }

}
