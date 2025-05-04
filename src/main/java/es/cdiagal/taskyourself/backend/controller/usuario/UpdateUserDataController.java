package es.cdiagal.taskyourself.backend.controller.usuario;

import com.jfoenix.controls.JFXButton;

import es.cdiagal.taskyourself.backend.controller.abstractas.AbstractController;
import es.cdiagal.taskyourself.backend.controller.utils.PantallasUtil.Pantallas;
import es.cdiagal.taskyourself.backend.dao.UsuarioDAO;
import es.cdiagal.taskyourself.backend.model.usuario.UsuarioModel;
import javafx.fxml.FXML;

public class UpdateUserDataController extends AbstractController {

    private Pantallas pantallaOrigen;
    private UsuarioModel usuario;
    private UsuarioDAO usuarioDAO;
    @FXML private JFXButton registerNameTextField;
    @FXML private JFXButton registerEmailTextField;
    @FXML private JFXButton registerPasswordField;
    @FXML private JFXButton backButton;
    @FXML private JFXButton settingsButton;
    @FXML private JFXButton editProfileButton;
    @FXML private JFXButton logoutButton;
    @FXML private JFXButton helpButton;
    @FXML private JFXButton openDeleteAccount;
    @FXML private JFXButton homeButton;
    @FXML private JFXButton calendarButton;
    @FXML private JFXButton notiButton;
    @FXML private JFXButton taskButton;
    @FXML private JFXButton profileButton;

    public UpdateUserDataController() {
        super();
        this.usuarioDAO = new UsuarioDAO(getRutaArchivoBD());
    }

    public void usuarioData(){
        if (usuario != null && getPropertiesLanguage() != null) {
            registerNameTextField.setText(usuario.getNombre() );
            registerEmailTextField.setText(usuario.getEmail());
            registerPasswordField.setText(usuario.getPassword());

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
     * Método que establece el usuario actual.
     * @param usuario que representa el usuario actual.
     */
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
     * Funcion que lleva hacia la pantalla anterior desde la que se accede a Settings.
     * Hace uso de un switch para determinar la pantalla de origen.
     * @param pantallaOrigen Pantalla de origen.
     */
    @FXML
    protected void onClickBackButton(){
        volverAPantallaOrigen(backButton, pantallaOrigen);
    }

    
}
