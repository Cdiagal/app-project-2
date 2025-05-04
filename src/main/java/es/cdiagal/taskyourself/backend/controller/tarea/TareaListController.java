package es.cdiagal.taskyourself.backend.controller.tarea;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.jfoenix.controls.JFXButton;

import es.cdiagal.taskyourself.backend.controller.abstractas.AbstractController;
import es.cdiagal.taskyourself.backend.controller.utils.PantallasUtil.Pantallas;
import es.cdiagal.taskyourself.backend.dao.TareaDAO;
import es.cdiagal.taskyourself.backend.dao.UsuarioDAO;
import es.cdiagal.taskyourself.backend.model.tarea.TareaModel;
import es.cdiagal.taskyourself.backend.model.usuario.UsuarioModel;
import es.cdiagal.taskyourself.initApp.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TareaListController extends AbstractController {

    private Pantallas pantallaOrigen;
    private UsuarioModel usuario;
    private UsuarioDAO usuarioDAO;
    private TareaDAO tareaDAO;
    @FXML private Label welcomeTaksListLabel;
    @FXML private Label dateTaksListLabel;
    @FXML private TextField findTaskTextField;
    @FXML private ScrollPane taskListScrollPane;
    @FXML private VBox taskListScrollVBox;
    @FXML private JFXButton settingsButton;
    @FXML private JFXButton backButton;
    @FXML private JFXButton homeButton;
    @FXML private JFXButton calendarButton;
    @FXML private JFXButton notiButton;
    @FXML private JFXButton taskButton;
    @FXML private JFXButton profileButton;
    @FXML private JFXButton nuevaTareaButton;

    /**
     * Constructor vacío de la clase TareaListController.
     */
    public TareaListController() {
        super();
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
     * Carga los datos del usuario y las tareas en la interfaz.
     */
    public void userInit(){
        if(usuario != null) {
            welcomeTaksListLabel.setText(getPropertiesLanguage().getProperty("welcomeTaksListLabel") + ", " + usuario.getNombre() + "!");
            dateTaksListLabel.setText(getPropertiesLanguage().getProperty("dateTaksListLabel") + java.time.LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            tareaDAO = new TareaDAO(getRutaArchivoBD());
            List<TareaModel> tareas = tareaDAO.obtenerTareasPorUsuario(usuario.getId());
            System.out.println("Tareas encontradas: " + tareas.size());
            cargarTareas(tareas);

            /**
             * Configuracion del campo de busqueda de tareas.
             * Se añade un listener para que cada vez que se escriba en el campo de búsqueda, se llame al metodo buscarTareas con el texto ingresado.
             * También se configura el evento de accion para que al presionar Enter, se llame al mismo metodo.
             */
            findTaskTextField.textProperty().addListener((obs, oldValue, newValue) -> {
                buscarTareas(newValue);
            });
            findTaskTextField.setOnAction(e -> {
                buscarTareas(findTaskTextField.getText());
            });
        }
    }

    
    
    /**
     * Carga las tareas en la interfaz.
     * @param tareas Lista de tareas a cargar.
     * Se utiliza para mostrar las tareas del usuario en la interfaz.
     */
    public void cargarTareas(List<TareaModel> tareas) {
        taskListScrollVBox.getChildren().clear();
        for (TareaModel tarea : tareas) {
            taskListScrollVBox.getChildren().add(crearTareaVisual(tarea));
        }
    }

    /**
     * Metodo que se ejecuta al escribir en el campo de busqueda.
     * @param texto Texto ingresado en el campo de busqueda.
     * Se utiliza para filtrar las tareas segun el texto ingresado.
     */
    private void buscarTareas(String texto) {
        if (tareaDAO == null) {
            tareaDAO = new TareaDAO(getRutaArchivoBD());
        }
    
        List<TareaModel> resultados;
        if (texto == null || texto.trim().isEmpty()) {
            resultados = tareaDAO.obtenerTareasPorUsuario(usuario.getId());
        } else {
            resultados = tareaDAO.buscarTareasPorUsuarioYTexto(usuario.getId(), texto.trim());
        }
    
        cargarTareas(resultados);
    }
    
    
    /**
     * Crea una representacion visual de una tarea.
     * @param tarea Tarea a representar.
     * @return Un HBox que contiene la representacion visual de la tarea.
     */
        private HBox crearTareaVisual(TareaModel tarea) {
        HBox contenedor = new HBox(10);
        contenedor.getStyleClass().add("tarea-box");
        contenedor.setAlignment(Pos.CENTER_LEFT);

        CheckBox check = new CheckBox();
        check.setSelected(tarea.getTareaCompletada());
        check.getStyleClass().add("check-completado");

        // VBox con titulo y fecha
        VBox vBox = new VBox();
        vBox.setSpacing(4);
        vBox.setTranslateX(10); // desplaza un poco a la derecha

        Label tituloLabel = new Label(tarea.getTitulo());
        tituloLabel.getStyleClass().add("tarea-titulo");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        Label fechaLabel = new Label(
            tarea.getFechaObjetivo() != null
                ? tarea.getFechaObjetivo().format(formatter)
                : "Sin fecha objetivo"
        );
        fechaLabel.getStyleClass().add("tarea-fecha");

        vBox.getChildren().addAll(tituloLabel, fechaLabel);

        // Espaciador flexible para empujar a la derecha
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Campana e interruptor de recordatorio
        ImageView campana = new ImageView(
            new Image(getClass().getResource("/imagesFxml/recordatorio-96.png").toExternalForm(), 20, 20, true, true)
        );
        campana.setVisible(false);

        CheckBox toggleRecordatorio = new CheckBox();
        toggleRecordatorio.getStyleClass().add("check-recordatorio");
        toggleRecordatorio.setOnAction(e -> campana.setVisible(toggleRecordatorio.isSelected()));

        // Boton de editar (lapiz)
        Button editar = new Button();
        editar.getStyleClass().add("icon-button");
        ImageView iconoLapiz = new ImageView(
            new Image(getClass().getResource("/imagesFxml/lapiz.png").toExternalForm(), 20, 20, true, true)
        );
        editar.setGraphic(iconoLapiz);
        editar.setOnAction(e -> editarTarea(tarea));

        // VBox con campana encima y checkbox debajo
        VBox vBoxDerecha = new VBox(5, campana, toggleRecordatorio);
        vBoxDerecha.setAlignment(Pos.CENTER);

        // Contenedor final con lapiz + VBox campana/recordatorio
        HBox derecha = new HBox(editar, vBoxDerecha);
        derecha.setAlignment(Pos.CENTER_RIGHT);
        derecha.setSpacing(5);

        contenedor.getChildren().addAll(check, vBox, spacer, derecha);
        VBox.setMargin(contenedor, new Insets(0, 8, 0, 8));
        return contenedor;
    }

    


    /**
     * Metodo que se ejecuta al hacer clic en el botón de editar tarea.
     * @param tarea Tarea a editar.
     * Se utiliza para abrir la ventana de edicion de tareas.
     */
    private void editarTarea(TareaModel tarea) {
        // logica para ir a la ventana de edición pasando la tarea
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
            Stage stage = (Stage) calendarButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("calendar.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 450, 600);
            stage.setTitle("Calendario");
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
            Stage stage = (Stage) notiButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("notifications.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 450, 600);
            stage.setTitle("Notificaciones");
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
            Scene scene = new Scene(fxmlLoader.load(), 450, 600);
            stage.setTitle("Lista de tareas");
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
    protected void onClickNuevaTarea(){
        try {
            Stage stage = (Stage) nuevaTareaButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("newTask.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 450, 600);
            stage.setTitle("Nueva tarea");
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


        }
    }


}