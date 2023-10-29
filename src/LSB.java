public class LSB {
    private String inputLine;
    private String bits;

    public LSB(String inputLine) {
        this.inputLine = inputLine;
    }

    public String getBits() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            byte[] bytes = inputLine.getBytes("UTF-8");
            for (byte by : bytes) {
                String bits = Integer.toString(by, 2);
                String insignificantBytes = "0".repeat(Math.max(0, 8 - bits.length())) +
                        bits;
                stringBuilder.append(insignificantBytes);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        bits = stringBuilder.toString();
        return bits;
    }

    private String replaceCharAtIndex(String str, int index, char replacement) {
        char[] charArray = str.toCharArray();
        charArray[index - 1] = replacement;
        return new String(charArray);
    }

    public int[][][] algoritmLsb(int[][][] pixelImage) {
        for (int i = 0; i < bits.length(); i++) {
            String color = Integer.toBinaryString(pixelImage[0][i][2]);
            String modifiedString = replaceCharAtIndex(color, color.length(), bits.charAt(i));
            pixelImage[0][i][2] = Integer.parseInt(modifiedString, 2);
        }
        return pixelImage;
    }


}
