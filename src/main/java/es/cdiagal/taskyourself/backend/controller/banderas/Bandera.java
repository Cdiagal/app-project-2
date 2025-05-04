package es.cdiagal.taskyourself.backend.controller.banderas;

import javafx.scene.image.Image;

/**
 * Clase que reperesenta la gestión de banderas e idiomas de la aplicacion de manera visual.
 * Se le pasa un idioma y una imagen que representa la bandera de ese idioma.
 * Se utiliza para mostrar la bandera en la interfaz grafica de usuario.
 */
public class Bandera {
    private final String nombre;
    private final Image icono;

    /**
     * Constructor de la clase Bandera.
     * @param nombre Nombre del idioma.
     * @param icono Imagen que representa la bandera del idioma.
     */
    public Bandera(String nombre, Image icono){
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
        return "";
    }
}

