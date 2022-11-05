package com.druiz.bosonit.docker.student.infrastructure.controller.dto.output;

import com.druiz.bosonit.docker.student.domain.Student;
import com.druiz.bosonit.docker.subject.domain.Subject;
import com.druiz.bosonit.docker.subject.infrastructure.dto.output.SubjectOutputDto;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class StudentOutputDto implements Serializable {
    private String id;
    private String idPerson;
    private List<SubjectOutputDto> subject = new ArrayList<>();
    private int numHoursWeek;
    private String comments;
    private String branch;

    public StudentOutputDto(Student st) {
        if (st == null) return;
        this.id = st.getId();
        this.idPerson = st.getIdPerson().getId();
        if (st.getEstudios() != null) {
            for (Subject subject : st.getEstudios()) { //Conversion de StudentEnt
                this.subject.add(new SubjectOutputDto(subject));
            }
            this.numHoursWeek = st.getNumHoursWeek();
            this.comments = st.getComments();
            this.branch = st.getBranch();
        }


    }
}
