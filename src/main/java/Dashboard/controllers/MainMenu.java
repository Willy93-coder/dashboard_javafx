/**
 * @author Chen, S. Folgueras y Willy
 * @version 1.0.0
 * 12/05/2023
 */
package Dashboard.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Clase que contiene toda la lógica del menú principal
 */
public class MainMenu implements Initializable {

    /**
     * Botón para ir a la vista
     * de la tabla libros
     */
    @FXML
    private Button btnBooks;

    /**
     * Botón para ir a la vista
     * de dar de alta usuarios
     */
    @FXML
    private Button btnUser;

    /**
     * Botón para ir a la vista
     * de insertar nuevos libros
     */
    @FXML
    private Button btnAddBooks;

    /**
     * Botón para ir a la vista
     * de la tabla de información de alquileres
     */
    @FXML
    private Button btnRent;

    /**
     * Método para inicializar la vista
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * Método que contiene la lógica
     * para ir a la vista de la tabla de libros
     */
    @FXML
    private void showBooks(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dashboard/Books.fxml"));
            Parent root = loader.load();
            BooksController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Books");
            stage.show();
            stage.setOnCloseRequest(e -> {controller.closeWindows();});
            Stage myStage = (Stage) this.btnBooks.getScene().getWindow();
            myStage.close();

        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * Método que contiene la lógica
     * para ir ir a la vista
     * de dar de alta usuarios
     */
    @FXML
    private void insertUser(){
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dashboard/InsertUser.fxml"));
        Parent root = loader.load();
        InsertUserController controller = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Insert user");
        stage.show();
        stage.setOnCloseRequest(e -> {controller.closeWindows();});
        Stage myStage = (Stage) this.btnUser.getScene().getWindow();
        myStage.close();

        }catch (IOException e){
        System.err.println(e.getMessage());
        }
    }

    /**
     * Método que contiene la lógica
     * para ir ir a la vista
     * de insertar libro
     */
    @FXML
    private void insertBook() throws IOException {
    try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dashboard/InsertBook.fxml"));
        Parent root = loader.load();
        InsertBookController controller = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Insert book");
        stage.show();
        stage.setOnCloseRequest(e -> {
            try {
                controller.closeWindows();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        Stage myStage = (Stage) this.btnAddBooks.getScene().getWindow();
        myStage.close();

        }catch (IOException e){
        System.err.println(e.getMessage());
        }
    }

    /**
     * Método que contiene la lógica
     * para ir ir a la vista
     * de la tabla de información
     * del alquiler de los libros
     */
    @FXML
    private void rentBooks(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dashboard/Rent.fxml"));
            Parent root = loader.load();
            RentController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Rent table");
            stage.show();
            stage.setOnCloseRequest(e -> {
                controller.closeWindows();
            });
            Stage myStage = (Stage) this.btnRent.getScene().getWindow();
            myStage.close();

        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * Método para cerrar las ventanas
     */
    public void closeWindows() {
    }
}
