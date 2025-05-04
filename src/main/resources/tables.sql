-- Eliminar tablas si existen
DROP TABLE IF EXISTS tareas;
DROP TABLE IF EXISTS usuario;

-- Crear tabla de usuarios
CREATE TABLE usuarios (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL
);

-- Crear tabla unificada de tareas
CREATE TABLE tareas (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    usuario_id INTEGER NOT NULL,
    titulo TEXT NOT NULL,
    descripcion TEXT,
    fecha_creacion TEXT NOT NULL,
    fecha_objetivo TEXT,
    completada INTEGER DEFAULT 0, -- 0 = pendiente, 1 = completada
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

-- Insertar un usuario de prueba
INSERT INTO usuarios (nombre, email, password) VALUES 
('Carlos', 'carlos@example.com', 'hash_segurity_fake');

-- Insertar 6 tareas para el usuario con ID 1
INSERT INTO tareas (usuario_id, titulo, descripcion, fecha_creacion, fecha_objetivo, completada) VALUES
(1, 'Estudiar JavaFX', 'Repasar controles y FXML', '2025-05-02T09:00:00', '2025-05-05T18:00:00', 0),
(1, 'Preparar examen', 'Organizar los temas más difíciles', '2025-05-01T10:00:00', '2025-05-06T23:00:00', 0),
(1, 'Actualizar proyecto', 'Subir última versión a GitHub', '2025-05-02T12:30:00', NULL, 1),
(1, 'Diseñar logo', 'Terminar iconos para la app', '2025-04-30T08:00:00', '2025-05-04T20:00:00', 0),
(1, 'Escribir README', 'Explicar funcionalidades del programa', '2025-05-01T14:00:00', NULL, 1),
(1, 'Probar funcionalidades', 'Testear toda la lógica del panel de tareas', '2025-05-02T15:00:00', '2025-05-07T10:00:00', 0);
