package laba1;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Laba1 {

    static BufferedImage img;
    static int n;
    public static int current;
    private static int nx;
    private static int ny;
    static long before, after;

    public static void main(String[] args) {
        try {
            n = Integer.parseInt(args[1]);
            before = System.currentTimeMillis();
            readImage(args[2]);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void readImage(String file) {
        try {
            img = ImageIO.read(new File(file));
            int w = img.getWidth(null);
            int h = img.getHeight(null);

            MyThread thr[] = new MyThread[n];
            if (w < thr.length) {
                nx = 1;
                ny = (thr.length - w) / h;
            } else {
                nx = w / thr.length;
                ny = h;
            }
            for (current = 0; current < thr.length; current++) {
                thr[current] = new MyThread(current, nx, ny);
                thr[current].start();
            }

            for (current = 0; current < thr.length; current++) {
                thr[current].join();
            }

            String ext = getFileExtention(file);
            String newFile = file.substring(0, file.length() - ext.length() - 1) + "_gray" + "." + ext;
            ImageIO.write(img, "jpg", new File(newFile));

            after = System.currentTimeMillis();
            long diff = after - before;
            System.out.println("THREADS " + n);
            System.out.println("TIME " + diff);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String getFileExtention(String filename) {
        int dotPos = filename.lastIndexOf(".") + 1;
        return filename.substring(dotPos);
    }
}
