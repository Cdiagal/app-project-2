package es.cdiagal.taskyourself.backend.controller.init;


import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

/**
 * Clase que controla todas las funciones que tiene el inicio de la app.
 *
 * @author cdiagal
 * @version 1.0.0
 */

public class InitAppController {
    
    @FXML
    protected AnchorPane anchorPane;

    @FXML
    protected JFXButton loginButton;

    @FXML
    protected JFXButton registerButton;
    
    @FXML
    protected JFXButton settingsButton;

    @FXML
    protected AnchorPane anchorPaneInitFront;
    

    /**
     * Metodo que gestiona el acceso a la pantalla de login mediante un boton.
     */

    @FXML
    public void onClicOpenLogin(){

    }

    /**
     * Metodo que gestiona el acceso a la pantalla de register mediante un boton.
     */

    @FXML
    public void onClicOpenRegister(){

    }

    /**
     * Metodo que gestiona el acceso a la pantalla de settings mediante un boton.
     */

    @FXML
    public void onClicOpenSettings(){

    }
}
