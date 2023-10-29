import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileByte {

    private String fileName;
    private int[][][] pixelImage;

    public FileByte(String fileName) {
        this.fileName = fileName;
    }

    public int[][][] fileToByte()  {
        BufferedImage img;

        try {
            img = ImageIO.read(new File(fileName));

            int height = img.getHeight();
            int width = img.getWidth();

            pixelImage = new int[height][width][3];

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    getPixelData(img, i, j, pixelImage);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return pixelImage;
    }

    private void getPixelData(BufferedImage img, int y, int x, int[][][] rgb) {
        int argb = img.getRGB(x, y);

        rgb[y][x][0] = (argb >> 16) & 0xff; // Red
        rgb[y][x][1] = (argb >> 8) & 0xff;  // Green
        rgb[y][x][2] = argb & 0xff;         // Blue
    }
}
