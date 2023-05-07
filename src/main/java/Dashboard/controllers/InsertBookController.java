package Dashboard.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class InsertBookController implements Initializable {
    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtAuthor;
    @FXML
    private TextField txtSinopsis;
    @FXML
    private TextField txtDate;
    @FXML
    private TextField txtQuantity;
    @FXML
    private Button btnInsert;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void InsertBook(){

    }
}
