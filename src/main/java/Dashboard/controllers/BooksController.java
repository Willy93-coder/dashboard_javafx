/**
 * @author Chen, S. Folgueras y Willy
 * @version 1.0.0
 * 12/05/2023
 */
package Dashboard.controllers;

import Dashboard.utils.DataBase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static Dashboard.utils.DataBase.showAllBooks;

public class BooksController implements Initializable {

    /**
     * Tabla de datos de los libros
     */
    @FXML
    private TableView tbl;

    /**
     * Columna de titulo del libro
     */
    @FXML
    private TableColumn<DataBase, String> tblTitle;

    /**
     * Columna del autor del libro
     */
    @FXML
    private TableColumn<DataBase, String> tblAuthor;

    /**
     * Columna de fecha de publicación del libro
     */
    @FXML
    private TableColumn<DataBase, Date> tblDate;

    /**
     * Columna de la cantidad del libro
     */
    @FXML
    private TableColumn<DataBase, Integer> tblQuantity;

    /**
     * Columna de sinopsis del libro
     */
    @FXML
    private TableColumn<DataBase, String> tblSinopsis;

    /**
     * Botón para volver al menú principal
     */
    @FXML
    private Button btnReturn;

    /**
     * Método para inicializar la vista
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // Función para pintar los datos de los libros
            showAllBooks(tblTitle, tblAuthor, tblDate, tblQuantity, tblSinopsis, tbl);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para cerrar la ventana y volver al menú principal
     */
    public void closeWindows(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dashboard/main_menu.fxml"));
            Parent root = loader.load();
            MainMenu controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e -> controller.closeWindows());
            Stage myStage = (Stage) this.btnReturn.getScene().getWindow();
            myStage.close();
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
