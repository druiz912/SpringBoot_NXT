package com.druiz.bosonit;

import java.util.Optional;

public class Persona {

    private String name;
    private String city;
    private String age;

    private static final String UNKNOWN = "Desconocido";

    public Persona(String name, String city, String age) {
        this.name = (name == "") ? null : name;
        this.city = (city == "") ? null : city;
        this.age  = (age == "") ? null : age;
    }

    public String getName() {
        Optional<String> maybeName = Optional.ofNullable(this.name);
        return maybeName.orElse(UNKNOWN);
    }

    public String getCity() {
        Optional<String> maybeCity = Optional.ofNullable(this.city);
        return maybeCity.orElse(UNKNOWN);
    }

    public String getAge() {
        Optional<String> maybeAge = Optional.ofNullable(this.age);
        return maybeAge.orElse(UNKNOWN);
    }

    @Override
    public String toString() {
        return "Nombre: " + getName() + ". "
                + "Poblacion: " + getCity() + ". "
                + "Edad: " + getAge();
    }

    public void addOneAge() {
        this.age =  String.valueOf(Integer.parseInt(getAge()) +1);
    }
}

