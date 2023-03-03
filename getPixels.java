import java.awt.Color;
//color storage object
import java.awt.image.BufferedImage;
//import for images
import java.io.File;
//file extension library
import java.io.IOException;
//java exceptions
import javax.imageio.ImageIO;
//image imports

public class getPixels {
    input i = new input();
    public getPixels() {
        BufferedImage image = null;
            try {
                //reads in the image from the file extension
                image = ImageIO.read(new File("/Users/elliestroke/Desktop/algorithm analysis/JuniorIS/happy-cottage.jpeg"));
            } catch (IOException e) {
            }
        // getting the pixels and putting them into a color object (an imported library)
        int x = 0;
        int y = 0;    
        Color c = new Color(image.getRGB(x, y));

    }    
}
