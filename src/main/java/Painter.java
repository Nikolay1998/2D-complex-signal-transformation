import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Painter {

    public void draw(double[][] values, String pathname) {
        int n = values[0].length;
        int x = n;
        int y = n;
        BufferedImage image = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        Double max = getMax(values);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //Color color = new Color(255, 255, 255);
                int color;
                if(values[i][j] < 0){
                    color = getIntFromColor((int) (0), (int) 0, (int) (255* Math.abs(values[i][j]/max)));
                }else {
                    color = getIntFromColor((int) (255 * Math.abs(values[i][j]/max)), (int) (0), (int) (0));
                }
                image.setRGB(i, j, color);
            }
        }
        try {
            ImageIO.write(image, "jpg", new File(pathname+".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Double getMax(double[][] values) {
        int n = values[0].length;
        Double max = 0.0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (values[i][j] >= max){
                    max = values[i][j];
                }
            }
        }
        return max;
    }

    public int getIntFromColor(int Red, int Green, int Blue) {
        Red = (Red << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
        Green = (Green << 8) & 0x0000FF00; //Shift Green 8-bits and mask out other stuff
        Blue = Blue & 0x000000FF; //Mask out anything not blue.

        return 0xFF000000 | Red | Green | Blue; //0xFF000000 for 100% Alpha. Bitwise OR everything together.
    }
}
