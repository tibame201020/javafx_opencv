package com.example.opencv.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private Label btnClick;

    @FXML
    protected void onRun() {
        btnClick.setText("click run btn");
    }

    @FXML
    void onConfig(){
        btnClick.setText("click config");
    }
}