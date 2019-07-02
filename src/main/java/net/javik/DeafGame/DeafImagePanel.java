package net.javik.DeafGame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DeafImagePanel extends JPanel{
    private BufferedImage scaledImage;

    public DeafImagePanel(String file, int height, int width) {
        try {
            BufferedImage image = ImageIO.read(new File(file));
            Image scaleImage = image.getScaledInstance(width,height, Image.SCALE_SMOOTH);

            this.scaledImage = new BufferedImage(scaleImage.getWidth(this),
                                                 scaleImage.getHeight(this),
                                                 BufferedImage.TYPE_INT_RGB);
            this.scaledImage.getGraphics().drawImage(scaleImage, 0, 0, null);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(scaledImage, 0, 0, this);
    }
}
