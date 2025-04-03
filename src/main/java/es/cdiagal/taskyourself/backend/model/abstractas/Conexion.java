package es.cdiagal.taskyourself.backend.model.abstractas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que gestiona la conexion con una base de datos.
 * @author cdiagal
 * @version 1.0.0
 */

public class Conexion {
    private String rutaArchivoBD;
    private Connection connection;

    /**
     * Constructor vacio.
     */
    public Conexion(){

    }

    /**
     * Constructor de la clase Conexion.
     * @param unaRutaArchivoBD
     */
    public Conexion(String unaRutaArchivoBD){
        if (unaRutaArchivoBD == null || unaRutaArchivoBD.isEmpty()){
            System.out.println("La base de datos es nula o está vacía.");
        }
    }

    /**
     * getter de rutaArchivoBD y Conection.
     */
    public String getRutaArchivoBD() {
        return this.rutaArchivoBD;
    }

    public Connection getConnection() {
        try {
            if(connection == null){
                connection = DriverManager.getConnection("jdbc:sqlite" + rutaArchivoBD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.connection;
    }

    /**
     * Metodo que abre la conexion con la bbdd.
     * @return conexion.
     * @throws SQLException error controlado.
     */
    public Connection conectar() throws SQLException{
        if (connection == null || connection.isClosed()){
            connection = DriverManager.getConnection("jdbc:sqlite" + rutaArchivoBD);
        }
        return connection;
    }

    /**
     * Metodo que cierra la conexion con la bbdd.
     */
    public Connection cerrar(){
        try {
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
