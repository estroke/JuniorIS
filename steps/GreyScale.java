package steps;
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
import javax.swing.*;

public class GreyScale {

    static public void main(String[] args) {
        try {
        BufferedImage image;
        int width;
        int height;
        //input i = new input();
                    //reads in the image from the file extension
                File input = new File("/Users/elliestroke/Desktop/algorithm analysis/JuniorIS/happy-cottage.jpeg");
                image = ImageIO.read(input);
            // getting the pixels and putting them into a color object (an imported library)
            width = image.getWidth();
            height = image.getHeight();

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    
                    //This starts us at pixel (0,0) then so on for 
                    // the duration of the length and width
                    int pixel = image.getRGB(x, y);
                    Color c = new Color(pixel);
                    //this is code to extract the alpha
                    int alpha = c.getAlpha();
                    //getting r,b,g from our color object
                    int red = c.getRed();
                    int blue = c.getBlue();
                    int green = c.getGreen();
                    //dividing the combined red,blue,and green values by 3 to get
                    //the average
                    int average = (red + blue + green) / 3;

                    // this is code to create each pixel from source
                    // https://dyclassroom.com/image-processing-project/how-to-convert-a-color-image-into-grayscale-image-in-java
                    pixel = (alpha<<24) | (average<<16) | (average<<8) | average;
                    
                    //setting each pixel to its new value
                    image.setRGB(x, y, pixel);
                }
            }


        File output = new File("/Users/elliestroke/Desktop/algorithm analysis/JuniorIS/grey.jpeg");
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

    } catch (IOException e) {}

}
}
