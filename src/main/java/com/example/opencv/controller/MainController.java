package com.example.opencv.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import nu.pattern.OpenCV;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


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

        // screenshot
        getSnapshot();

    }

    private void getSnapshot() {
        try {
            Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
            int width = (int) d.getWidth();
            int height = (int) d.getHeight();

            Robot robot = new Robot();
            Rectangle rectangle = new Rectangle(0, 0, width, height);

            BufferedImage screenCapture = robot.createScreenCapture(rectangle);

            String fileName = "snap" + System.currentTimeMillis() + ".png";

            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            Graphics graphics = image.createGraphics();
            graphics.drawImage(screenCapture, 0, 0, width, height, null);

            writeImage(image, fileName, "PNG");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeImage(BufferedImage image, String fileLocation, String extension) {
        try {
            BufferedImage bufferedImage = image;
            File output = new File(fileLocation);
            ImageIO.write(bufferedImage, extension, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}