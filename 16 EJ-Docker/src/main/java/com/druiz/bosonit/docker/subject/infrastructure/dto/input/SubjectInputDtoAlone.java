package com.druiz.bosonit.docker.subject.infrastructure.dto.input;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class SubjectInputDtoAlone implements Serializable {
    private List<String> id = new ArrayList<>();
}
