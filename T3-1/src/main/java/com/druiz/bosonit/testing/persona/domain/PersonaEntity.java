package com.druiz.bosonit.testing.persona.domain;
import javax.persistence.*;


@Entity
@Table(name = "persona")
public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
     private int id;
    @Column(name = "nombre",nullable = false)
    private String firstName;
    @Column(name = "apellido", nullable = false)
    private String lastName;
    @Column(name = "ciudad",nullable = false)
    String city;
    @Column(name = "telefono",nullable = false)
    private String phone;
    @Column(name = "email", nullable = false)
    private String email;

    public PersonaEntity() {}

    public PersonaEntity(String firstName, String lastName, String city, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.phone = phone;
        this.email = email;
    }
    public PersonaEntity(int id, String firstName, String lastName, String city, String phone, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public String getPhone() {
        return phone;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
