package com.druiz.bosonit.webflux.model;

import org.springframework.data.annotation.Id;

import java.util.HashMap;
import java.util.Map;


public class Person {

    @Id
    private int id;
    private String name;
    private String surname;
    private String mail;

    public Person surname(String surname){
        this.surname = surname;
        return this;
    }

    Map<String,String> key = new HashMap<>();

    


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
