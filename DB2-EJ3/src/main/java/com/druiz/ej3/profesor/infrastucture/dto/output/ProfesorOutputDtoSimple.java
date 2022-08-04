package com.druiz.ej3.profesor.infrastucture.dto.output;

import com.druiz.ej3.profesor.domain.ProfesorEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
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
