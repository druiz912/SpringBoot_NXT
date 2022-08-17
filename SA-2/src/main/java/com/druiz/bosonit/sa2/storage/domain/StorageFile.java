package com.druiz.bosonit.sa2.storage.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StorageFile {
    @Id
    @GeneratedValue
    int id;
    String nombre;
    Date fechaSubida;
    String categoria;
    String url;
}
