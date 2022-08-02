package com.druiz.ej3.profesor.infrastucture.dto.input;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class ProfesorInputDto implements Serializable {
    private String id_profesor;
    private String id_persona;
    private String comentarios;
    private String branch;
}
