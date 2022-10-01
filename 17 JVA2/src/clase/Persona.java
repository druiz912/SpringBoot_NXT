package clase;

import java.util.Optional;

public class Persona {
    private Optional<String> nombre;
    private Optional<String> poblacion;
    private Optional<Integer> edad;

    public Persona(Optional<String> nombre, Optional<String> poblacion, Optional<Integer> edad) {
        this.nombre = nombre;
        this.poblacion = poblacion;
        this.edad = edad;
    }

    /**
     * getters y setters
     */
    public Optional<String> getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = Optional.ofNullable(nombre);
    }

    public Optional<String> getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = Optional.ofNullable(poblacion);
    }

    public Optional<Integer> getEdad() {
        return edad;
    }

    public void setEdad(Optional<Integer> edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return
                " Mi nombre: " + nombre.get() +
                        " Poblacion: " + poblacion.get() +
                        " Edad: " + edad.get();
    }
}
