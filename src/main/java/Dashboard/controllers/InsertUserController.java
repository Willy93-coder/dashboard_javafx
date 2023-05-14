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
import java.util.ResourceBundle;

import static Dashboard.utils.DataBase.insertUser;
import static Dashboard.utils.IO.writeUser;

public class InsertUserController implements Initializable {

    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPassword;
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnReturn;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void register() throws SQLException {
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        if (email.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Los campos no pueden estar vacios");
            alert.showAndWait();
        } else if (!email.matches("[a-zA-Z]+@[a-z]+\\.[a-z]{2,}")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Email no valido");
            alert.showAndWait();
        } else {
            writeUser(email,password);
            insertUser(email, password);
            txtEmail.setText("");
            txtPassword.setText("");
        }
    }

    public void closeWindows() {
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
