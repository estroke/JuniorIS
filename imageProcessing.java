
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Image;
//import java.awt.List;
//color storage object
import java.awt.image.BufferedImage;
//import for images
import java.io.File;
import java.io.IOException;
import javax.swing.*;
// //file extension library
// import java.io.IOException;
// //java exceptions
import javax.imageio.ImageIO;

import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.Arrays;
// //image imports
//import javax.swing.*;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import java.util.Timer;
import java.util.TimerTask;

public class imageProcessing {
        //File input = new File("/Users/elliestroke/Desktop/algorithm analysis/JuniorIS/happy-cottage.jpeg");
        
        public List<int[]> newRegion = new ArrayList<>();
        int xOffset = 21;
        //ActionListener listener;
        

    static public void main(String[] args) throws IOException {
        //File imgFile = new File("/Users/elliestroke/Desktop/IS/ISCode/wallpaper1.jpeg");
        imageProcessing newImage = new imageProcessing();
        
        //newImage.GreyScaleMethod(imgFile);
        //newGreyScale.image = 
    }

    public void ContrastMethod(imageSegmentation newInstance, BufferedImage image, float scale) throws IOException {
        
        for (int i = 0; i < newInstance.region.size(); i++) {
            //accessing each x and y value from the region list of arrays[x,y]
                int[] array = newInstance.region.get(i);
                int x = array[0];
                int y = array[1];

                int pixel = image.getRGB(x, y);
                    Color c = new Color(pixel);
                    //this is code to extract the alpha
                    //int alpha = c.getAlpha();
                    //getting r,b,g from our color object
                    int newred = (int) (c.getRed() * scale);
                    int newblue = (int) (c.getBlue() * scale);
                    int newgreen = (int) (c.getGreen() * scale);

                    newred = Math.min(255, Math.max(0, newred));
                    newgreen = Math.min(255, Math.max(0, newgreen));
                    newblue = Math.min(255, Math.max(0, newblue));

                    int rgb = new Color(newred, newgreen, newblue).getRGB();

                    image.setRGB(x,y,rgb);
        }
    }
    public void GreyScaleMethod(imageSegmentation newInstance, BufferedImage image) throws IOException {
  
                
                //int width = newInstance.image.getWidth();
                //int height = newInstance.image.getHeight();

            for (int i = 0; i < newInstance.region.size(); i++) {
            //accessing each x and y value from the region list of arrays[x,y]
                int[] array = newInstance.region.get(i);
                int x = array[0];
                int y = array[1];
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
                    //System.out.print("(" + x + "," + y + "), ");
                }

        }
    //passing an instance of the segmentation class with the region of pixels populated

    //testing class to access pixels in the imageSegmentation region array
    public void Brightness(List<int[]> region, BufferedImage image, float brightnessValue) throws IOException {

       

        for (int i = 0; i < region.size(); i++) {
            //accessing each x and y value from the region list of arrays[x,y]
                int[] array = region.get(i);
                int x = array[0];
                int y = array[1];

                int pixel = image.getRGB(x, y);
                    Color c = new Color(pixel);
                    //this is code to extract the alpha
                    //int alpha = c.getAlpha();
                    //getting r,b,g from our color object
                    int newred = (int) (c.getRed() + brightnessValue);
                    int newblue = (int) (c.getBlue() + brightnessValue);
                    int newgreen = (int) (c.getGreen() + brightnessValue);

                    newred = Math.min(255, Math.max(0, newred));
                    newgreen = Math.min(255, Math.max(0, newgreen));
                    newblue = Math.min(255, Math.max(0, newblue));

                    int rgb = new Color(newred, newgreen, newblue).getRGB();

                    image.setRGB(x,y,rgb);
                    
        }

    }
    public void MedianMethod(BufferedImage image) throws IOException {

        
        int width = image.getWidth();
        int height = image.getHeight();

        int[] reds;
        int[] blues;
        int[] greens;
        Color[] pixel;
        pixel = new Color[9];
        reds = new int[9];
        greens = new int[9];
        blues = new int[9];
        

        for (int a = 1; a < width - 1; a++) {
            for (int b = 1; b < height - 1; b++) {
                //this loop stores the RGB values of all the pixels
                // in the current 3x3 window
                pixel[0] = new Color(image.getRGB(a-1, b-1));
                pixel[1] = new Color(image.getRGB(a, b-1));
                pixel[2] = new Color(image.getRGB(a+1, b-1));
                pixel[3] = new Color(image.getRGB(a-1, b));
                pixel[4] = new Color(image.getRGB(a, b));
                pixel[5] = new Color(image.getRGB(a+1, b));
                pixel[6] = new Color(image.getRGB(a-1, b+1));
                pixel[7] = new Color(image.getRGB(a, b+1));
                pixel[8] = new Color(image.getRGB(a+1, b+1));
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
                image.setRGB(a, b, new Color(reds[4],greens[4],blues[4]).getRGB());
            }
        }
    }
    public void simpleTranslation(List<int[]> region, BufferedImage image) throws IOException {

        Random rand = new Random();
        //int width = image.getWidth();
        //int height = image.getHeight();
        //List<int[]> arrayRand = new ArrayList<>();
        int size = region.size();
        //int num = 1;
        
        
        for (int i = 0;i<5000;i++) {
            int randomNum = rand.nextInt(size - 1);
            //arrayRand.add(region.get(randomNum));
            newRegion.add(region.get(randomNum));
        }
        
        
        

    }
}

