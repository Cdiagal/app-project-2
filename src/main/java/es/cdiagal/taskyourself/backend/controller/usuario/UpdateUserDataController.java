package es.cdiagal.taskyourself.backend.controller.usuario;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.jfoenix.controls.JFXButton;

import es.cdiagal.taskyourself.backend.controller.abstractas.AbstractController;
import es.cdiagal.taskyourself.backend.controller.herramientas.SettingsController;
import es.cdiagal.taskyourself.backend.controller.tarea.TareaListController;
import es.cdiagal.taskyourself.backend.controller.utils.PantallasUtil;
import es.cdiagal.taskyourself.backend.controller.utils.PantallasUtil.Pantallas;
import es.cdiagal.taskyourself.backend.dao.UsuarioDAO;
import es.cdiagal.taskyourself.backend.model.usuario.UsuarioModel;
import es.cdiagal.taskyourself.initApp.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UpdateUserDataController extends AbstractController {

    private Pantallas pantallaOrigen;
    private UsuarioModel usuario;
    private UsuarioDAO usuarioDAO;
    @FXML private TextField registerNameTextField;
    @FXML private TextField registerEmailTextField;
    @FXML private TextField registerRepeatEmailTextField;
    @FXML private TextField registerPasswordField;
    @FXML private TextField registerRepeatPasswordField;
    @FXML private JFXButton backButton;
    @FXML private JFXButton settingsButton;
    @FXML private JFXButton editProfileButton;
    @FXML private JFXButton homeButton;
    @FXML private JFXButton calendarButton;
    @FXML private JFXButton notiButton;
    @FXML private JFXButton taskButton;
    @FXML private JFXButton profileButton;
    @FXML private JFXButton changeImageButton;
    @FXML private ImageView imageUpdateCamera;


    public UpdateUserDataController() {
        super();
        this.usuarioDAO = new UsuarioDAO(getRutaArchivoBD());
    }



    public void usuarioData(){
        if (usuario != null && getPropertiesLanguage() != null) {
            registerNameTextField.setText(usuario.getNombre() );
            registerEmailTextField.setText(usuario.getEmail());
            registerRepeatEmailTextField.setText(usuario.getEmail());
            registerPasswordField.setText(usuario.getPassword());
            registerRepeatPasswordField.setText(usuario.getPassword());

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
     * Metodo que carga la imagen del usuario
     */
    private void cargarImagenUsuario() {
        byte[] bytes = usuario.getImagen();
        if (bytes != null) {
            imageUpdateCamera.setImage(new Image(new ByteArrayInputStream(bytes)));
        } else {
            imageUpdateCamera.setImage(new Image(getClass().getResourceAsStream("/images/profile.png")));
        }
        aplicarClipCircular(imageUpdateCamera);
    }

    /**
     * Genera un circulo para aplicarlo al ImageView y que tenga forma de circulo.
     */
    private void aplicarClipCircular(ImageView imageView) {
        if (imageView != null) {
    
            double radius = Math.min(imageView.getFitWidth(), imageView.getFitHeight()) / 2;
            Circle clip = new Circle(imageView.getFitWidth() / 2, imageView.getFitHeight() / 2, radius);
            imageView.setClip(clip);
        } else {
            System.out.println("Error: ImageView es null");
        }
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
            controller.setPantallaOrigen(PantallasUtil.Pantallas.USUARIO_EDITAR);
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
            controller.setPantallaOrigen(PantallasUtil.Pantallas.USUARIO_EDITAR);
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
            controller.setPantallaOrigen(PantallasUtil.Pantallas.USUARIO_EDITAR);
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
            userDataController.setPantallaOrigen(PantallasUtil.Pantallas.USUARIO_EDITAR);
            userDataController.usuarioData();

            stage.setTitle("Perfil de usuario");
            stage.setScene(scene);
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
            registerNameTextField.setPromptText(getPropertiesLanguage().getProperty("registerNameTextField"));
            registerEmailTextField.setPromptText(getPropertiesLanguage().getProperty("registerEmailTextField"));
            registerRepeatEmailTextField.setPromptText(getPropertiesLanguage().getProperty("registerRepeatEmailTextField"));
            registerPasswordField.setPromptText(getPropertiesLanguage().getProperty("registerPasswordField"));
            registerRepeatPasswordField.setPromptText(getPropertiesLanguage().getProperty("registerRepeatPasswordField"));
            editProfileButton.setText(getPropertiesLanguage().getProperty("editProfileButton"));
            

        }
    }

    /**
     * Metodo que cambia la imagen de perfil del usuario abriendo el explorador de archivos para elegirla.
     */
    @FXML
    public void onClicChangeImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar imagen de perfil");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg"));
    
        // Muestra el explorador de archivos
        File archivo = fileChooser.showOpenDialog(new Stage());
        if (archivo != null) {
            try {
                // Leer la imagen seleccionada como bytes
                imagenSeleccionada = Files.readAllBytes(archivo.toPath());
                // Usar URI para asegurarse de que la ruta es válida
                Image imagen = new Image(archivo.toURI().toString());  // Usar URI en lugar de ruta directa
                updateImageView.setImage(imagen);
                aplicarClipCircular(updateImageView);
                usuario.setImagen(imagenSeleccionada);
    
                // Intentar actualizar la imagen en la base de datos
                boolean imagenActualizada = usuarioDAO.actualizarImagen(usuario.getId(), imagenSeleccionada);
                if (imagenActualizada) {
                    mostrarAlerta("Imagen cargada", "Imagen seleccionada correctamente. Recuerda guardar para aplicarla.", "/images/alert_icon.png");
                } else {
                    mostrarAlerta("Error", "No se pudo actualizar la imagen en la base de datos.", "/images/alert_icon.png");
                }
            } catch (IOException e) {
                mostrarAlerta("Error", "No se pudo leer la imagen seleccionada.", "/images/alert_icon.png");
            }
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


