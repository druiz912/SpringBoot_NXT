package com.druiz.bs9.profesor.infrastucture.dto.output;

import com.druiz.bs9.profesor.domain.ProfesorEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ProfesorOutputDto implements Serializable {
    private String id_profesor;
    private String id_persona;
    private String comentarios;
    private String branch;

    public ProfesorOutputDto(ProfesorEntity profesorEntity) {
        if (profesorEntity == null) return;
        id_profesor = profesorEntity.getId_profesor();
        id_persona = profesorEntity.getPersona().getId_persona();
        comentarios = profesorEntity.getComments();
        branch = profesorEntity.getBranch();
    }
}
