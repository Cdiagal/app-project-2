
package es.cdiagal.taskyourself.backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.cdiagal.taskyourself.backend.model.abstractas.Conexion;
import es.cdiagal.taskyourself.backend.model.usuario.UsuarioModel;



public class UsuarioDAO extends Conexion {

    public UsuarioDAO(String rutaBD) {
        super(rutaBD);
    }

    /**
     * Metodo que busca un usuario por su nick en la BBDD.
     * @param nick del usuario.
     * @return usuario buscado.
     */
    public UsuarioModel buscarPorNombre(String nombre) {
        UsuarioModel usuario = null;
        String sql = "SELECT * FROM usuarios WHERE nombre = ?";
        
        try {
            Connection conection = conectar();
            try(PreparedStatement preparedStatement = conection.prepareStatement(sql)) {
                preparedStatement.setString(1, nombre);
                try(ResultSet cursor = preparedStatement.executeQuery()){
                    if (cursor.next()) {
                        return new UsuarioModel(
                            cursor.getInt("id"),
                            cursor.getString("nombre"),
                            cursor.getString("email"),
                            cursor.getString("password")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            cerrar();
        }
        return usuario;
    }

    
    /**
     * Metodo que busca un usuario por su email en la BBDD.
     * @param email del usuario.
     * @return usuario buscado.
     */
    public UsuarioModel buscarPorEmail(String email) {
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        UsuarioModel usuario = null;
        try {
            Connection conection = conectar();
            try(PreparedStatement preparedStatement = conection.prepareStatement(sql)) {
                preparedStatement.setString(1, email);
                try(ResultSet cursor = preparedStatement.executeQuery()){
                    if (cursor.next()) {
                        return new UsuarioModel(
                            cursor.getInt("id"),
                            cursor.getString("nombre"),
                            cursor.getString("email"),
                            cursor.getString("password")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            cerrar();
        }
        return usuario;
    }


    /**
     * Metodo que inserta un nuevo usuario en la BBDD.
     * @param usuario a insertar.
     * @return usuario insertado.
     */
    public boolean insertar(UsuarioModel usuario) {
        String sql = "INSERT INTO usuarios (nombre, email, password) VALUES (?, ?, ?)";
        try {
            Connection conection = conectar();
            try(PreparedStatement preparedStatement = conection.prepareStatement(sql)) {
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.setString(3, usuario.getPassword());
            return preparedStatement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrar();
        }
    }


    /**
     * Metodo que actualiza los datos del usuario (nickname, email, password, racha, puntos, nivel).
     * @param usuario con los datos actualizados.
     * @return true si se actualizó correctamente.
     */
    public boolean actualizarUsuario(UsuarioModel usuario) {
        String sql = "UPDATE usuarios SET nombre = ?, email = ?, password = ? WHERE id = ?";
        try {
            Connection conection = conectar();
            try(PreparedStatement preparedStatement = conection.prepareStatement(sql)) {
                preparedStatement.setString(1, usuario.getNombre());
                preparedStatement.setString(2, usuario.getEmail());
                preparedStatement.setString(3, usuario.getPassword());
                preparedStatement.setInt(4, usuario.getId());
                return preparedStatement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrar();
        }
        
    }


    /**
    * Metodo que elimina un usuario por su ID.
    * @param idUsuario del usuario a eliminar.
    * @return true si se eliminó correctamente.
    */
    public boolean eliminar(int idUsuario) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try {
            Connection conection = conectar();
            try(PreparedStatement preparedStatement = conection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idUsuario);
                return preparedStatement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrar();
        }
        
    }



    /**
     * Metodo que actualiza la contrasenia antigua por la nueva al enviarse el correo.
     * El usuario solicita via email una nueva contrasenia.
     * @param email con la nueva contrasenia.
     * @param nuevaPassword del usuario.
     * @return nueva contrasenia.
     */
    public boolean actualizarPasswordPorEmail(String email, String nuevaPassword) {
        String sql = "UPDATE usuarios SET password = ? WHERE email = ?";
        boolean actualizada = false;
    
        try {
            Connection conection = conectar();
            try(PreparedStatement preparedStatement = conection.prepareStatement(sql)) {
                preparedStatement.setString(1, nuevaPassword); // Aquí podrías aplicar hashing si lo deseas
                preparedStatement.setString(2, email);
                int filas = preparedStatement.executeUpdate();
                actualizada = filas > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
    
        return actualizada;
    }








        /**
     * Método que actualiza la imagen de perfil del usuario.
     * @param idUsuario ID del usuario.
     * @param imagenBytes imagen convertida a byte[].
     * @return true si se actualiza correctamente.
     */
    public boolean actualizarImagen(int idUsuario, byte[] imagenBytes) {
        String sql = "UPDATE usuarios SET imagen_perfil = ? WHERE id = ?";
        try {
            Connection conection = conectar();
            try(PreparedStatement preparedStatement = conection.prepareStatement(sql)) {
                preparedStatement.setBytes(1, imagenBytes);
                preparedStatement.setInt(2, idUsuario);
                return preparedStatement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrar();
        }
    }


    /**
 * Obtiene la imagen de perfil del usuario como byte[].
 * @param idUsuario ID del usuario.
 * @return imagen en bytes o null.
 */
public byte[] obtenerImagen(int idUsuario) {
    String sql = "SELECT imagen_perfil FROM usuarios WHERE id = ?";
    try {
        Connection conection = conectar();
            try(PreparedStatement preparedStatement = conection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idUsuario);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return rs.getBytes("imagen_perfil");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        cerrar();
    }
    return null;
}

}
