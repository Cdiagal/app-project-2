package es.cdiagal.taskyourself.backend.controller.banderas;

import javafx.scene.image.Image;

public class Banderas {
    private final String nombre;
    private final Image icono;

    public Banderas(String nombre, Image icono){
        this.nombre = nombre;
        this.icono = icono;
    }

    public String getNombre() {
        return nombre;
    }

    public Image getIcono() {
        return icono;
    }

    @Override
    public String toString() {
        return nombre;
    }
}

