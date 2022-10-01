package com.druiz.bosonit;


import java.io.IOException;

public class StreamFileApplication {

    private static final String FICHEROLECTURA = "src/main/resources/static/datos.txt";
    private static final String FICHEROESCRIBIR = "src/main/resources/static/write.txt";

    public static void main(String[] args) throws IOException {
        ReadFile readFile = new ReadFile();
        // Fichero del ejercicio
        readFile.Lectura(FICHEROLECTURA);
        // Prueba de escritura en fichero
        // readFile.Escribir(FICHEROESCRIBIR);
        // Lectura de la escritura
        // readFile.Lectura(FICHEROESCRIBIR);
    }
}

