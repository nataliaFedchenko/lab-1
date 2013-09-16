package laba1;

public class MyThread extends Thread {

    int current;
    int nx;
    int ny;

    public MyThread(int current, int nx, int ny) {
        this.current = current;
        this.nx = nx;
        this.ny = ny;
    }

    @Override
    public void run() {
        if (ny < Laba1.img.getHeight(null)) {
            imageProcessing(current * nx, (current + 1) * nx, current * ny, (current + 1) * ny);
        } else {
            imageProcessing(current * nx, (current + 1) * nx, 0, ny);
        }
    }

    public void imageProcessing(int x_start, int x_end, int y_start, int y_end) {
        try {
            for (int x = x_start; x < x_end; x++) {
                for (int y = y_start; y < y_end; y++) {
                    int v = Laba1.img.getRGB(x, y);
                    int red = (v & 0xff0000) >> 16;
                    int green = (v & 0xff00) >> 8;
                    int blue = v & 0xff;

                    red *= 0.2126;
                    green *= 0.7125;
                    blue *= 0.0722;
                    v = red + green + blue;
                    if (v > 225) {
                        v = 225;
                    } else if (v < 0) {
                        v = 0;
                    }
                    v = (v << 16) + (v << 8) + v;
                    Laba1.img.setRGB(x, y, v);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
