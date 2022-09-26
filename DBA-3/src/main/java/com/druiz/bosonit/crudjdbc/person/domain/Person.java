package com.druiz.bosonit.crudjdbc.person.domain;


public record Person(Integer id,
                    String name,
                    String surname,
                    String mail) {
}