package Dashboard.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenu implements Initializable {

    @FXML
    private Button btnBooks;
    @FXML
    private Button btnUser;
    @FXML
    private Button btnAddBooks;
    @FXML
    private Button btnRentBooks;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    @FXML
    private void showBooks(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dashboard/Books.fxml"));
            Parent root = loader.load();
            BooksController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e -> {controller.closeWindows();});
            Stage myStage = (Stage) this.btnUser.getScene().getWindow();
            myStage.close();

        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
    @FXML
    private void insertUser(){
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dashboard/InsertUser.fxml"));
        Parent root = loader.load();
        InsertUserController controller = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> {controller.closeWindows();});
        Stage myStage = (Stage) this.btnUser.getScene().getWindow();
        myStage.close();

    }catch (IOException e){
        System.err.println(e.getMessage());
    }
    }
    @FXML
    private void insertBook() throws IOException {
    try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dashboard/InsertBook.fxml"));
        Parent root = loader.load();
        InsertBookController controller = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> {
            try {
                controller.closeWindows();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        Stage myStage = (Stage) this.btnAddBooks.getScene().getWindow();
        myStage.close();

        }catch (IOException e){
        System.err.println(e.getMessage());
    }
    }
    @FXML
    private void rentBooks(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dashboard/Rent.fxml"));
            Parent root = loader.load();
            RentController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e -> {
                controller.closeWindows();
            });
            Stage myStage = (Stage) this.btnAddBooks.getScene().getWindow();
            myStage.close();

        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public void closeWindows() {
    }
}
