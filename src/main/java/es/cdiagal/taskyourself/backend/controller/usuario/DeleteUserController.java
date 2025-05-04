package es.cdiagal.taskyourself.backend.controller.usuario;

import com.jfoenix.controls.JFXButton;

import es.cdiagal.taskyourself.backend.controller.abstractas.AbstractController;
import es.cdiagal.taskyourself.backend.controller.herramientas.SettingsController;
import es.cdiagal.taskyourself.backend.controller.tarea.TareaListController;
import es.cdiagal.taskyourself.backend.controller.utils.PantallasUtil;
import es.cdiagal.taskyourself.backend.controller.utils.PantallasUtil.Pantallas;
import es.cdiagal.taskyourself.backend.dao.UsuarioDAO;
import es.cdiagal.taskyourself.backend.model.usuario.UsuarioModel;
import es.cdiagal.taskyourself.backend.model.utils.service.HashUtils;
import es.cdiagal.taskyourself.initApp.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DeleteUserController extends AbstractController {
    private final UsuarioDAO usuarioDAO;
    private Pantallas pantallaOrigen;
    private UsuarioModel usuario;

    @FXML private TextField deleteEmailTextField;
    @FXML private PasswordField deletePasswordField;
    @FXML private PasswordField deleteRepeatPasswordField;
    @FXML private JFXButton deleteButtonn;
    @FXML private JFXButton backButton;
    @FXML private JFXButton settingsButton;
    @FXML private JFXButton homeButton;
    @FXML private JFXButton calendarButton;
    @FXML private JFXButton notiButton;
    @FXML private JFXButton taskButton;
    @FXML private JFXButton profileButton;


    public DeleteUserController() {
        super();
        this.usuarioDAO = new UsuarioDAO(getRutaArchivoBD());
    }

    /**
     * Constructor de la clase TareaListController.
     * @param usuario UsuarioModel que representa al usuario actual.
     */
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
        deleteEmailTextField.requestFocus();
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

        
        deleteEmailTextField.setPromptText(getPropertiesLanguage().getProperty("deleteEmailTextFieldPrompText"));
        deletePasswordField.setPromptText(getPropertiesLanguage().getProperty("deletePasswordFieldPrompText"));
        deleteRepeatPasswordField.setPromptText(getPropertiesLanguage().getProperty("deleteRepeatPasswordFieldPrompText"));
        deleteButtonn.setText(getPropertiesLanguage().getProperty("deleteButtonn"));
        }
    }

    /**
     * Valida que los datos de entrada sean correctos.
     */
    private boolean validarDatos() {

        // Campos vacios.
        if (deleteEmailTextField.getText().isEmpty() || deletePasswordField.getText().isEmpty() || deleteRepeatPasswordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Campos incompletos", "Debes rellenar todos los campos.");
            return false;
        }
        // Contrasenias diferentes.
        if (!deletePasswordField.getText().equals(deleteRepeatPasswordField.getText())) {
            showAlert(Alert.AlertType.ERROR, "Contraseñas diferentes", "Las contraseñas no coinciden.");
            return false;
        }
        // Usuario inexistente.
        UsuarioModel usuario = usuarioDAO.buscarPorEmail(deleteEmailTextField.getText().trim());
        if (usuario == null) {
            showAlert(Alert.AlertType.ERROR, "Usuario no encontrado", "No existe ningún usuario con ese alias.");
            return false;
        }
        // Contrasenias iguales.
        String password = deletePasswordField.getText();
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

        String email = deleteEmailTextField.getText().trim();
        UsuarioModel usuario = usuarioDAO.buscarPorEmail(email);
        showAlert(Alert.AlertType.WARNING, "Eliminar usuario", "A continuación se va a eliminar el usuario de forma permanente \n ¿Estás seguro de continuar?");
        ButtonType confirmar = new ButtonType("Continuar");
        boolean eliminado = usuarioDAO.eliminar(usuario.getId());
        if (eliminado) {
            showAlert(Alert.AlertType.INFORMATION, "Usuario eliminado", "El usuario con email: '" + email + "' ha sido eliminado correctamente.");
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
     * Funcion que gestiona el acceso a la pantalla de settings mediante un boton.
     */

    @FXML
    public void onClicOpenSettings(){
        try {
        Stage stage = (Stage) settingsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("settings.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        SettingsController controller = fxmlLoader.getController();
        controller.setUsuario(usuario);
        controller.setPantallaOrigen(PantallasUtil.Pantallas.ELIMINAR_CUENTA);
        
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
     * Funcion que accede a la ventana de Inicio.
     */
    @FXML
    protected void onClicHome(){
        try {
            Stage stage = (Stage) homeButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("initApp.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
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
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("userTaskList.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            TareaListController controller = fxmlLoader.getController();
            controller.setUsuario(usuario);
            controller.setPantallaOrigen(PantallasUtil.Pantallas.ELIMINAR_CUENTA);
            controller.userInit();

            stage.setTitle("Lista de tareas");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Funcion que accede a la ventana de Calendario.
     */
    @FXML
    protected void onClicCalendar(){
        try {
            Stage stage = (Stage) calendarButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("initApp.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            TareaListController controller = fxmlLoader.getController();
            controller.setUsuario(usuario);
            controller.setPantallaOrigen(PantallasUtil.Pantallas.ELIMINAR_CUENTA);
            controller.userInit();

            stage.setTitle("Lista de tareas");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Funcion que accede a la ventana de Recordatorios.
     */
    @FXML
    protected void onClicNotiList(){
        try {
            Stage stage = (Stage) notiButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("initApp.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            TareaListController controller = fxmlLoader.getController();
            controller.setUsuario(usuario);
            controller.setPantallaOrigen(PantallasUtil.Pantallas.ELIMINAR_CUENTA);
            controller.userInit();

            stage.setTitle("Rrecordatorios");
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
            Stage stage = (Stage) profileButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("userData.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            UserDataController userDataController = fxmlLoader.getController();
            userDataController.setUsuario(usuario);
            userDataController.setPantallaOrigen(PantallasUtil.Pantallas.ELIMINAR_CUENTA);
            userDataController.usuarioData();

            stage.setTitle("Perfil de usuario");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
