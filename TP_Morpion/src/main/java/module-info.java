module com.example.tp_morpion {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.tp_morpion to javafx.fxml;
    exports com.example.tp_morpion;
}