package com.druiz.bosonit.reactive.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Document(collection = "Personas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    private int id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;
    @NotNull
    private int age;
    @NotEmpty
    private String mail;
    @NotNull
    private int phone;
    @NotNull
    private int dni;
    @NotEmpty
    private String photo;

}
