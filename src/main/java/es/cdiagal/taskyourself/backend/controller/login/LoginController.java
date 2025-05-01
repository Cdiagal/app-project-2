package es.cdiagal.taskyourself.backend.controller.login;

import com.jfoenix.controls.JFXButton;

import es.cdiagal.taskyourself.backend.controller.abstractas.AbstractController;
import es.cdiagal.taskyourself.initApp.MainApplication;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Clase que controla todas las funciones que contiene la ventana 'Login'.
 *
 * @author cdiagal
 * @version 1.0.0
 */

public class LoginController extends AbstractController{
    @FXML
    protected JFXButton settingsButton;
    
    @FXML
    protected Label emaiLabel;

    @FXML
    protected MFXButton emailTextField;

    @FXML
    protected Label passwordLabel;

    @FXML
    protected MFXButton passwordField;

    @FXML
    protected Hyperlink passwordLink;

    @FXML
    protected JFXButton loginButton;

    @FXML
    protected JFXButton googleLoginButton;

    @FXML
    protected JFXButton appleLoginButton;

    @FXML
    protected JFXButton facebookLoginButton;

    @FXML
    protected JFXButton homeButton;


    /**
     * Funcion que lleva hacia la pantalla de tareas.
     */
    @FXML
    protected void onClicLogin(){
        try {
            Stage stage = (Stage) loginButton.getScene().getWindow();
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
     * Funcion que lleva hacia la pantalla de tareas.
     */
    @FXML
    protected void onClicGoogle(){
        try {
            Stage stage = (Stage) loginButton.getScene().getWindow();
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
     * Funcion que lleva hacia la pantalla de tareas.
     */
    @FXML
    protected void onClicApple(){
        try {
            Stage stage = (Stage) loginButton.getScene().getWindow();
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
     * Funcion que lleva hacia la pantalla de tareas.
     */
    @FXML
    protected void onClicFacebook(){
        try {
            Stage stage = (Stage) loginButton.getScene().getWindow();
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
}
