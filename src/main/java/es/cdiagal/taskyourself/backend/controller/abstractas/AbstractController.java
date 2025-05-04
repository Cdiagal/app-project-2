package es.cdiagal.taskyourself.backend.controller.abstractas;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import es.cdiagal.taskyourself.backend.controller.utils.PantallasUtil;
import es.cdiagal.taskyourself.backend.controller.utils.PantallasUtil.Pantallas;
import es.cdiagal.taskyourself.backend.dao.UsuarioDAO;
import es.cdiagal.taskyourself.backend.model.abstractas.Conexion;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase abstracta que gestiona los idiomas y ficheros de la app.
 * @author cdiagal
 * @version 1.0.0
 */

public abstract class AbstractController extends Conexion{

    /**
     * Ruta de la base de datos.
     */
    static final String PATH_DB =  "src/main/resources/usuarios.db";

    /**
     * Atributos privados de la clase.
     */
    protected PantallasUtil.Pantallas pantallaOrigen;
    private UsuarioDAO usuarioDAO;

    private Properties propertiesIdioma;
    private static String idiomaActual = "español";


    public static String getIdiomaActual(){
        return idiomaActual;
    }
    public static void setIdiomaActual(String idioma){
        idiomaActual = idioma;
    }

    /**
     * Constructor de la clase que crea una instancia de usuario usando la ruta de la BBDD.
     */
    
    public AbstractController() {
        super(PATH_DB);
        try {
            this.usuarioDAO = new UsuarioDAO(PATH_DB);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Getters y setters para los idiomas.
     * @param properties
     */
    public void setPropertiesLanguage(Properties properties) {
        propertiesIdioma = properties;
    }

    public Properties getPropertiesLanguage() {
        return this.propertiesIdioma;
    }

    /**
     * Carga los idiomas alojados en los .properties.
     * @param nombreFichero
     * @param idioma
     * @return loadIdioma.
     */
    public Properties loadLanguage(String nombreFichero, String idioma) {
        Properties properties = new Properties();
        
        if (nombreFichero == null || idioma == null) {
            return properties;
        }
        
        String path = "src/main/resources/" + nombreFichero + "-" + idioma + ".properties";

        File file = new File(path);

        if (!file.exists() || !file.isFile()) {
            System.out.println("Path:"+file.getAbsolutePath());
            return properties;
        }
        
        try {
            FileInputStream input = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(input, "UTF-8");
            properties.load(isr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return properties;
    }


    /**
     * Getter de la clase.
     * @return usuarioServiceModel.
     */
    public UsuarioDAO getUsuarioServiceModel() {
        return this.usuarioDAO;
    }


    /**
     * Getter de la clase.
     * @return pantallaOrigen.
     */
    public void setPantallaOrigen(PantallasUtil.Pantallas origen) {
        this.pantallaOrigen = origen;
    }

    /**
     * Funcion que cambia la pantalla de la aplicacion.
     * @param stage del que viene la escena.
     */
    public void volverAPantallaOrigen(Node trigger, Pantallas pantallaOrigen) {
        try {
            Stage stage = (Stage) trigger.getScene().getWindow();
            FXMLLoader loader;

            switch (pantallaOrigen) {
                case INICIO -> loader = new FXMLLoader(getClass().getResource("/es/cdiagal/taskyourself/initApp/initApp.fxml"));
                case LOGIN -> loader = new FXMLLoader(getClass().getResource("/es/cdiagal/taskyourself/initApp/login.fxml"));
                case REGISTRO -> loader = new FXMLLoader(getClass().getResource("/es/cdiagal/taskyourself/initApp/register.fxml"));
                case TAREAS -> loader = new FXMLLoader(getClass().getResource("/es/cdiagal/taskyourself/initApp/userTaskList.fxml"));
                case TAREA_NUEVA -> loader = new FXMLLoader(getClass().getResource("/es/cdiagal/taskyourself/initApp/newTask.fxml"));
                case TAREA_EDITAR -> loader = new FXMLLoader(getClass().getResource("/es/cdiagal/taskyourself/initApp/editTask.fxml"));
                case AJUSTES -> loader = new FXMLLoader(getClass().getResource("/es/cdiagal/taskyourself/initApp/settings.fxml"));
                case PERFIL -> loader = new FXMLLoader(getClass().getResource("/es/cdiagal/taskyourself/initApp/userData.fxml"));
                case ELIMINAR_CUENTA -> loader = new FXMLLoader(getClass().getResource("/es/cdiagal/taskyourself/initApp/deleteUser.fxml"));
                case RECUPERAR_CONTRASENIA -> loader = new FXMLLoader(getClass().getResource("/es/cdiagal/taskyourself/initApp/recovery.fxml"));
                case NOTIFICACIONES -> loader = new FXMLLoader(getClass().getResource("/es/cdiagal/taskyourself/initApp/notifications.fxml"));
                case USUARIO_EDITAR -> loader = new FXMLLoader(getClass().getResource("/es/cdiagal/taskyourself/initApp/updateUserData.fxml"));
                default -> loader = new FXMLLoader(getClass().getResource("/es/cdiagal/taskyourself/initApp/initApp.fxml"));
            }

            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.sizeToScene();
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
