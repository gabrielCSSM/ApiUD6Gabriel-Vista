module gabrielud6.vista {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;
    requires com.google.gson;


    opens gabrielud6.vista to javafx.fxml, com.google.gson;
    exports gabrielud6.vista;
}