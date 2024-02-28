module gabrielud6.vista {
    requires javafx.controls;
    requires javafx.fxml;


    opens gabrielud6.vista to javafx.fxml;
    exports gabrielud6.vista;
}