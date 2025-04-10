package es.cdiagal.taskyourself.backend.controller.login;

import javax.swing.event.HyperlinkEvent;

import com.jfoenix.controls.JFXButton;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * Clase que controla todas las funciones que contiene la ventana 'Login'.
 *
 * @author cdiagal
 * @version 1.0.0
 */

public class LoginController {
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


    @FXML
    
}
