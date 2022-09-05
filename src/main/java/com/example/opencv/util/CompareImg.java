package com.example.opencv.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class CompareImg {

    public static float compareImg(String img1, String img2) {
        return compare(getData(img1), getData(img2));
    }


    private static float compare(int[] s, int[] t) {
        try {
            float result = 0F;
            for (int i = 0; i < 256; i++) {
                int abs = Math.abs(s[i] - t[i]);
                int max = Math.max(s[i], t[i]);
                result += (1 - ((float) abs / (max == 0 ? 1 : max)));
            }
            return (result / 256) * 100;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static int[] getData(String name) {
        try {
            BufferedImage img = ImageIO.read(new File(name));
            BufferedImage slt = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
            slt.getGraphics().drawImage(img, 0, 0, 100, 100, null);

            int[] data = new int[256];
            for (int x = 0; x < slt.getWidth(); x++) {
                for (int y = 0; y < slt.getHeight(); y++) {
                    int rgb = slt.getRGB(x, y);
                    Color myColor = new Color(rgb);
                    int r = myColor.getRed();
                    int g = myColor.getGreen();
                    int b = myColor.getBlue();
                    data[(r + g + b) / 3]++;
                }
            }

            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
