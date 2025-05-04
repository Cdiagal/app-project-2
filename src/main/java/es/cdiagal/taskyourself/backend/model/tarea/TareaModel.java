package es.cdiagal.taskyourself.backend.model.tarea;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Clase TareaModel
 * 
 * Representa una tarea en el sistema. Almacena información sobre el título,
 * descripción, fechas relevantes y el estado de la tarea (completada o pendiente).
 * 
 * @author cdiagal
 * @version 1.0.0
 */

public class TareaModel {
    private int idTarea;
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaObjetivo;
    private boolean tareaCompletada;


    //Constructor vacío.
    public TareaModel(){

    }

    //Constructor con el id de la clase.
    public TareaModel(int idTarea){
        this.idTarea = idTarea;
    }

    //Constructor con todas las propiedades de la clase.
    public TareaModel(int idTarea, String titulo,String descripcion, LocalDateTime fechaCreacion, LocalDateTime fechaObjetivo, boolean tareaCompletada){
        this.idTarea = idTarea;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaObjetivo = fechaObjetivo;
        this.tareaCompletada = tareaCompletada;
    }

    //Getters y setters.

    public int getIdTarea() {
        return this.idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaObjetivo() {
        return this.fechaObjetivo;
    }

    public void setFechaObjetivo(LocalDateTime fechaObjetivo) {
        this.fechaObjetivo = fechaObjetivo;
    }

    public boolean isTareaCompletada() {
        return this.tareaCompletada;
    }

    public boolean getTareaCompletada() {
        return this.tareaCompletada;
    }

    public void setTareaCompletada(boolean tareaCompletada) {
        this.tareaCompletada = tareaCompletada;
    }

    //toString y equals()


    @Override
    public String toString() {
        return "{" +
            " idTarea='" + getIdTarea() + "'" +
            ", titulo='" + getTitulo() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", fechaCreacion='" + getFechaCreacion() + "'" +
            ", fechaObjetivo='" + getFechaObjetivo() + "'" +
            ", tareaCompletada='" + isTareaCompletada() + "'" +
            "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TareaModel)) {
            return false;
        }
        TareaModel tareaModel = (TareaModel) o;
        return Objects.equals(idTarea, tareaModel.idTarea) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTarea);
    }


}
