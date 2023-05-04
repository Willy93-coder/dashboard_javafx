package Dashboard.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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