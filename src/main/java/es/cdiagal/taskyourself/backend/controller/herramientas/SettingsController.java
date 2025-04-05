package es.cdiagal.taskyourself.backend.controller.herramientas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;

import es.cdiagal.taskyourself.backend.controller.abstractas.AbstractController;
import es.cdiagal.taskyourself.backend.controller.banderas.Bandera;
import es.cdiagal.taskyourself.initApp.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


/**
 * Clase que controla todas las funciones que contiene la ventana 'Settings'.
 *
 * @author cdiagal
 * @version 1.0.0
 */

public class SettingsController extends AbstractController {

    @FXML
    protected JFXButton languageButton;

    @FXML
    protected Label languageLabel;

    @FXML
    protected ComboBox<Bandera> languageComboBox;

    @FXML
    protected JFXButton backButton;

    @FXML
    protected JFXButton settingsButton;

    @FXML
    protected Label darkModeLabel;

    @FXML
    protected JFXToggleButton darkButton;

    @FXML
    protected Label notiLabel;

    @FXML
    protected JFXToggleButton notiToggleButton;

    @FXML
    protected JFXButton fontSizeButton;

    @FXML
    protected Label fontSizeLabel;

    @FXML
    protected Label infoAppLabel;

    @FXML
    protected JFXButton infoAppButton;

    @FXML
    protected JFXButton homeButton;

    @FXML
    protected JFXButton calendarButton;

    @FXML
    protected JFXButton notiButton;

    @FXML
    protected JFXButton taskButton;

    @FXML
    protected JFXButton profileButton;


        /**
     * Metodo que inicializa el cambio de idioma en el ComboBox.
     */
    @FXML
    public void initialize() {
        languageComboBox.getItems().addAll(
            new Bandera("español", new Image("/imagesFxml/sp.png")),
            new Bandera("english", new Image("/imagesFxml/uk.png")),
            new Bandera("français", new Image("/imagesFxml/fr.png"))
        );

    // Mostrar solo banderas en el desplegable
        languageComboBox.setCellFactory(listView -> crearCeldasIdioma());

    // Mostrar solo la bandera seleccionada
        languageComboBox.setButtonCell(crearCeldasIdioma());
    
    // Seleccionar la actual
    for (Bandera idioma : languageComboBox.getItems()) {
        if (idioma.getNombre().equalsIgnoreCase(AbstractController.getIdiomaActual())) {
            languageComboBox.setValue(idioma);
            break;
        }
    }

    }
    /**
     * Metodo que crea las celdas del ComboBox con imagenes.
     */
    private ListCell<Bandera> crearCeldasIdioma() {
    return new ListCell<>() {
        private final ImageView imageView = new ImageView();
    
        @Override
        protected void updateItem(Bandera item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setGraphic(null);
            } else {
                imageView.setImage(item.getIcono());
                imageView.setFitWidth(30);
                imageView.setFitHeight(30);
                setGraphic(imageView);
            }
        }
    };
}

    @FXML
    public void onClicChangeLanguage() {
        Bandera idiomaSeleccionado = (Bandera) languageComboBox.getValue();
        AbstractController.setIdiomaActual(idiomaSeleccionado.getNombre());
        setPropertiesIdioma(loadIdioma("lan",languageComboBox.getValue().getNombre()));

        languageLabel.setText(getPropertiesIdioma().getProperty("languageLabel"));
        darkModeLabel.setText(getPropertiesIdioma().getProperty("darkModeLabel"));
        notiLabel.setText(getPropertiesIdioma().getProperty("notiLabel"));
        fontSizeLabel.setText(getPropertiesIdioma().getProperty("fontSizeLabel"));
        infoAppLabel.setText(getPropertiesIdioma().getProperty("infoAppLabel"));
    }
        



    /**
     * Funcion que lleva hacia la pantalla anterior desde la que se accede a Settings.
     */
    @FXML
    protected void onClickBackButton(){
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("initApp.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 450, 600);
            stage.setTitle("TaskYourself");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Funcion que lleva hacia la pantalla Settings.
     */
    @FXML
    protected void onClicSettings(){
        try {
            Stage stage = (Stage) settingsButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("settings.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 450, 600);
            stage.setTitle("Ajustes");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Funcion que cambia el modo de la app de claro a oscuro y viceversa.
     */
    @FXML
    protected void onClicDarkFlip(){

    }

    /**
     * Funcion que activa las notificaciones.
     */
    @FXML
    protected void onClicNoti(){
    
    }
    
    /**
     * Funcion que cambia el tamanio de la letra de la aplicacion.
     */
    @FXML
    protected void onClicChangeFontSize(){

    }

    /**
     * Funcion que accede a la informacion de la app.
     */
    @FXML
    protected void onClicInfoApp(){
        
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
     * Funcion que accede a la ventana calendario.
     */
    @FXML
    protected void onClicCalendar(){
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
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
     * Funcion que accede a la ventana de notificaciones.
     */
    @FXML
    protected void onClicNotiList(){
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
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
     * Funcion que accede a la ventana de tareas.
     */
    @FXML
    protected void onClicTaskList(){
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
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
     * Funcion que accede a la ventana del perfil del usuario.
     */
    @FXML
    protected void onClicProfileButton(){
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
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
