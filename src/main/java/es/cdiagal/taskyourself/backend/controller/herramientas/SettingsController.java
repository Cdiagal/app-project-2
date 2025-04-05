package es.cdiagal.taskyourself.backend.controller.herramientas;
/**
 * Clase que controla todas las funciones que contiene la ventana 'Settings'.
 *
 * @author cdiagal
 * @version 1.0.0
 */

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;

import es.cdiagal.taskyourself.backend.controller.abstractas.AbstractController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SettingsController extends AbstractController {
    @FXML
    protected JFXButton languageButton;

    @FXML
    protected Label languageLabel;

    @FXML
    protected JFXButton backButton;

    @FXML
    protected JFXToggleButton darkButton;

    @FXML
    protected JFXToggleButton notiButton;

    @FXML
    protected JFXButton fontSizeButton;

    @FXML
    protected Label fontSizeLabel;

    @FXML
    protected JFXButton infoAppButton;





}
