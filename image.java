import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class image {

    public BufferedImage image;

    public void populateImage(File file) throws IOException {
        image = ImageIO.read(file);
    }
}
