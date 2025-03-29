package es.cdiagal.taskyourself.backend.model.usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * Clase UsuarioModel
 * 
 * Representa a un usuario genérico de la aplicación. Almacena información
 * como su ID, nombre, correo electrónico y contraseña. 
 * 
 * @author cdiagal
 * @version 1.0.0
 */

public class UsuarioModel {
    private String id;
    private String nombre;
    private String email;
    private String password;
    private List<String> tareasPendientes;
    private List<String> tareasCompletadas;

    //Constructor vacío de la clase.
    public UsuarioModel (){

    }

    //Constructor con el id del Usuario.
    public UsuarioModel (String id){
        this.id = id;
    }

    //Constructor con todas las propiedades de la clase UsuarioModel.
    public UsuarioModel (String id, String nombre, String email, String password){
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.tareasPendientes = new ArrayList<>();
        this.tareasCompletadas = new ArrayList<>();
    }

    //Getters y setters de la clase.



    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getTareasPendientes() {
        return this.tareasPendientes;
    }

    public void setTareasPendientes(List<String> tareasPendientes) {
        this.tareasPendientes = tareasPendientes;
    }

    public List<String> getTareasCompletadas() {
        return this.tareasCompletadas;
    }

    public void setTareasCompletadas(List<String> tareasCompletadas) {
        this.tareasCompletadas = tareasCompletadas;
    }
    

    //ToString() y equals().


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", tareasPendientes='" + getTareasPendientes() + "'" +
            ", tareasCompletadas='" + getTareasCompletadas() + "'" +
            "}";
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UsuarioModel)) {
            return false;
        }
        UsuarioModel usuarioModel = (UsuarioModel) o;
        return Objects.equals(id, usuarioModel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, email, password, tareasPendientes, tareasCompletadas);
    }
   

}
