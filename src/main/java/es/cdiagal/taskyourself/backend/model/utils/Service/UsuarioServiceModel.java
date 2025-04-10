package es.cdiagal.taskyourself.backend.model.utils.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import es.cdiagal.taskyourself.backend.model.abstractas.Conexion;
import es.cdiagal.taskyourself.backend.model.usuario.UsuarioModel;

public class UsuarioServiceModel extends Conexion{


private String rutaDB;



    public UsuarioServiceModel(){
        
    }

    public UsuarioServiceModel(String rutaDB){
        super(rutaDB);
        this.rutaDB = rutaDB;
    }


    /**
     * Metodo que busca un usuario con sus atributos..
     * @return Usuario.
     */
    public List<UsuarioModel> loadUser(String sql){
        List<UsuarioModel> usuarios = new ArrayList<>();
        try {
            conectar();
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            ResultSet cursor = preparedStatement.executeQuery();
            while (cursor.next()) {
                String id = cursor.getString("id");
                String nombre = cursor.getString("nombre");
                String contrasenia = cursor.getString("contrasenia");
                String email = cursor.getString("email");
                UsuarioModel usuario = new UsuarioModel(id, nombre, contrasenia, email);
                usuarios.add(usuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }   return usuarios;
    }

    /**
     * Metodo que lista todos los usuarios.
     * @return lista de usuarios.
     */
    public List<UsuarioModel> loadAllUsers(){
        String sql = "SELECT * FROM Usuario";
        return loadUser(sql);
    }

    /**
     * Metodo que busca un usuario por su email.
     * @param email del usuario.
     * @return usuario.
     */
    public UsuarioModel loadUserbyEmail (String email){
        try {
            String sql = "SELECT * FROM Usuario " + "where email='"+ email + "'";
        List<UsuarioModel> usuarios = loadUser(sql);
        if (usuarios.isEmpty()) {
            return null;
        }
        return usuarios.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrar();
        } 
        return null;
    }

    /**
     * Metodo que aniade un nuevo usuario con los atributos.
     * @param newUser a crear.
     * @return nuevo usuario.
     */
    public boolean addUser (UsuarioModel newUser){
        try {
            conectar();
            PreparedStatement preparedStatement = getConnection().prepareStatement(
                        "INSERT INTO usuario (id, nombre, contrasenia, email) values (?,?,?,?)");
            preparedStatement.setString(1, newUser.getId());
            preparedStatement.setString(2, newUser.getNombre());
            preparedStatement.setString(3, newUser.getPassword());
            preparedStatement.setString(4, newUser.getEmail());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrar();
        }
    }

    /**
     * Método que actualiza un usuario.
     * @param usuario de la base de datos.
     * @return usuario actualizado.
     */
    public boolean updateUser(UsuarioModel usuario) {
        String sql = "UPDATE usuario SET nombre = ?, contrasenia = ?, WHERE email = ?";
        try {
            conectar();
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getPassword());
            preparedStatement.setString(3, usuario.getEmail());
            int filas = preparedStatement.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrar();
        }
    }


    /**
     * Metodo que elimina un usuario.
     * @param email del usuario.
     * @return usuario eliminado.
     */
    public boolean deleteUser(String email) {
        String sql = "DELETE FROM usuario WHERE email = ?";
        try {
            conectar();
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, email);
            int filas = preparedStatement.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrar();
        }
    }


}
