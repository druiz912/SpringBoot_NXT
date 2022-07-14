package com.druiz.bosonit;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.*;


@RestController
public class Controller {

    @GetMapping
    String hello(){
        return "Hello world! ";
    }

    @GetMapping("users")
    Persona getPersona(String name, String ciudad, int edad){
        Persona per = new Persona();
        per.setNombre("Daniel");
        per.setCiudad("Jerez");
        per.setEdad(22);
        return per;
    }

    @GetMapping("users/{nombre}")
    String getHello(@PathVariable String nombre){
       return "Hello " + nombre;
    }

    @PostMapping("useradd")
    Persona newPerson(@RequestBody ObjectNode requestObject) {
        Persona newPerson =
                new Persona(
                        requestObject.get("nombre").asText(),
                        requestObject.get("ciudad").asText(),
                        requestObject.get("edad").asInt());
        newPerson.setSumEd();
        return newPerson;

    }

    class Persona {

        private String nombre;
        private String ciudad;
        private int edad;

        public Persona(String nombre, String ciudad, int edad) {
            this.nombre = nombre;
            this.ciudad = ciudad;
            this.edad = edad;
        }

        public Persona() {

        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getCiudad() {
            return ciudad;
        }

        public void setCiudad(String ciudad) {
            this.ciudad = ciudad;
        }

        public int getEdad() {
            return edad;
        }

        public void setEdad(int edad) {
            this.edad = edad;
        }

        public int setSumEd(){
            return getEdad() + 1;
        }
    }
}

