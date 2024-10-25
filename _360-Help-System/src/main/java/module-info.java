module com.example._360helpsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example._360helpsystem to javafx.fxml;
    exports com.example._360helpsystem;
}