package es.cdiagal.taskyourself.backend.controller.init;


import com.jfoenix.controls.JFXButton;

import es.cdiagal.taskyourself.backend.controller.abstractas.AbstractController;
import es.cdiagal.taskyourself.initApp.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Clase que controla todas las funciones que tiene el inicio de la app.
 *
 * @author cdiagal
 * @version 1.0.0
 */

public class InitAppController  extends AbstractController{
    

    @FXML
    protected AnchorPane anchorPane;

    @FXML
    protected Label loginLabel;

    @FXML
    protected JFXButton loginButton;

    @FXML
    protected Label registroLabel;

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
        try {
        Stage stage = (Stage) settingsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 451,600);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        } catch (Exception e) {
            System.out.println("Error al cargar la página Configuración.");
            e.printStackTrace();
        }
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
        try {
        Stage stage = (Stage) settingsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("settings.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 451,600);
        stage.setTitle("Configuración");
        stage.setScene(scene);
        stage.show();
        } catch (Exception e) {
            System.out.println("Error al cargar la página Configuración.");
            e.printStackTrace();
        }
    }


    @FXML
    public void initialize(){
        onClicChangeLanguage();
    }

    @FXML
    public void onClicChangeLanguage(){
        String idioma = AbstractController.getIdiomaActual();

        if (getPropertiesIdioma() == null){
            setPropertiesIdioma(loadIdioma("lan", idioma));
        }
        if (getPropertiesIdioma() != null){
            loginLabel.setText(getPropertiesIdioma().getProperty("loginLabel"));
            loginButton.setText(getPropertiesIdioma().getProperty("loginButton"));
            registroLabel.setText(getPropertiesIdioma().getProperty("registroLabel"));
            registerButton.setText(getPropertiesIdioma().getProperty("registerButton"));
        }
    }
}
