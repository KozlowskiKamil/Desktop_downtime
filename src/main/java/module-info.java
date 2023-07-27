module com.example.desktop_downtime {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.desktop_downtime to javafx.fxml;
    exports com.example.desktop_downtime;
}