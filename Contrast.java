import java.awt.image.BufferedImage;

import java.awt.image.RescaleOp;
//import for images
import java.io.File;
//file extension library
import java.io.IOException;
//java exceptions
import javax.imageio.ImageIO;
//image imports
import javax.swing.*;

public class Contrast {
    static public void main(String[] args) {
        try {
            BufferedImage image;

            File input = new File("/Users/elliestroke/Desktop/algorithm analysis/JuniorIS/happy-cottage.jpeg");
            image = ImageIO.read(input);

            //This is the rescale operation, which applies an effect to all pixels.
            // This could have been done also for GreyScale, but I wanted to manipulate the pixels directly.
            //By changing the scaleFactor closer to 0, we get less brightness/less contrast,
            //and further from 0, we get more contrast. I am choosing to go with darker
            RescaleOp rop = new RescaleOp(.6f, 0, null);
            rop.filter(image, image);

            File output = new File("/Users/elliestroke/Desktop/algorithm analysis/JuniorIS/contrast.jpeg");
            ImageIO.write(image, "jpeg", output);
            BufferedImage newimage;

            newimage = ImageIO.read(output);

            ImageIcon imageIcon = new ImageIcon(newimage);
            JFrame jFrame = new JFrame();

            //setting the size of the image frame, creating an instance
            jFrame.setSize(500, 500);
            JLabel jLabel = new JLabel("Test Image");

            //setting up where the image will be displayed
            jLabel.setIcon(imageIcon);
            jFrame.add(jLabel);
            jFrame.setVisible(true);
   
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }catch (IOException e) {}
    }
}
