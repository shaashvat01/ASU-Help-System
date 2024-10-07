module com.example._360helpsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example._360helpsystem to javafx.fxml;
    exports com.example._360helpsystem;
}