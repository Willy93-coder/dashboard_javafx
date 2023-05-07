package Dashboard.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import static Dashboard.utils.DataBase.getData;

public class LoginController implements Initializable {
    @FXML
    private Pane root;

    @FXML
    private TextField userInput;

    @FXML
    private TextField passwordInput;

    @FXML
    private Button loginButton;

    @Override
    public void initialize(URL url, ResourceBundle event){

    }
    @FXML
    private void loginMethod(ActionEvent event) {
        String userEmail = userInput.getText();
        String userPassword = passwordInput.getText();
        String query = "SELECT * FROM lib_user WHERE user_email='"+userEmail+"' and password='"+userPassword+"'";
        try {
            ResultSet rs = getData(query);
            if (rs.next()) {
                Object JOptionPane;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}