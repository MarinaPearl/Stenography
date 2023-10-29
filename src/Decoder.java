import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Decoder {
    private String imagePath;
    private Integer encodedLineLength;

    public Decoder(String path, Integer length) {
        imagePath = path;
        encodedLineLength = length;
    }

    public String decrypt() {
        BufferedImage img;

        try {
            img = ImageIO.read(new File(imagePath));

            StringBuilder extractedBits = new StringBuilder();

            int height = img.getHeight();
            int width = img.getWidth();



            int[] pixelData = new int[width];
            img.getRGB(0, 0, width, 1, pixelData, 0, width);

            int[][][] rgbArray = new int[1][width][3];

            for (int x = 0; x < width; x++) {
                int pixel = pixelData[x];

                rgbArray[0][x][0] = (pixel >> 16) & 0xFF;  // Red
                rgbArray[0][x][1] = (pixel >> 8) & 0xFF;   // Green
                rgbArray[0][x][2] = pixel & 0xFF;          // Blue
            }
            for (int x = 0; x < encodedLineLength; x++) {
                int blue = rgbArray[0][x][2] & 1;
                extractedBits.append(blue);
            }
            return binaryStringToString(extractedBits.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String binaryStringToString(String binaryString) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < binaryString.length(); i += 8) {
            String byteStr = binaryString.substring(i, Math.min(i + 8, binaryString.length()));

            int charCode = Integer.parseInt(byteStr, 2);
            char character = (char) charCode;


            result.append(character);
        }

        return result.toString();
    }
}
