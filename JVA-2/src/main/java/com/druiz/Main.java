package com.druiz;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Persona> listaPersonas = new ArrayList<>();
        Persona persona; //Objeto persona
        String cadenaAux = "";
        //Array para almacenar los datos cuando los dividamos
        String[] partes = new String[2];
        String URL_FILE = "C:\\Users\\daniel.ruiz\\Desktop\\Dani\\JAVA\\Ejercicios\\Ejercicio2\\out\\artifacts\\Ejercicio2_jar\\datos.txt";

        File file = new File(URL_FILE); //Pillando el archivo
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        //Bucle para leer archivo
        while ((cadenaAux = br.readLine()) != null) {
            partes = cadenaAux.split(":"); //separacion
            Optional<Integer> edad = partes.length <= 2 ? Optional.empty() : Optional.of(Integer.parseInt(partes[2]));
            persona = new Persona("Daniel","Jerez","22");
            listaPersonas.add(persona);
        }

        listaPersonas.stream()
                .filter(person -> person != null
                        && !person.edad().equals("Desconocida")
                        && Integer.parseInt(person.edad()) < 25)
                .forEach(person -> System.out.println(person.toString()));
    }
}
