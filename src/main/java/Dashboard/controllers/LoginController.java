package Dashboard.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import static Dashboard.utils.DataBase.getData;

public class LoginController implements Initializable {
    @FXML
    private TextField userInput;

    @FXML
    private TextField passwordInput;

    @FXML
    private Label registerLink;

    @FXML
    private Button loginButton;

    @Override
    public void initialize(URL url, ResourceBundle event) {

    }

    @FXML
    private void loginMethod(ActionEvent event) {

        String userEmail = userInput.getText();
        String userPassword = passwordInput.getText();
        String query = "SELECT * FROM lib_user WHERE user_email='" + userEmail + "' and password='" + userPassword + "'";
        try {
            ResultSet rs = getData(query);
            if (rs.next()) {
                // Usuario autenticado, realizar acción correspondiente
            } else {
                // Mostrar mensaje de error si los credenciales son incorrectos
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("El correo electrónico o la contraseña son incorrectos");
                alert.showAndWait();
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

    public void goRegisterPage(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterView.fxml"));
        Parent root = loader.load();

        // Crear una nueva escena
        Scene scene = new Scene(root);

        // Crear una nueva ventana (stage)
        Stage stage = new Stage();
        stage.setTitle("Register Page");
        stage.setScene(scene);

        // Mostrar la nueva ventana
        stage.show();

    }
}