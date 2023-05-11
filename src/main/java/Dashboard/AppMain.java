package Dashboard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static Dashboard.utils.DataBase.closeBD;
import static Dashboard.utils.DataBase.createDB;

public class AppMain extends Application {
    public static void main(String[] args) throws SQLException {
        launch();
        createDB();
    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppMain.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.sizeToScene();
        stage.setTitle("Library");
        stage.setScene(scene);
        stage.show();
    }


}