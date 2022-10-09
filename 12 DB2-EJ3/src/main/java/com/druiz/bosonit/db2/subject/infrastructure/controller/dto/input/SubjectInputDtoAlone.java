package com.druiz.bosonit.db2.subject.infrastructure.controller.dto.input;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class SubjectInputDtoAlone implements Serializable {
    private List<String> id = new ArrayList<>();
}
