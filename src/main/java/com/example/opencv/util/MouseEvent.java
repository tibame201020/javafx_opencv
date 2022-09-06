package com.example.opencv.util;

import org.opencv.core.Point;

import java.awt.*;
import java.awt.event.InputEvent;

public class MouseEvent {

    private static final Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void move (Point point) {
        if (null == point) {
            return;
        }

        robot.mouseMove((int) point.x, (int) point.y);
    }

    public static void click(Point point) {
        if (null == point) {
            return;
        }

        try {
            robot.mouseMove((int) point.x, (int) point.y);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void drag(int originX, int originY, int gotoX, int gotoY) {
        try {
            robot.mouseMove(originX, originY);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseMove(gotoX, gotoY);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doubleClick(Point point) {
        if (null == point) {
            return;
        }
        try {
            robot.mouseMove((int) point.x, (int) point.y);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
