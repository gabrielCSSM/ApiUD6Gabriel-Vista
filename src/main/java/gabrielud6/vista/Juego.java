package gabrielud6.vista;

import java.util.List;

public class Juego {

    long id;
    String nombre;
    List<Puntuacion> puntuacions;

    public Juego() {
    }

    public Juego(String nombre) {
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Puntuacion> getPuntuacions() {
        return puntuacions;
    }

    public void setPuntuacions(List<Puntuacion> puntuacions) {
        this.puntuacions = puntuacions;
    }

    @Override
    public String toString() {
        return nombre;
    }

}

