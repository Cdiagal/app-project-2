package es.cdiagal.taskyourself.backend.controller.registro;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jfoenix.controls.JFXButton;

import es.cdiagal.taskyourself.backend.controller.abstractas.AbstractController;
import es.cdiagal.taskyourself.backend.dao.UsuarioDAO;
import es.cdiagal.taskyourself.backend.model.usuario.UsuarioModel;
import es.cdiagal.taskyourself.backend.model.utils.service.HashUtils;
import es.cdiagal.taskyourself.initApp.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Clase que gestiona el registro de un usuario.
 * @author cdiagal
 * @version 1.0.0
 */
public class RegisterController  extends AbstractController{
    private final UsuarioDAO usuarioDAO;

    @FXML protected JFXButton backButton;
    @FXML protected Label registerNameLabel;
    @FXML protected TextField registerNameTextField;
    @FXML protected Label nameRegisterAdviseLabel;
    @FXML protected Label registerEmailLabel;
    @FXML protected TextField registerEmailTextField;
    @FXML protected TextField registerRepeatEmailTextField;
    @FXML protected Label emailRegisterAdviseLabel;
    @FXML protected Label registerPasswordLabel;
    @FXML protected PasswordField registerPasswordField;
    @FXML protected PasswordField registerRepeatPasswordField;
    @FXML protected Label passwordRegisterAdviseLabel;
    @FXML protected JFXButton registerUserButton;
    @FXML protected JFXButton homeButton;
    @FXML protected HBox hBoxIcons;
    @FXML protected Hyperlink loginHyperlink;
    @FXML protected JFXButton settingsButton;




    public RegisterController(){
        super();
        this.usuarioDAO = new UsuarioDAO(getRutaArchivoBD());
    }
    /**
     * Metodo que aniade un nuevo usuario a la BBDD en el caso de que las validaciones sean correctas, creando una alerta emergente que avisa
     * si ha sido satisfactorio el registro o no.
     * Automaticamente si es satisfactorio, redirige a la ventana 'Login'.
     */
    @FXML
    protected void onClickRegister(){
        if (validarDatos()) {
        UsuarioModel nuevoUsuario = new UsuarioModel();
        nuevoUsuario.setNombre(registerNameTextField.getText());
        nuevoUsuario.setEmail(registerEmailTextField.getText());
        nuevoUsuario.setPassword(registerPasswordField.getText());
        
        nuevoUsuario.setPassword(HashUtils.hashPassword(registerPasswordField.getText()));
        boolean insertado = usuarioDAO.insertar(nuevoUsuario);

        if (insertado) {
            mostrarAlertaConIcono("¡Registro exitoso!", "El ususario ha sido registrado correctamente", "/imagesFxml/V-check.png");

            // REDIRIGIR A LOGIN
            try {
                Stage stage = (Stage) registerUserButton.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/fxml/login.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 450, 600);
                stage.setTitle("Iniciar sesión");
                stage.setScene(scene);
                stage.sizeToScene();
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error al redirigir al login.");
            }

        } else {
            mostrarAlertaConIcono("¡Registro fallido!", "El usuario no se ha podido registrar", "/imagesFxml/X-fail.png");
        }
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
     * Funcion que lleva hacia la pantalla de Login.
     */
    @FXML
    public void onClickOpenLogin(){
        try {
            Stage stage = (Stage) loginHyperlink.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Inciciar sesión");
            stage.setScene(scene);
            stage.sizeToScene();
            stage.show();
        } catch (Exception e) {
            System.out.println("Error al cargar la página.");
            e.printStackTrace();
        }
    }

    
    /**
     * Metodo que gestiona el acceso a la pantalla de settings mediante un boton.
     */

    @FXML
    public void onClicSettings(){
        try {
        Stage stage = (Stage) settingsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("settings.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 451,600);
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
     * Validación de los datos aportados para el registro antes de hacerse efectivo.
     */
    private boolean validarDatos(){
        if(registerNameTextField.getText().isEmpty()){
            nameRegisterAdviseLabel.setText(getPropertiesLanguage().getProperty("nameRegisterAdviseLabel"));
            return false;
        }

        if(registerPasswordField == null || registerRepeatPasswordField == null
            || registerRepeatPasswordField.getText().isEmpty() || registerPasswordField.getText().isEmpty()){
                passwordRegisterAdviseLabel.setText(getPropertiesLanguage().getProperty("passwordRegisterAdviseLabel_null"));
                return false;
        }

        if(!registerPasswordField.getText().equals(registerRepeatPasswordField.getText())){
            passwordRegisterAdviseLabel.setText(getPropertiesLanguage().getProperty("passwordRegisterAdviseLabel_match"));
            return false;
        } else {
            passwordRegisterAdviseLabel.setText("");
        }
        
        if(registerEmailTextField.getText() == null || registerRepeatEmailTextField.getText() == null
            || registerEmailTextField.getText().isEmpty() || registerRepeatEmailTextField.getText().isEmpty()){
                emailRegisterAdviseLabel.setText(getPropertiesLanguage().getProperty("emailRegisterAdviseLabel_empty"));
                return false;
        }

        if(!registerEmailTextField.getText().equals(registerRepeatEmailTextField.getText())){
            emailRegisterAdviseLabel.setText(getPropertiesLanguage().getProperty("emailRegisterAdviseLabel_match"));
            return false;
        } else {
            emailRegisterAdviseLabel.setText("");
        }
        
        if(!emailCorrecto(registerEmailTextField.getText()) || !emailCorrecto(registerRepeatEmailTextField.getText())){
            emailRegisterAdviseLabel.setText(getPropertiesLanguage().getProperty("emailRegisterAdviseLabel_valid"));
            return false;
        } else {
            emailRegisterAdviseLabel.setText("");
        }
        return true;
    }


    /**
     * Funcion que comprueba que el email introducido cumple con el formato establecido para ser un email.
     * @param email del usuario
     * @return validacion de formato.
     */
    private boolean emailCorrecto(String email){
        String regex = "^[A-Za-z0-9%+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Metodo que retorna una ventana tipo Alert con un icono
     */
    private void mostrarAlertaConIcono(String titulo, String mensaje, String iconoPath) {
        Image image = new Image(iconoPath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(60);
        imageView.setFitHeight(60);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.setGraphic(imageView);
        alert.showAndWait();
    }

    /**
     * Metodo que inicializa el cambio de idioma en el ComboBox.
     */
    @FXML
    public void initialize(){
        if(getPropertiesLanguage()==null){
            setPropertiesLanguage(loadLanguage("lan", getIdiomaActual()));
        }
        registerNameTextField.requestFocus();
        changeLanguage();
    }

    
    /**
     * Funcion que cambia el idioma de las etiquetas y objetos de la ventana
     */
    @FXML
    public void changeLanguage() {
        String language = AbstractController.getIdiomaActual();

        if(getPropertiesLanguage() == null){
            setPropertiesLanguage(loadLanguage("lan", language));
        }
        if(getPropertiesLanguage() != null){

        registerNameLabel.setText(getPropertiesLanguage().getProperty("registerNameLabel"));
        registerNameTextField.setPromptText(getPropertiesLanguage().getProperty("registerNameTextFieldPrompText"));
        nameRegisterAdviseLabel.setText(getPropertiesLanguage().getProperty("nameRegisterAdviseLabel"));
        registerEmailLabel.setText(getPropertiesLanguage().getProperty("registerEmailLabel"));
        registerEmailTextField.setPromptText(getPropertiesLanguage().getProperty("registerEmailTextFieldPrompText"));
        registerRepeatEmailTextField.setPromptText(getPropertiesLanguage().getProperty("registerRepeatEmailTextFieldPrompText"));
        emailRegisterAdviseLabel.setText(getPropertiesLanguage().getProperty("emailRegisterAdviseLabel_null"));
        emailRegisterAdviseLabel.setText(getPropertiesLanguage().getProperty("emailRegisterAdviseLabel_empty"));
        emailRegisterAdviseLabel.setText(getPropertiesLanguage().getProperty("emailRegisterAdviseLabel_match"));
        emailRegisterAdviseLabel.setText(getPropertiesLanguage().getProperty("emailRegisterAdviseLabel_valid"));
        registerPasswordLabel.setText(getPropertiesLanguage().getProperty("registerPasswordLabel"));
        registerPasswordField.setPromptText(getPropertiesLanguage().getProperty("registerPasswordFieldPrompText"));
        registerRepeatPasswordField.setPromptText(getPropertiesLanguage().getProperty("registerRepeatPasswordFieldPrompText"));
        passwordRegisterAdviseLabel.setText(getPropertiesLanguage().getProperty("passwordRegisterAdviseLabel_null"));
        passwordRegisterAdviseLabel.setText(getPropertiesLanguage().getProperty("passwordRegisterAdviseLabel_match"));
        registerUserButton.setText(getPropertiesLanguage().getProperty("registerUserButton"));
        loginHyperlink.setText(getPropertiesLanguage().getProperty("loginHyperlink"));
        }
    }
}

