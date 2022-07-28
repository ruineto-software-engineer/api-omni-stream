import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickerFactory {
  public void createSticker() throws Exception {
    // 1. read the image
    InputStream inputStream = new FileInputStream(new File("./input/movie.jpg"));
    BufferedImage originalImage = ImageIO.read(new File("./input/movie.jpg"));

    // 2. create a new image in memory with transparency and with a new size
    int originalWidth = originalImage.getWidth();
    int originalHeight = originalImage.getHeight();
    int newHeight = originalHeight + 200;
    BufferedImage newImage = new BufferedImage(originalWidth, newHeight, BufferedImage.TRANSLUCENT);

    // 3. copy original image to memory image
    Graphics2D graphics = (Graphics2D) newImage.getGraphics();
    graphics.drawImage(originalImage, 0, 0, null);

    // 4. Configure the font
    var font = new Font(Font.SANS_SERIF, Font.BOLD, 80);
    graphics.setFont(font);

    // 5. Write a sentence on the new image
    // 5.1 Calculation to center text
    var stickerText = "AMAZING MOVIE";
    var widthCenter = originalWidth / 2;
    var textCenter = stickerText.length() / 2;
    var centerDiff = widthCenter - textCenter;

    graphics.setColor(Color.ORANGE);
    graphics.drawString(stickerText, centerDiff / 2 - textCenter * 20, newHeight - 70);

    // 6. Write (create) the new image to a file
    ImageIO.write(newImage, "png", new File("./output/movieSticker.png"));
  }

  public static void main(String[] args) throws Exception {
    var generator = new StickerFactory();
    generator.createSticker();
  }
}
