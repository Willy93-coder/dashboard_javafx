/**
 * @author Chen, S. Folgueras y Willy
 * @version 1.0.0
 * 12/05/2023
 */
package Dashboard.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static Dashboard.utils.DataBase.*;

/**
 * Clase que contiene la lógica del login
 */
public class LoginController implements Initializable {

    /**
     * Ventana padre
     */
    @FXML
    private Pane root;

    /**
     * Input usuario
     */
    @FXML
    private TextField userInput;

    /**
     * Input contraseña
     */
    @FXML
    private TextField passwordInput;

    /**
     * Botón para hacer el login
     */
    @FXML
    private Button loginButton;

    /**
     * Método para inicializar la vista
     */
    @Override
    public void initialize(URL url, ResourceBundle event) {

    }

    /**
     * Método que contiene la lógica para hacer el login
     * @param event recibe el evento
     */
    @FXML
    private void loginMethod(ActionEvent event) throws SQLException {
        initDB();
        String userEmail = userInput.getText();
        String userPassword = passwordInput.getText();
        String query = "SELECT * FROM lib_user WHERE user_email='"+userEmail+"' and user_password='"+userPassword+"'";
        try {
            ResultSet rs = getData(query);
            if (rs.next()) {
                closeBD();
                goToMainMenu();
            } else {
                Alert alert= new Alert(Alert.AlertType.ERROR);
                alert.setContentText("La contraseña o el usuario no son correctos");
                alert.showAndWait();
                closeBD();
            }
        } catch (Exception e) {
            // Mostrar mensaje de error si se produce una excepción
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Se produjo un error durante el inicio de sesión. Por favor, inténtalo de nuevo más tarde.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    private void goToMainMenu(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dashboard/main_menu.fxml"));
            Parent root = loader.load();
            MainMenu controller = loader.getController();
            // Crear una nueva escena
            Scene scene = new Scene(root);
            // Crear una nueva ventana (stage)
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Main menu");
            // Mostrar la nueva ventana
            stage.show();
            stage.setOnCloseRequest(e -> {
                controller.closeWindows();
            });
            Stage myStage = (Stage) this.loginButton.getScene().getWindow();
            myStage.close();

        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * Método para cerrar la ventana
     */
    public void closeWindows() {
    }
}