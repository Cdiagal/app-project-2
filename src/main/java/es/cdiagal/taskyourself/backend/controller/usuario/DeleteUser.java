package es.cdiagal.taskyourself.backend.controller.usuario;

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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DeleteUser extends AbstractController {
    private final UsuarioDAO usuarioDAO;

    @FXML private TextField registerEmailTextField;
    @FXML private PasswordField registerPasswordField;
    @FXML private PasswordField registerRepeatPasswordField;
    @FXML private JFXButton deleteButtonn;
    @FXML private JFXButton backButton;

    public DeleteUser() {
        super();
        this.usuarioDAO = new UsuarioDAO(getRutaArchivoBD());
    }

    /**
     * Metodo que inicializa el cambio de idioma en el ComboBox.
     */
    @FXML
    public void initialize(){
        if(getPropertiesLanguage()==null){
            setPropertiesLanguage(loadLanguage("lan", getIdiomaActual()));
        }
        registerEmailTextField.requestFocus();
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

        
        registerEmailTextField.setPromptText(getPropertiesLanguage().getProperty("nickTextFieldPromptText"));
        registerPasswordField.setPromptText(getPropertiesLanguage().getProperty("deletePasswordField1PrompText"));
        registerRepeatPasswordField.setPromptText(getPropertiesLanguage().getProperty("deletePasswordfield2PrompText"));
        deleteButtonn.setText(getPropertiesLanguage().getProperty("deleteButton"));

        
        }
    }

    /**
     * Valida que los datos de entrada sean correctos.
     */
    private boolean validarDatos() {

        // Campos vacios.
        if (registerEmailTextField.getText().isEmpty() || registerPasswordField.getText().isEmpty() || registerRepeatPasswordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Campos incompletos", "Debes rellenar todos los campos.");
            return false;
        }
        // Contrasenias diferentes.
        if (!registerPasswordField.getText().equals(registerRepeatPasswordField.getText())) {
            showAlert(Alert.AlertType.ERROR, "Contraseñas diferentes", "Las contraseñas no coinciden.");
            return false;
        }
        // Usuario inexistente.
        UsuarioModel usuario = usuarioDAO.buscarPorEmail(registerEmailTextField.getText().trim());
        if (usuario == null) {
            showAlert(Alert.AlertType.ERROR, "Usuario no encontrado", "No existe ningún usuario con ese alias.");
            return false;
        }
        // Contrasenias iguales.
        String password = registerPasswordField.getText();
        if (!HashUtils.verificarPassword(password, usuario.getPassword())) {
            showAlert(Alert.AlertType.ERROR, "Contraseña incorrecta", "La contraseña no coincide con la del usuario.");
            return false;
        }
        return true;
    }

    /**
     * Gestiona la orden del boton de eliminacion de usuario.
     */
    @FXML
    protected void onClickDelete() {
        if (!validarDatos()) return;

        String email = registerEmailTextField.getText().trim();
        UsuarioModel usuario = usuarioDAO.buscarPorEmail(email);
        showAlert(Alert.AlertType.WARNING, "Eliminar usuario", "A continuación se va a eliminar el usuario de forma permanente \n ¿Estás seguro de continuar?");
        ButtonType confirmar = new ButtonType("Continuar");
        boolean eliminado = usuarioDAO.eliminar(usuario.getId());
        if (eliminado) {
            showAlert(Alert.AlertType.INFORMATION, "Usuario eliminado", "El usuario '" + nick + "' ha sido eliminado correctamente.");
            onClickBackButton();
        } else {
            showAlert(Alert.AlertType.ERROR, "Error al eliminar", "Ha ocurrido un error al intentar eliminar el usuario.");
        }
    }

    /**
     * Funcion que lleva a la ventana updateUserData.
     */
    @FXML
    protected void onClickBackButton() {
        volverAPantallaOrigen(backButton, pantallaOrigen);
    }

    
    /**
     * Funcion que muestra una alerta genérica.
     */
    private void showAlert(Alert.AlertType type, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle("Eliminar usuario");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
