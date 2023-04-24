
// aid was taken from https://github.com/praserocking/MedianFilter/blob/master/MedianFilter.java
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Color;
import java.util.Arrays;
import java.awt.image.*;

import java.awt.image.BufferedImage;
import java.io.File;

import java.io.IOException;

public class Median {
    int[] reds;
    int[] blues;
    int[] greens;
    Color[] pixel;


    public Median(BufferedImage i) {

        
        //declaring an array of Color objects- recall from 
        //greyscale how this object stores RGB values and alpha
        pixel = new Color[9];
        reds = new int[9];
        greens = new int[9];
        blues = new int[9];
        int width = i.getWidth();
        int height = i.getHeight();

        for (int a = 1; a < width - 1; a++) {
            for (int b = 1; b < height - 1; b++) {
                //this loop stores the RGB values of all the pixels
                // in the current 3x3 window
                pixel[0] = new Color(i.getRGB(a-1, b-1));
                pixel[1] = new Color(i.getRGB(a, b-1));
                pixel[2] = new Color(i.getRGB(a+1, b-1));
                pixel[3] = new Color(i.getRGB(a-1, b));
                pixel[4] = new Color(i.getRGB(a, b));
                pixel[5] = new Color(i.getRGB(a+1, b));
                pixel[6] = new Color(i.getRGB(a-1, b+1));
                pixel[7] = new Color(i.getRGB(a, b+1));
                pixel[8] = new Color(i.getRGB(a+1, b+1));
                for (int n = 1; n < 9; n++) {
                    reds[n] = pixel[n].getRed();
                    greens[n] = pixel[n].getGreen();
                    blues[n] = pixel[n].getBlue();
                }
                //sorting the array so we can find the middle value (median)
                Arrays.sort(reds);
                Arrays.sort(greens);
                Arrays.sort(blues);

                //setting the middle pixel [4] to the median value
                i.setRGB(a, b, new Color(reds[4],greens[4],blues[4]).getRGB());
            }
        }
    }

    static public void main(String[] args) throws IOException {
        BufferedImage image;
        
        File input = new File("/Users/elliestroke/Desktop/algorithm analysis/JuniorIS/happy-cottage.jpeg");
        image = ImageIO.read(input); 
        
        Median m = new Median(image);
                
        File output = new File("/Users/elliestroke/Desktop/algorithm analysis/JuniorIS/mfiltered.jpeg");
        ImageIO.write(image, "jpeg", output);
        BufferedImage newimage;

        newimage = ImageIO.read(output);
            
        ImageIcon imageIcon = new ImageIcon(newimage);
        JFrame jFrame = new JFrame("Median Filter");

                //setting the size of the image frame, creating an instance
        jFrame.setSize(500, 500);
        JLabel jLabel = new JLabel();

                //setting up where the image will be displayed
        jLabel.setIcon(imageIcon);
        jFrame.add(jLabel);
        jFrame.setVisible(true);


                
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

}



}
