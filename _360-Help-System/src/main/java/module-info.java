module com.example._360helpsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;


    opens com.example._360helpsystem to javafx.fxml;
    exports com.example._360helpsystem;
}