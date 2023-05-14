/**
 * @author Chen, S. Folgueras y Willy
 * @version 1.0.0
 * 12/05/2023
 */
package Dashboard.utils;

import java.io.*;

/**
 * Clase que contiene la lógica para crear un fichero
 * con los datos introducidos en la Base de Datos
 */
public class IO {

    /**
     * Método que crea un fichero y escribe los datos de los usuarios dados de alta
     * @param email email del usuario dado de alta
     * @param password contraseña del usuario dado de alta
     */
    public static void writeUser(String email, String password) {
        try {
            String txt = "User Email: " + email + ", Password: " + password;
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("user_data.txt", true));
            osw.write("\r\n" + txt);
            osw.flush();
            osw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método que crea un fichero y escribe los datos de los usuarios dados de alta
     * @param title titulo del libro insertado
     * @param author autor del libro insertado
     * @param sinopsis sinopsis del libro insertado
     * @param publish_day fecha de publicación del libro insertado
     * @param quantity cantida del libro insertado
     */
    public static void writeDataBook(String title, String author, String sinopsis, String publish_day, String quantity) {
        try {
            String txt = "Book{\r\n"+"\tTitle: " + title + "\r\n\tAuthor: " + author + "\r\n\tSinopsis: " + sinopsis + "\r\n\tPublish_day: " + publish_day + "\r\n\tQuantity: " + quantity +"\r\n}";
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("book_data.txt", true));
            osw.write("\r\n" + txt);
            osw.flush();
            osw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
