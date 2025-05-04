package es.cdiagal.taskyourself.backend.controller.abstractas;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import es.cdiagal.taskyourself.backend.dao.UsuarioDAO;
import es.cdiagal.taskyourself.backend.model.abstractas.Conexion;

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
}
