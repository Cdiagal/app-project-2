package es.cdiagal.taskyourself.backend.controller.usuario;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.jfoenix.controls.JFXButton;

import es.cdiagal.taskyourself.backend.controller.abstractas.AbstractController;
import es.cdiagal.taskyourself.backend.controller.utils.PantallasUtil.Pantallas;
import es.cdiagal.taskyourself.backend.dao.UsuarioDAO;
import es.cdiagal.taskyourself.backend.model.usuario.UsuarioModel;
import es.cdiagal.taskyourself.initApp.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Clase que controla todas las funciones que contiene la ventana 'UserData'.
 * @author cdiagal
 * @version 1.0.0
 */

public class UserDataController extends AbstractController {
    private Pantallas pantallaOrigen;
    private UsuarioModel usuario;
    private UsuarioDAO usuarioDAO;

    @FXML private JFXButton backButton;
    @FXML private JFXButton settingsButton;
    @FXML private Label userNameLabel;
    @FXML private Label emailUserDataLabel;
    @FXML private ImageView updateImageView;
    @FXML private JFXButton changeImageButton;
    @FXML private JFXButton homeButton;
    @FXML private JFXButton calendarButton;
    @FXML private JFXButton notiButton;
    @FXML private JFXButton taskButton;
    @FXML private JFXButton profileButton;
    @FXML private JFXButton nuevaTareaButton;
    @FXML private JFXButton editProfileButton;
    @FXML private JFXButton logoutButton;
    @FXML private JFXButton helpButton;
    @FXML private JFXButton openDeleteAccount;

    


    public UserDataController() {
        super();
        this.usuarioDAO = new UsuarioDAO(getRutaArchivoBD());
    }

    public void setUsuario(UsuarioModel usuario){
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
     * Carga los datos del usuario y las tareas en la interfaz.
     */
    public void usuarioData(){
        if (usuario != null && getPropertiesLanguage() != null) {
            userNameLabel.setText(getPropertiesLanguage().getProperty("userNameLabel") + ", " + usuario.getNombre() + "!");
            emailUserDataLabel.setText(getPropertiesLanguage().getProperty("emailUserDataLabel") + ": " + usuario.getEmail());

            //cargarImagenUsuario();
            /**
            // Mostrar imagen de perfil si existe
            UsuarioDAO dao = new UsuarioDAO(getRutaArchivoBD());
            byte[] imagenBytes = dao.obtenerImagen(usuario.getId());
            if (imagenBytes != null) {
                InputStream is = new ByteArrayInputStream(imagenBytes);
                updateImageView.setImage(new Image(is));
            } else {
                InputStream is = getClass().getResourceAsStream("/images/profile.png");
                updateImageView.setImage(new Image(is));
            }*/
        }
    }

    /**
     * Funcion que lleva hacia la pantalla anterior desde la que se accede a Settings.
     * Hace uso de un switch para determinar la pantalla de origen.
     * @param pantallaOrigen Pantalla de origen.
     */
    @FXML
    protected void onClickBackButton(){
        try {
            if(pantallaOrigen == null) return;

            FXMLLoader fxmlLoader = null;
            String fxml = null;
            switch (pantallaOrigen) {
                case LOGIN -> fxml = "login.fxml";
                case AJUSTES -> fxml = "settings.fxml";
                case PERFIL -> fxml = "userData.fxml";
                case TAREA_NUEVA -> fxml = "newTask.fxml";
                case TAREA_EDITAR -> fxml = "editTask.fxml";
                case NOTIFICACIONES -> fxml = "notifications.fxml";
            }

                if (fxml != null){
                Stage stage = (Stage) backButton.getScene().getWindow();
                fxmlLoader = new FXMLLoader(MainApplication.class.getResource(fxml));
                Scene scene = new Scene(fxmlLoader.load(), 450, 600);
                stage.setTitle("Iniciar sesión");
                stage.setScene(scene);
                stage.show();
                }
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
}
