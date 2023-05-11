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
import static Dashboard.utils.DataBase.rentBooksTable;

// import static Dashboard.utils.DataBase.rentBooksTable;

public class RentController implements Initializable {
    @FXML
    private TableView tblRent;
    @FXML
    private TableColumn<DataBase, String> tblTitle;
    @FXML
    private TableColumn<DataBase, Date> tblDateRent;
    @FXML
    private TableColumn<DataBase, Date> tblDateReturn;
    @FXML
    private TableColumn<DataBase, String> tblUser;
    @FXML
    private Button btnReturn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            rentBooksTable(tblTitle, tblDateRent, tblDateReturn, tblUser, tblRent);
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
