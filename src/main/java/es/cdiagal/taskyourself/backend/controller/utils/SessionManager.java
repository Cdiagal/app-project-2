package es.cdiagal.taskyourself.backend.controller.utils;

import es.cdiagal.taskyourself.backend.model.usuario.UsuarioModel;

public class SessionManager {
    private static SessionManager instance;
    private UsuarioModel usuario;

    private SessionManager() {}

    public static SessionManager getInstance() {
        if (instance == null) instance = new SessionManager();
        return instance;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public void cerrarSesion() {
        usuario = null;
    }
}
