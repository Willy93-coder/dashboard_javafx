package Dashboard.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    @FXML
    private Button btnReturn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void InsertBook(){

    }
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
