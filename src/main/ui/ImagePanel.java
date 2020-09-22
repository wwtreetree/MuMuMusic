package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


//this classs display image
public class ImagePanel extends JPanel {
    private BufferedImage image;


    //read a picture
    public ImagePanel() {
        try {
            image = ImageIO.read(new File("./image/black-cat-adoption-and-other-october-facts.jpeg"));
        } catch (IOException ex) {
            // handle exception...
        }
    }

    //EFFECTS: draw image on panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters
    }

}
