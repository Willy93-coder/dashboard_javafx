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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Dashboard.utils.DataBase.insertBook;

/**
 * Clase que implementa la lógica de insertar libros
 */
public class InsertBookController implements Initializable {

    /**
     * Input para insertar
     * el titulo del libro
     */
    @FXML
    private TextField txtTitle;

    /**
     * Input para insertar
     * el autor del libro
     */
    @FXML
    private TextField txtAuthor;

    /**
     * Input para insertar
     * la sinopsis del libro
     */
    @FXML
    private TextField txtSinopsis;

    /**
     * Input para insertar
     * la fecha de publicación del libro
     */
    @FXML
    private TextField txtDate;

    /**
     * Input para insertar
     * la cantidad del libro que tenemos
     */
    @FXML
    private TextField txtQuantity;

    /**
     * Botón para insertar
     * los datos del libro
     */
    @FXML
    private Button btnInsert;

    /**
     * Botón para volver
     * al menu principal
     */
    @FXML
    private Button btnReturn;

    /**
     * Método para inicializar la vista
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * Método que contiene la lógica de insertar libro
     */
    @FXML
    private void insertFormBook() throws SQLException, ParseException {
        String title  = txtTitle.getText();
        String author  = txtAuthor.getText();
        String sinopsis  = txtSinopsis.getText();
        String date  = txtDate.getText();
        String quantity  = txtQuantity.getText();
        // Patron para comprobar que es un número
        Pattern patron = Pattern.compile("\\d+");
        Matcher matcher = patron.matcher(quantity);

        // Validación si hay algún campo vacío
        if (title.isEmpty() || author.isEmpty() || sinopsis.isEmpty() || date.isEmpty() || quantity.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Los campos no pueden estar vacios");
            alert.showAndWait();
            // Validación comprobar que la fecha es correcta
        } else if (!date.matches("^(0?[1-9]|[1-2][0-9]|3[0-1])\\/(0?[1-9]|1[0-2])\\/\\d{4}$")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("La fecha no es correcta.");
            alert.showAndWait();
            // Validación para comprobar que el valor que introduce es un número
        } else if (!matcher.matches()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Solo se admiten números enteros");
            alert.showAndWait();
        } else {
            insertBook(title, author, sinopsis, date, quantity);
            txtTitle.setText("");
            txtAuthor.setText("");
            txtSinopsis.setText("");
            txtDate.setText("");
            txtQuantity.setText("");
        }
    }

    /**
     * Método para cerrar la ventana y volver al menú principal
     */
    public void closeWindows() throws IOException {
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
