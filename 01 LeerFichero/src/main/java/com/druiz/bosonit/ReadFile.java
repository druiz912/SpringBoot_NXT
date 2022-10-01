package com.druiz.bosonit;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ReadFile {

    //PATH --> RUTA DEL ARCHIVO

    public void Escribir(String path) throws IOException {
        try {
            File file = new File(path);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            printWriter.write("Manuel:Gonzalez:21");
            printWriter.append("\nDaniel:Ruiz:22");
            printWriter.append("\nEmar:Martinez:90");

            printWriter.close();
            bufferedWriter.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error" + e);
        }
    }

    public void Lectura(String path) throws FileNotFoundException {
        // Creamos archivo File
        List<Persona> personList = new ArrayList<>();
        String [] split;
        String linea = "";

        try{
            FileReader file = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(file);

            while ((linea = bufferedReader.readLine()) != null){
                split = linea.split(":");
                Persona persona = new Persona();

                if (split.length<=3){
                    persona.setName(split[0]);

                    if (split[1].isEmpty()){
                        persona.setCity("Desconocida");
                    }else {
                        persona.setCity(split[1]);
                    }

                }else {
                    persona.setName("Desconocida");
                }

                if (split.length > 2){
                    persona.setAge(split[2]);
                }else {
                    persona.setAge("Desconocida");
                }

                personList.add(persona);

            }
            bufferedReader.close();

        }catch (IOException e){
            System.out.println("Error al leer el archivo");
        }

        personList.stream()
                .filter(p-> p.getName()!=null && p.getCity() != null && !Objects.equals(p.getAge(), "Desconocida") && Integer.parseInt(p.getAge()) < 25)

                .forEach(p-> System.out.println("Nombre:" + p.getName() + " Población:" + p.getCity()+ " Edad:" + p.getAge()));

    }

}


