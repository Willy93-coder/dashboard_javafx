package Dashboard.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

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
    public void initialize(URL url, ResourceBundle event){

    }
    @FXML
    private void loginMethod(ActionEvent event) {
        String userEmail = userInput.getText();
        String userPassword = passwordInput.getText();

        try {

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}