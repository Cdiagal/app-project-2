package es.cdiagal.taskyourself.backend.controller.usuario;

import com.jfoenix.controls.JFXButton;

import es.cdiagal.taskyourself.backend.controller.abstractas.AbstractController;
import es.cdiagal.taskyourself.backend.controller.herramientas.SettingsController;
import es.cdiagal.taskyourself.backend.controller.tarea.TareaListController;
import es.cdiagal.taskyourself.backend.controller.utils.PantallasUtil;
import es.cdiagal.taskyourself.backend.controller.utils.PantallasUtil.Pantallas;
import es.cdiagal.taskyourself.backend.controller.utils.SessionManager;
import es.cdiagal.taskyourself.backend.dao.UsuarioDAO;
import es.cdiagal.taskyourself.backend.model.usuario.UsuarioModel;
import es.cdiagal.taskyourself.initApp.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
    @FXML private JFXButton editProfileButton;
    @FXML private JFXButton logoutButton;
    @FXML private JFXButton helpButton;
    @FXML private JFXButton openDeleteAccountButton;
    

    


    public UserDataController() {
        super();
        this.usuarioDAO = new UsuarioDAO(getRutaArchivoBD());
    }

    public void setUsuario(UsuarioModel usuario){
        this.usuario = usuario;
        usuarioData();
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
        if (usuario != null) {

            userNameLabel.setText(usuario.getNombre());
            emailUserDataLabel.setText( usuario.getEmail());

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
     * Metodo que se ejecuta al inicializar la vista.
     * Se utiliza para cargar los elementos de la interfaz y establecer el idioma.
     */
    public void initialize() {
        if(getPropertiesLanguage()==null){
            setPropertiesLanguage(loadLanguage("lan", getIdiomaActual()));
        }
        changeLanguage();
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

    
    /**
     * Funcion que lleva hacia la pantalla Settings.
     */
    @FXML
    protected void onClickOpenUpdateUserData(){
        try {
            Stage stage = (Stage) editProfileButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("updateUserData.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            UpdateUserDataController controller = fxmlLoader.getController();
            controller.setUsuario(usuario);
            controller.setPantallaOrigen(PantallasUtil.Pantallas.PERFIL);
            controller.usuarioData();

            stage.setTitle("Ajustes");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        controller.setPantallaOrigen(PantallasUtil.Pantallas.PERFIL);
        
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
            controller.setPantallaOrigen(PantallasUtil.Pantallas.PERFIL);
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
            controller.setPantallaOrigen(PantallasUtil.Pantallas.PERFIL);
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
            controller.setPantallaOrigen(PantallasUtil.Pantallas.PERFIL);
            controller.userInit();

            stage.setTitle("Recordatorios");
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
            userDataController.setPantallaOrigen(PantallasUtil.Pantallas.PERFIL);
            userDataController.usuarioData();

            stage.setTitle("Perfil de usuario");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Funcion que cierra la sesión del usuario.
     */
    @FXML
    public void onClickLogout() {
        try {
            SessionManager.getInstance().cerrarSesion();
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("initApp.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("TaskYourself");
            stage.setScene(scene);
            stage.sizeToScene();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Funcion que accede a la ventana de soporte de la app.
     */
    @FXML
    protected void onClickHelp() {
        try {
            Stage stage = (Stage) helpButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("support.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Soporte");
            stage.setScene(scene);
            stage.sizeToScene();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Funcion que lleva hacia la pantalla de Eliminar usuario.
     */
    @FXML
    protected void onClickOpenDelete() {
        try {
            Stage stage = (Stage) openDeleteAccountButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("deleteUser.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Eliminar usuario");
            stage.setScene(scene);
            stage.sizeToScene();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            editProfileButton.setText(getPropertiesLanguage().getProperty("editProfileButton"));
            logoutButton.setText(getPropertiesLanguage().getProperty("logoutButton"));
            helpButton.setText(getPropertiesLanguage().getProperty("helpButton"));
            openDeleteAccountButton.setText(getPropertiesLanguage().getProperty("openDeleteAccountButton"));

        }
    }

    /**
     * Metodo que pasa a la ventana update para cambiar la imagen.
     */
    @FXML
    public void onClicChangeImage() {
        try {
            Stage stage = (Stage) changeImageButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/fxml/updateUserData.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
    
            UpdateUserDataController updateUserDataController = fxmlLoader.getController();
            updateUserDataController.setUsuario(this.usuario);
    
            stage.setTitle("Actualizar perfil");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga una imagen generica para el perfil.
     *

    private void cargarImagenUsuario() {
        try {
            if (usuario != null && usuario.getImagen() != null) {
                // Si tiene imagen, cargar desde byte[]
                ByteArrayInputStream bis = new ByteArrayInputStream(usuario.getImagen());
                Image imagen = new Image(bis);
                updateImageView.setImage(imagen);
            } else {
                // Si no tiene imagen, cargar por defecto desde resources
                InputStream is = getClass().getResourceAsStream("/images/profile.png"); // Usa la ruta correcta
                if (is == null) {
                    System.out.println("No se pudo encontrar la imagen por defecto.");
                    return; // Salir si la imagen no se encuentra
                }
                Image imagen = new Image(is);
                updateImageView.setImage(imagen);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        aplicarClipCircular(updateImageView);
    }

    /**
     * Genera un circulo para aplicarlo al ImageView y que tenga forma de circulo.
    
    private void aplicarClipCircular(ImageView imageView) {
        if (imageView != null) {
    
            double radius = Math.min(imageView.getFitWidth(), imageView.getFitHeight()) / 2;
            Circle clip = new Circle(imageView.getFitWidth() / 2, imageView.getFitHeight() / 2, radius);
            imageView.setClip(clip);
        } else {
            System.out.println("Error: ImageView es null");
        }
    }*/

}
