package es.cdiagal.taskyourself.backend.model.estadisticas;
import java.util.Objects;

/**
 * Clase EstadisticasModel.
 * 
 * Encargada de generar y almacenar estadísticas relacionadas con el
 * uso de la aplicación. Los datos incluyen el total de usuarios, tareas
 * completadas y tareas totales.
 * 
 * @author cdiagal
 * @version 1.0.0
 */

public class EstadísticasModel {
    private String idEstadistica;
    private int numUsuarios;
    private int numTareas;
    private int numTareasCompletadas;
    private int numTareasPenditentes;
    private int numTareasModificadas;

    //Constructor vacío para la clase.
    public EstadísticasModel (){

    }

    //Constructor con el atributo único de la clase.
    public EstadísticasModel(String idTarea){
        this.idEstadistica = idTarea;
    }

    //Constructor con todos los atributos de la clase.
    public EstadísticasModel(String idTarea, int numUsuarios, int numTareas, int numTareasCompletadas, int numTareasPenditentes, int numTareasModificadas){
        this.idEstadistica = idTarea;
        this.numUsuarios = numUsuarios;
        this.numTareas = numTareas;
        this.numTareasCompletadas = numTareasCompletadas;
        this.numTareasPenditentes = numTareasPenditentes;
        this.numTareasModificadas = numTareasModificadas;
    }

    //Getters y setters.


    public String getIdTarea() {
        return this.idEstadistica;
    }

    public void setIdTarea(String idTarea) {
        this.idEstadistica = idTarea;
    }

    public int getNumUsuarios() {
        return this.numUsuarios;
    }

    public void setNumUsuarios(int numUsuarios) {
        this.numUsuarios = numUsuarios;
    }

    public int getNumTareas() {
        return this.numTareas;
    }

    public void setNumTareas(int numTareas) {
        this.numTareas = numTareas;
    }

    public int getNumTareasCompletadas() {
        return this.numTareasCompletadas;
    }

    public void setNumTareasCompletadas(int numTareasCompletadas) {
        this.numTareasCompletadas = numTareasCompletadas;
    }

    public int getNumTareasPenditentes() {
        return this.numTareasPenditentes;
    }

    public void setNumTareasPenditentes(int numTareasPenditentes) {
        this.numTareasPenditentes = numTareasPenditentes;
    }

    public int getNumTareasModificadas() {
        return this.numTareasModificadas;
    }

    public void setNumTareasModificadas(int numTareasModificadas) {
        this.numTareasModificadas = numTareasModificadas;
    }

    //toString() y equals().


    @Override
    public String toString() {
        return "{" +
            " idTarea='" + getIdTarea() + "'" +
            ", numUsuarios='" + getNumUsuarios() + "'" +
            ", numTareas='" + getNumTareas() + "'" +
            ", numTareasCompletadas='" + getNumTareasCompletadas() + "'" +
            ", numTareasPenditentes='" + getNumTareasPenditentes() + "'" +
            ", numTareasModificadas='" + getNumTareasModificadas() + "'" +
            "}";
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EstadísticasModel)) {
            return false;
        }
        EstadísticasModel estadísticasModel = (EstadísticasModel) o;
        return Objects.equals(idEstadistica, estadísticasModel.idEstadistica);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(idEstadistica, numUsuarios, numTareas, numTareasCompletadas, numTareasPenditentes, numTareasModificadas);
    }
    

}
