package es.cdiagal.taskyourself.backend.model;
import java.util.Objects;

/**
 * Clase AdministradorModel.
 * 
 * Subclase de UsuarioModel que representa a un administrador del sistema.
 * Además de los atributos de un usuario genérico, contiene un nivel de permisos
 * que define su capacidad administrativa dentro de la aplicación.
 * 
 * @author cdiagal
 * @version 1.0.0
 */

public class AdministradorModel  extends UsuarioModel{
    private String permisos;

    //Constructor vacío de la clase.
    public AdministradorModel(){

    }

    //Constructor de la clase con atributos comunes heredados de usuario y los específicos.
    public AdministradorModel (String id, String nombre, String email, String password, String permisos){
        super(id, nombre, email, password);
        this.permisos = permisos;
    }

    //Gettes y setters.

    public String getPermisos() {
        return this.permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

    //toString() y equals().


    @Override
    public String toString() {
        return "{" +
            " permisos='" + getPermisos() + "'" +
            "}";
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AdministradorModel)) {
            return false;
        }
        AdministradorModel administradorModel = (AdministradorModel) o;
        return Objects.equals(permisos, administradorModel.permisos);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),permisos);
    }
   
    
}
