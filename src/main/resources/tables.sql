-- Eliminar la tabla usuario si existe
DROP TABLE IF EXISTS usuario;

-- Eliminar la tabla usuario si existe
DROP TABLE IF EXISTS tarea_pendiente;

-- Eliminar la tabla usuario si existe
DROP TABLE IF EXISTS tarea_completada;


-- Tabla principal de usuarios
CREATE TABLE usuario (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL
);

-- Tabla de tareas pendientes
CREATE TABLE tarea_pendiente (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    usuario_id TEXT NOT NULL,
    descripcion TEXT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE
);

-- Tabla de tareas completadas
CREATE TABLE tarea_completada (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    usuario_id TEXT NOT NULL,
    descripcion TEXT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE
);

INSERT INTO usuario (nombre, email, password) VALUES
    ('Carlos', 'aaaa', 'carlos@example.com');

INSERT INTO tarea_pendiente (usuario_id, descripcion) VALUES
    ('u001', 'Estudiar programación');

INSERT INTO tarea_completada (usuario_id, descripcion) VALUES
    ('u001', 'Hacer pantallas mook');