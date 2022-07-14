package main;

import clase.Persona;

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

        File file = new File("datos.txt"); //Pillando el archivo
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        //Bucle para leer archivo
        while ((cadenaAux = br.readLine()) != null) {
            partes = cadenaAux.split(":"); //separacion
            Optional<Integer> edad = partes.length <= 2 ? Optional.empty() : Optional.of(Integer.parseInt(partes[2]));
            persona = new Persona(Optional.ofNullable(partes[0]),
                    Optional.of(partes[1].equals("") ? "desconocida" : partes[1]), edad);
            listaPersonas.add(persona);
        }

        listaPersonas.stream()
                .filter(per ->
                {
                    if (per.getEdad().isEmpty())
                        return false;
                    else
                        return per.getEdad().get() < 25; //CONDICIÓN
                })
                .forEach(p -> System.out.println(p));
    }
}
