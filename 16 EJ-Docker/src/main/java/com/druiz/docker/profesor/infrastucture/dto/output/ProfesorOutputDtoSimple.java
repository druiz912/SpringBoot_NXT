package com.druiz.docker.profesor.infrastucture.dto.output;

import com.druiz.docker.profesor.domain.ProfesorEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorOutputDtoSimple {

    String id_persona;
    int id_profesor;
    String comments;
    String branch;

    public ProfesorOutputDtoSimple(ProfesorEntity profesor) {
    }
}
