module com.example.opencv {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires opencv;
    requires java.desktop;


    opens com.example.opencv to javafx.fxml;
    exports com.example.opencv;
    exports com.example.opencv.controller;

    opens com.example.opencv.controller to javafx.fxml;
}