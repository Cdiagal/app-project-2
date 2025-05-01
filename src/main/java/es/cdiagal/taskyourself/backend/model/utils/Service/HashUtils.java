package es.cdiagal.taskyourself.backend.model.utils.service;

import org.mindrot.jbcrypt.BCrypt;
/**
 * Clase que gestiona y crea un código hash para encriptar las contrasenias de los usuarios.
 * @author cdiagal
 * @version 1.0.0
 */

public class HashUtils {
    
    /**
     * Aplica hashing seguro a una contraseña con BCrypt.
     * @param password contraseña sin hash.
     * @return contraseña hasheada.
     */
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Verifica si una contraseña coincide con su hash.
     * @param password contraseña introducida.
     * @param hashedPassword hash guardado.
     * @return true si la contraseña es válida.
     */
    public static boolean verificarPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
