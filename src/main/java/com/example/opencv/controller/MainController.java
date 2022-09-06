package com.example.opencv.controller;

import com.example.opencv.util.MouseEvent;
import com.example.opencv.util.PictureEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import nu.pattern.OpenCV;
import org.opencv.core.Point;

import static com.example.opencv.util.Constant.TARGET_DIR;


public class MainController {
    @FXML
    private Label btnClick;

    @FXML
    protected void onRun() {
        btnClick.setText("click run btn");
    }

    @FXML
    void onConfig() {
        btnClick.setText("click config");
    }

    @FXML
    void onSnapShot() throws Exception {
        OpenCV.loadLocally();
        long before = System.currentTimeMillis();

        Point point = PictureEvent.findImage("wantToFind.PNG");

        System.out.println("point = " + point);

        MouseEvent.move(point);

        long after = System.currentTimeMillis();
        long mills = after - before;
        System.out.println("mills = " +mills);


    }

}