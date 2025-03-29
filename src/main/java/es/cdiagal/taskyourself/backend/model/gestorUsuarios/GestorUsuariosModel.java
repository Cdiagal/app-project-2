package es.cdiagal.taskyourself.backend.model.gestorUsuarios;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import es.cdiagal.taskyourself.backend.model.usuario.UsuarioModel;

/**
 * Clase GestorUsuariosModel.
 * 
 * Encargada de gestionar los usuarios dentro de la aplicación. Almacena
 * los usuarios en una estructura de tipo HashMap, donde la clave es el
 * ID único del usuario y el valor es el objeto UsuarioModel asociado.
 * 
 * @author cdiagal
 * @version 1.0.0
 */

public class GestorUsuariosModel {

    private Map<String, UsuarioModel> usuarios;

    //Constructor vacío de la clase. Se inicializa un nuevo ArrayList.
    public GestorUsuariosModel (){
        this.usuarios = new HashMap<>();
    }

    //Getters y setters.
    public Map<String,UsuarioModel> getUsuarios() {
        return this.usuarios;
    }

    public void setUsuarios(Map<String,UsuarioModel> usuarios) {
        this.usuarios = usuarios;
    }

    //toString y equals().

    @Override
    public String toString() {
        return "{" +
            " usuarios='" + getUsuarios() + "'" +
            "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GestorUsuariosModel)) {
            return false;
        }
        GestorUsuariosModel gestorUsuarios = (GestorUsuariosModel) o;
        return Objects.equals(usuarios, gestorUsuarios.usuarios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarios);
    }
    

}
