package es.cdiagal.taskyourself.backend.model.recordatorio;
import java.time.LocalDateTime;
import java.util.Objects;

import es.cdiagal.taskyourself.backend.model.notificacion.NotificacionModel;

/**
 * Clase RecordatorioModel.
 * 
 * Representa un recordatorio específico que se asocia con una tarea
 * o evento, y puede tener una fecha y hora para recordar al usuario.
 * 
 * @author cdiagal
 * @version 1.0.0
 */

public class RecordatorioModel extends NotificacionModel {
    private LocalDateTime fechaRecordatorio;

    //Constructor vacío.
    public RecordatorioModel (){

    }

    //Constructor de la clase con todas las propiedades heredadas de NotificacionModel y las propias.
    public RecordatorioModel(String idNotificacion, String destinatario, String mensaje, String tipo, boolean confirmaEnviada, LocalDateTime fechaCreacion, LocalDateTime fechaRecordatorio) {
        super(idNotificacion, destinatario, mensaje, tipo, confirmaEnviada, fechaCreacion);
        this.fechaRecordatorio = fechaRecordatorio;
    }

    public LocalDateTime getFechaRecordatorio() {
        return fechaRecordatorio;
    }

    public void setFechaRecordatorio(LocalDateTime fechaRecordatorio) {
        this.fechaRecordatorio = fechaRecordatorio;
    }

    //toString() equals().
    @Override
    public String toString() {
        return super.toString() + " RecordatorioModel{" +
                "fechaRecordatorio=" + fechaRecordatorio +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RecordatorioModel)) {
            return false;
        }
        RecordatorioModel recordatorioModel = (RecordatorioModel) o;
        return Objects.equals(fechaRecordatorio, recordatorioModel.fechaRecordatorio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }
    
}


