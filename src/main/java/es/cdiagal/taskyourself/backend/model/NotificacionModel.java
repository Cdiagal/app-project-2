package es.cdiagal.taskyourself.backend.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Clase NotificacionModel.
 * 
 * Representa una notificación generada en el sistema. Almacena información
 * como el mensaje, destinatario, tipo de notificación y el estado (si fue
 * enviada o no).
 * 
 * @author cdiagal
 * @version 1.0.0
 */


public class NotificacionModel {
    private String idNotificacion;
    private String destinatario;
    private String mensaje;
    private String tipo;
    private boolean confirmaEnviada;
    private LocalDateTime fechaCreacion;
    

    //Constructor vacío de la clase.
    public NotificacionModel(){

    }

    //Constructor con el id de la Clase.
    public NotificacionModel(String idNotificacion){
        this.idNotificacion = idNotificacion;
    }

    //Contructor con las propiedades de la clase.
    public NotificacionModel(String idNotificacion, String destinatario, String mensaje, String tipo, boolean confirmaEnviada, LocalDateTime fechaCreacion){
        this.idNotificacion = idNotificacion;
        this.destinatario = destinatario;
        this.mensaje = mensaje;
        this.tipo = tipo;
        this.confirmaEnviada = confirmaEnviada;
        this.fechaCreacion = LocalDateTime.now();

    }

    //Getters y setters.

    public String getId() {
        return this.idNotificacion;
    }

    public void setId(String id) {
        this.idNotificacion = idNotificacion;
    }

    public String getDestinatario() {
        return this.destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isConfirmaEnviada() {
        return this.confirmaEnviada;
    }

    public boolean getConfirmaEnviada() {
        return this.confirmaEnviada;
    }

    public void setConfirmaEnviada(boolean confirmaEnviada) {
        this.confirmaEnviada = confirmaEnviada;
    }

    //toString() y equals().


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", destinatario='" + getDestinatario() + "'" +
            ", tipo='" + getTipo() + "'" +
            ", confirmaEnviada='" + isConfirmaEnviada() + "'" +
            "}";
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof NotificacionModel)) {
            return false;
        }
        NotificacionModel notificacionModel = (NotificacionModel) o;
        return Objects.equals(idNotificacion, notificacionModel.idNotificacion) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNotificacion, destinatario, tipo, confirmaEnviada);
    }

}
