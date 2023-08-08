module com.example.desktop_downtime {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires com.fasterxml.jackson.databind;

    opens com.example.desktop_downtime to javafx.fxml;
    exports com.example.desktop_downtime;
    exports com.example.desktop_downtime.service;
    opens com.example.desktop_downtime.service to javafx.fxml;
    exports com.example.desktop_downtime.model;
    opens com.example.desktop_downtime.model to javafx.fxml;
}