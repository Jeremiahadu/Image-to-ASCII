import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Color;
import java.io.File;

public class Image {

    public static void main(String[] args) {
        try {
            BufferedImage image = ImageIO.read(new File("C:\\Users\\jerem\\IdeaProjects\\Image to Ascii\\src\\cowboy bepop...jpg"));

            int newWidth = 800;
            int newHeight = (int) Math.round((double)newWidth/image.getWidth() * image.getHeight());

            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            resizedImage.getGraphics().drawImage(image.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH),0,0, null);

            convertImageToAscii(resizedImage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void convertImageToAscii(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        char[] asciiChars = {
                ' ', '.', ',', '-', '_', ':', ';', '~', '^', '`',
                '\'', '\"', '(', ')', '[', ']', '{', '}', '<', '>',
                '|', '\\', '!', '?', '*', '+', '=', '%', '&',
                '$', '#', '@', '0', '1', '2', '3', '4', '5', '6',
                '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
                'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
                'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', };

        for(int col = 0; col < height; col++) {
            for(int row = 0; row < width; row++) {
                int pixel = image.getRGB(row, col);
                int grayscale = getGrayscale(pixel);

                int index = (int) Math.round((double)(grayscale / 255.0) * (asciiChars.length - 1));
                System.out.print(asciiChars[index]);
            }
            System.out.println();
        }

    }

    private static int getGrayscale(int pixel) {
        Color color = new Color(pixel);
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        return (int) (0.299 * red + 0.587 * green + 0.114 * blue);

    }

}