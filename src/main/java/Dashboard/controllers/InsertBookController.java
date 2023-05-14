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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Dashboard.utils.DataBase.insertBook;
import static Dashboard.utils.IO.writeDataBook;

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
    private void insertFormBook() throws SQLException, ParseException {
        String title  = txtTitle.getText();
        String author  = txtAuthor.getText();
        String sinopsis  = txtSinopsis.getText();
        String date  = txtDate.getText();
        String quantity  = txtQuantity.getText();
        Pattern patron = Pattern.compile("\\d+");
        Matcher matcher = patron.matcher(quantity);
        
        if (title.isEmpty() || author.isEmpty() || sinopsis.isEmpty() || date.isEmpty() || quantity.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Los campos no pueden estar vacios");
            alert.showAndWait();
        } else if (!date.matches("^(0?[1-9]|[1-2][0-9]|3[0-1])\\/(0?[1-9]|1[0-2])\\/\\d{4}$")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("La fecha no es correcta.");
            alert.showAndWait();
        } else if (!matcher.matches()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Solo se admiten números enteros");
            alert.showAndWait();
        } else {
            writeDataBook(title,author,sinopsis,date,quantity);
            insertBook(title, author, sinopsis, date, quantity);
            txtTitle.setText("");
            txtAuthor.setText("");
            txtSinopsis.setText("");
            txtDate.setText("");
            txtQuantity.setText("");
        }
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
