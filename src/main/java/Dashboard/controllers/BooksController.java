package Dashboard.controllers;

import Dashboard.utils.DataBase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static Dashboard.utils.DataBase.showAllBooks;

public class BooksController implements Initializable {
    @FXML
    private TableView tbl;
    @FXML
    private TableColumn<DataBase, String> tblTitle;
    @FXML
    private TableColumn<DataBase, String> tblAuthor;
    @FXML
    private TableColumn<DataBase, Date> tblDate;
    @FXML
    private TableColumn<DataBase, Integer> tblQuantity;
    @FXML
    private TableColumn<DataBase, String> tblSinopsis;
    @FXML
    private Button btnReturn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            showAllBooks(tblTitle, tblAuthor, tblDate, tblQuantity, tblSinopsis, tbl);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeWindows(){
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
