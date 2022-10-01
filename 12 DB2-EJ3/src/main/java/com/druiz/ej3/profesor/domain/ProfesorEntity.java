package com.druiz.ej3.profesor.domain;

import com.druiz.ej3.persona.domain.PersonaEntity;
import com.druiz.ej3.profesor.infrastucture.dto.input.ProfesorInputDto;
import com.druiz.ej3.shared.sequences.StringPrefixedSequenceIdGenerator;
import com.druiz.ej3.studentCourse.domain.StudentCourseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "profesor")
@NoArgsConstructor
public class ProfesorEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profesorSeq")
    @GenericGenerator(

            name = "profesorSeq",
            strategy = "com.druiz.ej3.shared.sequences.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "PRO"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d")
            })
    private String id_profesor;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_persona")
    private PersonaEntity persona;

    @OneToMany(mappedBy = "profesor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<StudentCourseEntity> courseStudent = new HashSet<>();
    @Column(name = "comentarios")
    private String comments;
    @Column(name = "rama", nullable = false)
    private String branch;

    public ProfesorEntity(ProfesorInputDto profesorInputDTO) {
        id_profesor = profesorInputDTO.getId_profesor();
        comments = profesorInputDTO.getComentarios();
        branch = profesorInputDTO.getBranch();
    }

    public void update(ProfesorInputDto profesorInputDTO) {
        comments = profesorInputDTO.getComentarios();
        branch = profesorInputDTO.getBranch();
    }

}
