package com.example.opencv.util;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static com.example.opencv.util.Constant.SNAPSHOT;
import static com.example.opencv.util.Constant.TARGET_DIR;

public class PictureEvent {

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

    public static Point findImage(String fileName) {
        Mat src = getMatSnapshot();
        Mat target = getMatFromFile(TARGET_DIR + fileName);
        Pattern pattern = matchTemplate(src, target);

        if (pattern.getSimilar() > 0.95) {
            return pattern.getPoint();
        } else {
            return null;
        }

    }

    private static Pattern matchTemplate(Mat src, Mat temp) {
        int match_method = Imgproc.TM_SQDIFF_NORMED;

        Mat result = new Mat(src.rows(), src.cols(), CvType.CV_32FC1);
        Imgproc.matchTemplate(src, temp, result, match_method);
        Core.MinMaxLocResult mmr = Core.minMaxLoc(result);

        return new Pattern(mmr.minVal, mmr.minLoc);
    }

    public static void getSnapshot() {
        try {
            Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
            int width = (int) d.getWidth();
            int height = (int) d.getHeight();
            Robot robot = new Robot();
            Rectangle rectangle = new Rectangle(0, 0, width, height);
            BufferedImage screenCapture = robot.createScreenCapture(rectangle);

            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            Graphics graphics = image.createGraphics();
            graphics.drawImage(screenCapture, 0, 0, width, height, null);

            writeImage(image, SNAPSHOT, "PNG");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeImage(BufferedImage image, String fileLocation, String extension) {
        try {
            File output = new File(fileLocation);
            ImageIO.write(image, extension, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Mat getMatFromFile(String filePath) {
        return Imgcodecs.imread(filePath);
    }

    public static Mat getMatSnapshot() {
        getSnapshot();
        return getMatFromFile(SNAPSHOT);
    }

    public static class Pattern {

        double similar;

        public Pattern(double similar, Point point) {
            this.similar = similar;
            this.point = point;
        }

        Point point;

        public Point getPoint() {
            return point;
        }
        public double getSimilar() {
            return 1.0 - similar;
        }
    }

}
