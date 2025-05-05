package es.cdiagal.taskyourself.backend.controller.login;

import com.jfoenix.controls.JFXButton;

import es.cdiagal.taskyourself.backend.controller.abstractas.AbstractController;
import es.cdiagal.taskyourself.backend.controller.herramientas.SettingsController;
import es.cdiagal.taskyourself.backend.controller.tarea.NuevaTareaController;
import es.cdiagal.taskyourself.backend.controller.tarea.TareaListController;
import es.cdiagal.taskyourself.backend.controller.usuario.UserDataController;
import es.cdiagal.taskyourself.backend.controller.utils.PantallasUtil;
import es.cdiagal.taskyourself.backend.controller.utils.PantallasUtil.Pantallas;
import es.cdiagal.taskyourself.backend.dao.UsuarioDAO;
import es.cdiagal.taskyourself.backend.model.usuario.UsuarioModel;
import es.cdiagal.taskyourself.backend.model.utils.service.HashUtils;
import es.cdiagal.taskyourself.initApp.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Clase que controla todas las funciones que contiene la ventana 'Login'.
 *
 * @author cdiagal
 * @version 1.0.0
 */

public class LoginController extends AbstractController{
    private UsuarioModel usuario;
    private final UsuarioDAO usuarioDAO;
    private Pantallas pantallaOrigen;

    @FXML protected JFXButton settingsButton;
    @FXML protected Label loginBigLabel;
    @FXML protected Label emailLabel;
    @FXML protected TextField userLoginTextfield;
    @FXML protected Label passwordLabel;
    @FXML protected PasswordField userLoginPasswordfield;
    @FXML protected Hyperlink recoveryLink;
    @FXML protected JFXButton acceptLoginButton;
    @FXML protected JFXButton homeButton;
    @FXML protected Text loginTextAdvise;

    /**
     * Inyección de dependencia del DAO usando la ruta unificada de la BD.
     */
    public LoginController(){
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
     * Metodo que inicializa el cambio de idioma en el ComboBox.
     */
    @FXML
    public void initialize(){
        if(getPropertiesLanguage()==null){
            setPropertiesLanguage(loadLanguage("lan", getIdiomaActual()));
        }
        loginTextAdvise.setText("");
        userLoginTextfield.requestFocus();
        changeLanguage();
    }

    
    /**
     * Funcion que cambia el idioma de las etiquetas y objetos de la ventana
     */
    @FXML
    public void changeLanguage() {
        String idioma = AbstractController.getIdiomaActual();

        if(getPropertiesLanguage() == null){
            setPropertiesLanguage(loadLanguage("lan", idioma));
        }
        if(getPropertiesLanguage() != null){

        loginBigLabel.setText(getPropertiesLanguage().getProperty("loginBigLabel"));
        emailLabel.setText(getPropertiesLanguage().getProperty("emailLabel"));
        passwordLabel.setText(getPropertiesLanguage().getProperty("passwordLabel"));
        userLoginTextfield.setPromptText(getPropertiesLanguage().getProperty("userLoginTextfieldPrompText"));
        userLoginPasswordfield.setPromptText(getPropertiesLanguage().getProperty("userLoginPasswordfieldPrompText"));
        loginTextAdvise.setText(getPropertiesLanguage().getProperty("loginTextAdvise_errorUser"));
        loginTextAdvise.setText(getPropertiesLanguage().getProperty("loginTextAdvise_errorPassword"));
        acceptLoginButton.setText(getPropertiesLanguage().getProperty("acceptLoginButton"));
        recoveryLink.setText(getPropertiesLanguage().getProperty("recoveryLink"));
        }
    }

    /**
     * Funcion que abre la ventana userData despues de comprobar los datos.
     */
    @FXML
    public void onClicAcceptLogin(){
        UsuarioModel usuarioValidado = validarDatosLogin();
        if (usuarioValidado != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("userTaskList.fxml"));
                Scene scene = new Scene(fxmlLoader.load());

                TareaListController controller = fxmlLoader.getController();
                controller.setUsuario(usuarioValidado);
                controller.setPantallaOrigen(PantallasUtil.Pantallas.LOGIN);
                controller.userInit();

                Stage stage = (Stage) acceptLoginButton.getScene().getWindow();
                stage.setTitle(getPropertiesLanguage().getProperty("Lista de tareas"));
                stage.setScene(scene);
                stage.sizeToScene();
                stage.show();
            } catch (Exception e) {
                System.out.println("Error al cargar la página.");
                e.printStackTrace();
            }
        }
    }

    /**
     * Funcion que lleva hacia la pantalla de recuperacion de contraseña.
     */
    @FXML
    public void onClicRecoverPassword(){
        try {
            Stage stage = (Stage) recoveryLink.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("recover.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Recuperar contraseña");
            stage.setScene(scene);
            stage.sizeToScene();
            stage.show();
        } catch (Exception e) {
            System.out.println("Error al cargar la página.");
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
            stage.sizeToScene();
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

        SettingsController controller = fxmlLoader.getController();
        controller.setUsuario(usuario);
        controller.setPantallaOrigen(PantallasUtil.Pantallas.LOGIN);
        
        stage.setTitle("Configuración");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
        } catch (Exception e) {
            System.out.println("Error al cargar la página Configuración.");
            e.printStackTrace();
        }
    }

/**
     * Funcion que valida todos los datos posibles para que pueda acceder un nuevo
     * usuario con sus credenciales.
     * 
     * @return nuevo login.
     */
    private UsuarioModel validarDatosLogin() {
        // Campos vacios
        if (userLoginTextfield == null || userLoginTextfield.getText().isEmpty() ||
        userLoginPasswordfield == null || userLoginPasswordfield.getText().isEmpty()) {
                loginTextAdvise.setText(getPropertiesLanguage().getProperty("loginTextAdvise"));
            return null;
        }
        // Usuario inexistente
        UsuarioModel usuarioLogin = usuarioDAO.buscarPorEmail(userLoginTextfield.getText());
        if (usuarioLogin == null) {
            loginTextAdvise.setText(getPropertiesLanguage().getProperty("loginTextAdvise_errorUser"));
            return null;
        }
        // Contraseña incorrecta (con hash verificado)
        if (!HashUtils.verificarPassword(userLoginPasswordfield.getText(), usuarioLogin.getPassword())) {
            loginTextAdvise.setText(getPropertiesLanguage().getProperty("loginTextAdvise_errorPassword"));
            return null;
        }
        // validacion exitosa
        loginTextAdvise.setText("¡Usuario validado correctamente!");
        return usuarioLogin;
    }


}
