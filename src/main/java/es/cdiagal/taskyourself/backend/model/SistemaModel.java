package es.cdiagal.taskyourself.backend.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Clase SistemaModel
 * 
 * Representa el sistema principal encargado de gestionar notificaciones
 * en la aplicación. No almacena usuarios ni tareas, pero actúa como un
 * servicio para generar y enviar notificaciones.
 * 
 * @author cdiagal
 * @version 1.0.0
 */


public class SistemaModel {

    private List<String> notificaciones;

    //Constructor de la clase.
    public SistemaModel (){
        this.notificaciones = new ArrayList<>();
    }

    //Getters y setters.

    public List<String> getNotificaciones() {
        return this.notificaciones;
    }

    public void setNotificaciones(List<String> notificaciones) {
        this.notificaciones = notificaciones;
    }

    //toString y equals().

    @Override
    public String toString() {
        return "{" +
            " notificaciones='" + getNotificaciones() + "'" +
            "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SistemaModel)) {
            return false;
        }
        SistemaModel sistemaModel = (SistemaModel) o;
        return Objects.equals(notificaciones, sistemaModel.notificaciones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notificaciones);
    }
    
}
