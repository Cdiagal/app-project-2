package es.cdiagal.taskyourself.backend.controller.init;


import com.jfoenix.controls.JFXButton;

import es.cdiagal.taskyourself.backend.controller.abstractas.AbstractController;
import es.cdiagal.taskyourself.backend.controller.herramientas.SettingsController;
import es.cdiagal.taskyourself.backend.controller.utils.PantallasUtil;
import es.cdiagal.taskyourself.backend.controller.utils.PantallasUtil.Pantallas;
import es.cdiagal.taskyourself.backend.dao.UsuarioDAO;
import es.cdiagal.taskyourself.backend.model.usuario.UsuarioModel;
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
    private UsuarioModel usuario;
    private Pantallas pantallaOrigen;
    private UsuarioDAO usuarioDAO;


    @FXML protected AnchorPane anchorPane;
    @FXML protected Label loginLabel;
    @FXML protected JFXButton loginButton;
    @FXML protected Label registroLabel;
    @FXML protected JFXButton registerButton;
    @FXML protected JFXButton settingsButton;
    @FXML protected AnchorPane anchorPaneInitFront;

    /**
     * Inyección de dependencia del DAO usando la ruta unificada de la BD.
     */
    public InitAppController(){
        super();
        this.usuarioDAO = new UsuarioDAO(getRutaArchivoBD());
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    /**
     * Constructor de la clase TareaListController.
     * @param pantallaOrigen que representa la pantalla de origen.
     */
    public void setPantallaOrigen(Pantallas pantallaOrigen) {
        this.pantallaOrigen = pantallaOrigen;
    }

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
        stage.sizeToScene();
        stage.show();
        } catch (Exception e) {
            System.out.println("Error al cargar la página Login.");
            e.printStackTrace();
        }
    }

    /**
     * Metodo que gestiona el acceso a la pantalla de register mediante un boton.
     */

    @FXML
    public void onClicOpenRegister(){
        try {
            Stage stage = (Stage) settingsButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("register.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 451,600);
            stage.setTitle("Registro");
            stage.setScene(scene);
            stage.sizeToScene();
            stage.show();
            } catch (Exception e) {
                System.out.println("Error al cargar la página Registro.");
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

        SettingsController controller = fxmlLoader.getController();
        controller.setPantallaOrigen(PantallasUtil.Pantallas.INICIO);
        controller.setUsuario(usuario);
        controller.configurarIconos();

        stage.setTitle("Configuración");
        stage.setScene(scene);
        stage.sizeToScene();
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

        if (getPropertiesLanguage() == null){
            setPropertiesLanguage(loadLanguage("lan", idioma));
        }
        if (getPropertiesLanguage() != null){
            loginLabel.setText(getPropertiesLanguage().getProperty("loginLabel"));
            loginButton.setText(getPropertiesLanguage().getProperty("loginButton"));
            registroLabel.setText(getPropertiesLanguage().getProperty("registroLabel"));
            registerButton.setText(getPropertiesLanguage().getProperty("registerButton"));
        }
    }
}
