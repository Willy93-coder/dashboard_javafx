module com.example.dashboard_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens Dashboard to javafx.fxml;
    opens Dashboard.utils to javafx.base;
    exports Dashboard;
    exports Dashboard.controllers;
    opens Dashboard.controllers to javafx.fxml;
}