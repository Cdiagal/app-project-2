package es.cdiagal.taskyourself.backend.dao;

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

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public TareaDAO(String rutaBD) {
        super(rutaBD);
    }

    public boolean insertarTarea(TareaModel tarea) {
        String sql = "INSERT INTO tareas (id, titulo, descripcion, fecha_creacion, fecha_objetivo, completada) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            conectar();
            try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
                preparedStatement.setString(1, tarea.getIdTarea());
                preparedStatement.setString(2, tarea.getTitulo());
                preparedStatement.setString(3, tarea.getDescripcion());
                preparedStatement.setString(4, tarea.getFechaCreacion().format(FORMATTER));
                preparedStatement.setString(5, tarea.getFechaObjetivo().format(FORMATTER));
                preparedStatement.setBoolean(6, tarea.isTareaCompletada());
                return stmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrar();
        }
    }

    public List<TareaModel> obtenerTodasLasTareas() {
        List<TareaModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM tareas";
        try {
            conectar();
            try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
                ResultSet cursor = preparedStatement.executeQuery()) {
                while (cursor.next()) {
                    TareaModel tarea = new TareaModel(
                        cursor.getString("id"),
                        cursor.getString("titulo"),
                        cursor.getString("descripcion"),
                        LocalDateTime.parse(cursor.getString("fecha_creacion"), FORMATTER),
                        LocalDateTime.parse(cursor.getString("fecha_objetivo"), FORMATTER),
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

    public boolean eliminarTarea(String idTarea) {
        String sql = "DELETE FROM tareas WHERE id = ?";
        try {
            conectar();
            try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
                preparedStatement.setString(1, idTarea);
                return preparedStatement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrar();
        }
    }

    public boolean marcarComoCompletada(String idTarea) {
        String sql = "UPDATE tareas SET completada = 1 WHERE id = ?";
        try {
            conectar();
            try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
                preparedStatement.setString(1, idTarea);
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

