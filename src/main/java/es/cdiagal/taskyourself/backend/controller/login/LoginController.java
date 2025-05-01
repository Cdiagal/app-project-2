package es.cdiagal.taskyourself.backend.controller.login;

import com.jfoenix.controls.JFXButton;

import es.cdiagal.taskyourself.backend.controller.abstractas.AbstractController;
import es.cdiagal.taskyourself.initApp.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Clase que controla todas las funciones que contiene la ventana 'Login'.
 *
 * @author cdiagal
 * @version 1.0.0
 */

public class LoginController extends AbstractController{
    @FXML protected JFXButton settingsButton;
    
    @FXML protected Label loginLabel;

    @FXML protected Label emaiLabel;

    @FXML protected TextField userLoginTextfield;

    @FXML protected Label passwordLabel;

    @FXML protected PasswordField userLoginPasswordfield;

    @FXML protected Hyperlink recoveryLink;

    @FXML protected JFXButton acceptLoginButton;

    @FXML protected JFXButton homeButton;


    /**
     * Funcion que lleva hacia la pantalla de tareas.
     */
    @FXML
    protected void onClicLogin(){
        try {
            Stage stage = (Stage) acceptLoginButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("tareaList.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 450, 600);
            stage.setTitle("Tareas");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
        /**
     * Funcion que accede a la ventana de Inicio.
     */
    @FXML
    protected void onClicHome(){
        try {
            Stage stage = (Stage) homeButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("initApp.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 450, 600);
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
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



}
