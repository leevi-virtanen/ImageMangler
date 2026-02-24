module com.example.imagemangler {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens com.example.imagemangler to javafx.fxml;
    exports com.example.imagemangler;
}