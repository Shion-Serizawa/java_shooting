import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class Enemy {
    private int x;
    private int y;
    String pathname;

    public void drawPlayer(Graphics2D g2) {
        // いけます！！！
        try {
            BufferedImage image = ImageIO.read(
                    new File(this.pathname));
            g2.drawImage(image, (int) x, (int) y, 31, 50, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
