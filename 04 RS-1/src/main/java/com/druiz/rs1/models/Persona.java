package com.druiz.rs1.models;


import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

public class Persona {
        private static AtomicInteger count = new AtomicInteger(0);
        private Integer id;
        private String name;
        private String city;
        private Integer age;

        public Persona(String name, String city, String age) {
                this.name = (name.equals("")) ? null : name;
                this.city = (city.equals("")) ? null : city;
                this.age  = (age.equals(""))  ? null : Integer.parseInt(age);
                this.id = count.incrementAndGet();
        }

        public Integer getId() {
                return this.id;
        }

        public String getName() {
                return this.name;
        }

        public String getCity() {
                return this.city;
        }

        public Integer getAge() {
                return this.age;
        }

        public void setName(String name) {
                this.name = name;
        }

        public void setCity(String city) {
                this.city = city;
        }

        public void setAge(Integer age) {
                this.age = age;
        }

        @Override
        public String toString() {
                return "Person{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", city='" + city + '\'' +
                        ", age=" + age +
                        '}';
        }
}



