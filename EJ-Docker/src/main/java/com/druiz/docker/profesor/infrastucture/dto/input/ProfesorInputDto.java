package com.druiz.docker.profesor.infrastucture.dto.input;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class ProfesorInputDto implements Serializable {
    private String id_profesor;
    private String id_persona;
    private String comentarios;
    private String branch;
}
