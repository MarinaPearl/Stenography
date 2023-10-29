import java.util.Scanner;

public class MyClass {


    public static void main(String[] args) {
        try {
            System.out.println("Enter line");
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            LSB lsb = new LSB(line);
            String message = lsb.getBits();
            System.out.println("message in bits: "+ message);

            FileByte fileByte = new FileByte("panda.jpg");
            int[][][] fileBytes = fileByte.fileToByte();
            int[][][] encrypted = lsb.algoritmLsb(fileBytes);
            RgbArrayToImage rgbArrayToImage = new RgbArrayToImage(encrypted);
            rgbArrayToImage.write();


            Decoder decoder = new Decoder("output.png", message.length());
            String answer = decoder.decrypt();
            System.out.println(answer);
            in.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


}