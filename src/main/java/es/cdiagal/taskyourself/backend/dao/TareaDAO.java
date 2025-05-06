package es.cdiagal.taskyourself.backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import es.cdiagal.taskyourself.backend.model.abstractas.Conexion;
import es.cdiagal.taskyourself.backend.model.tarea.TareaModel;

public class TareaDAO extends Conexion {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    public TareaDAO(String rutaBD) {
        super(rutaBD);
    }

    /**
     * Inserta una nueva tarea en la base de datos.
     */
    public boolean insertarTarea(TareaModel tarea) {
        String sql = "INSERT INTO tareas (titulo, descripcion, fecha_creacion, fecha_objetivo, completada) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = conectar();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, tarea.getTitulo());
                preparedStatement.setString(2, tarea.getDescripcion());
                preparedStatement.setString(3, tarea.getFechaCreacion().format(FORMATTER));
                preparedStatement.setString(4, tarea.getFechaObjetivo().format(FORMATTER));
                preparedStatement.setBoolean(5, tarea.isTareaCompletada());
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
     * Obtiene todas las tareas de la base de datos.
     * @return Lista de tareas.
     */
    public List<TareaModel> obtenerTodas() {
        return obtenerPorEstado(null); // todas sin filtrar
    }

    /**
     * Obtiene las tareas completadas de la base de datos.
     * @return Lista de tareas completadas.
     */
    public List<TareaModel> obtenerCompletadas() {
        return obtenerPorEstado(true);
    }

    /**
     * Obtiene las tareas pendientes de la base de datos.
     * @return Lista de tareas pendientes.
     */
    public List<TareaModel> obtenerPendientes() {
        return obtenerPorEstado(false);
    }

    /**
     * Obtiene las tareas de la base de datos según su estado.
     * @param completada Estado de la tarea (completada o no).
     * @return Lista de tareas según el estado.
     */
    private List<TareaModel> obtenerPorEstado(Boolean completada) {
        List<TareaModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM tareas";
        if (completada != null) {
            sql += " WHERE completada = ?";
        }

        try {
            Connection connection = conectar();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                if (completada != null) {
                    preparedStatement.setBoolean(1, completada);
                }
                ResultSet cursor = preparedStatement.executeQuery();
                while (cursor.next()) {
                    String fechaObjetivoStr = cursor.getString("fecha_objetivo");
                    LocalDateTime fechaObjetivo = fechaObjetivoStr != null ? LocalDateTime.parse(fechaObjetivoStr, FORMATTER) : null;
                    lista.add(new TareaModel(
                        cursor.getInt("id"),
                        cursor.getString("titulo"),
                        cursor.getString("descripcion"),
                        LocalDateTime.parse(cursor.getString("fecha_creacion"), FORMATTER),
                        fechaObjetivo,
                        cursor.getBoolean("completada")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return lista;
    }

    /**
     * Obtiene una tarea por su ID.
     * @param idTarea ID de la tarea.
     * @return Tarea con el ID especificado.
     */
    public List<TareaModel> obtenerTodasOrdenadasPorFecha() {
        List<TareaModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM tareas ORDER BY fecha_creacion ASC";
    
        Connection connection = null;
        try {
            connection = conectar();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet cursor = preparedStatement.executeQuery()) {
    
                while (cursor.next()) {
                    String fechaCreacionStr = cursor.getString("fecha_creacion");
                    String fechaObjetivoStr = cursor.getString("fecha_objetivo");
    
                    LocalDateTime fechaCreacion = (fechaCreacionStr != null && !fechaCreacionStr.isEmpty())
                            ? LocalDateTime.parse(fechaCreacionStr, FORMATTER)
                            : null;
    
                    LocalDateTime fechaObjetivo = (fechaObjetivoStr != null && !fechaObjetivoStr.isEmpty())
                            ? LocalDateTime.parse(fechaObjetivoStr, FORMATTER)
                            : null;
    
                    TareaModel tarea = new TareaModel(
                            cursor.getInt("id"),
                            cursor.getString("titulo"),
                            cursor.getString("descripcion"),
                            fechaCreacion,
                            fechaObjetivo,
                            cursor.getBoolean("completada")
                    );
                    lista.add(tarea);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
    
        return lista;
    }
    

    /**
     * Obtiene una tarea por su ID.
     * @param idTarea ID de la tarea.
     * @return Tarea con el ID especificado.
     */
    public List<TareaModel> obtenerTareasPorUsuario(int idUsuario) {
        List<TareaModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM tareas WHERE usuario_id = ? ORDER BY fecha_creacion ASC";
    
        try (Connection connection = conectar();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
    
            preparedStatement.setInt(1, idUsuario);
            ResultSet cursor = preparedStatement.executeQuery();
            
            while (cursor.next()) {
                String fechaObjetivoStr = cursor.getString("fecha_objetivo");
                LocalDateTime fechaObjetivo = fechaObjetivoStr != null ? LocalDateTime.parse(fechaObjetivoStr, FORMATTER) : null;
                lista.add(new TareaModel(
                    cursor.getInt("id"),
                    cursor.getString("titulo"),
                    cursor.getString("descripcion"),
                    LocalDateTime.parse(cursor.getString("fecha_creacion"), FORMATTER),
                    fechaObjetivo,
                    cursor.getBoolean("completada")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
    
        return lista;
    }

    /**
     * Busca tareas por texto en el título o descripción.
     * @param texto Texto a buscar.
     * @return Lista de tareas que coinciden con el texto.
     */
    public List<TareaModel> buscarTareasPorTexto(String texto) {
        List<TareaModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM tareas WHERE titulo LIKE ? OR descripcion LIKE ? ORDER BY fecha_creacion ASC";
    
        try {
            Connection connection = conectar();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                String query = "%" + texto + "%";
                preparedStatement.setString(1, query);
                preparedStatement.setString(2, query);
    
                ResultSet cursor = preparedStatement.executeQuery();
                while (cursor.next()) {
                    String fechaObjetivoStr = cursor.getString("fecha_objetivo");
                    LocalDateTime fechaObjetivo = fechaObjetivoStr != null ? LocalDateTime.parse(fechaObjetivoStr, FORMATTER) : null;
                    TareaModel tarea = new TareaModel(
                        cursor.getInt("id"),
                        cursor.getString("titulo"),
                        cursor.getString("descripcion"),
                        LocalDateTime.parse(cursor.getString("fecha_creacion"), FORMATTER),
                        fechaObjetivo,
                        cursor.getBoolean("completada")
                    );
                    lista.add(tarea);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
    
        return lista;
    }
    
    /**
     * Busca tareas por texto en el título o descripción y por ID de usuario.
     * @param usuarioId ID del usuario.
     * @param texto Texto a buscar.
     * @return Lista de tareas que coinciden con el texto y el ID de usuario.
     */
    public List<TareaModel> buscarTareasPorUsuarioYTexto(int usuarioId, String texto) {
        List<TareaModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM tareas WHERE usuario_id = ? AND (titulo LIKE ? OR descripcion LIKE ?) ORDER BY fecha_creacion ASC";
    
        try (Connection connection = conectar();
            PreparedStatement ps = connection.prepareStatement(sql)) {
    
            ps.setInt(1, usuarioId);
            String query = "%" + texto + "%";
            ps.setString(2, query);
            ps.setString(3, query);
    
            ResultSet cursor = ps.executeQuery();
            while (cursor.next()) {
                String fechaObjetivoStr = cursor.getString("fecha_objetivo");
                LocalDateTime fechaObjetivo = (fechaObjetivoStr != null && !fechaObjetivoStr.isEmpty())
                    ? LocalDateTime.parse(fechaObjetivoStr, FORMATTER)
                    : null;
    
                lista.add(new TareaModel(
                    cursor.getInt("id"),
                    cursor.getString("titulo"),
                    cursor.getString("descripcion"),
                    LocalDateTime.parse(cursor.getString("fecha_creacion"), FORMATTER),
                    fechaObjetivo,
                    cursor.getBoolean("completada")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
    
        return lista;
    }
    
    /**
     * Actualiza una tarea en la base de datos.
     * @param tarea Tarea a actualizar.
     * @return true si la tarea se actualizó correctamente, false en caso contrario.
     */
    public boolean marcarComoCompletada(int idTarea) {
        return actualizarEstado(idTarea, true);
    }

    /**
     * Marca una tarea como pendiente en la base de datos.
     * @param idTarea ID de la tarea a marcar como pendiente.
     * @return true si la tarea se actualizó correctamente, false en caso contrario.
     */
    public boolean marcarComoPendiente(int idTarea) {
        return actualizarEstado(idTarea, false);
    }

    /**
     * Actualiza el estado de una tarea en la base de datos.
     * @param idTarea ID de la tarea a actualizar.
     * @param completada Estado de la tarea (completada o no).
     * @return true si la tarea se actualizó correctamente, false en caso contrario.
     */
    private boolean actualizarEstado(int idTarea, boolean completada) {
        String sql = "UPDATE tareas SET completada = ? WHERE id = ?";
        try {
            Connection connection = conectar();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setBoolean(1, completada);
                preparedStatement.setInt(2, idTarea);
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
     * Elimina una tarea de la base de datos.
     * @param idTarea ID de la tarea a eliminar.
     * @return true si la tarea se eliminó correctamente, false en caso contrario.
     */
    public boolean eliminarTarea(int idTarea) {
        String sql = "DELETE FROM tareas WHERE id = ?";
        try {
            Connection connection = conectar();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idTarea);
                return preparedStatement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrar();
        }
    }
}
