import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class imageSegmentation {

    
    public List<int[]> region = new ArrayList<>();
    

    static public void main(String[] args) throws IOException {
        //create new instance of class here and add file call. can call to image animation class to test

        imageSegmentation newSegmentation = new imageSegmentation();
        //showing regional growth
        //File imgFile1 = new File("/Users/elliestroke/Desktop/puppy.jpeg");
        //newSegmentation.threshold(imgFile1, 70);
        //newSegmentation.regionalGrowth(imgFile1, 1000, 1000, 70);
        //File outputImageFile = new File("/Users/elliestroke/Desktop/regional_puppy70.jpeg");
        //ImageIO.write(newSegmentation.image, "jpeg", outputImageFile);
        
        
        
        
    }
    
    public void regionalGrowth(BufferedImage image, int xPixel, int yPixel, int threshold) throws IOException {

        //different x,y thresholds:
        //100,1,10 selects clouds good accuracy
        

        Stack<int[]> stack = new Stack<>();

        int width = image.getWidth();
        int height = image.getHeight();

        boolean[][] visited = new boolean[width][height];

        
        int seedPixel = image.getRGB(xPixel, yPixel);
        Color c1 = new Color(seedPixel);
        int red1 = c1.getRed();
        int green1 = c1.getGreen();
        int blue1 = c1.getBlue();
        //int grey1 = (red1 + blue1 + green1) / 3;
        
        
        
        // JFrame jFrame = new JFrame();
        // jFrame.setSize(700, 700);
        // JLabel jLabel = new JLabel("Test Image");

        stack.push(new int [] {xPixel,yPixel});

        while (!stack.isEmpty()) {
        // for (int y = 0; y < height; y++) {
        //     for (int x = 0; x < width; x++) {
            int[] firstArray = stack.pop();
            int x = firstArray[0];
            int y = firstArray[1];

            if (x >= 0 && x < image.getWidth() && y >= 0 && y < image.getHeight() && !visited[x][y]) {
                int pixel = image.getRGB(x,y);
                Color c = new Color(pixel);
                int red = c.getRed();
                int green = c.getGreen();
                int blue = c.getBlue();

                //checking if the red, blue and green values are near the threshold value (precision)
                if (Math.abs((red) - (red1)) < threshold && Math.abs((green) - (green1)) < threshold && Math.abs((blue) - (blue1)) < threshold) {
                    visited[x][y] = true;
                    //int rgb = new Color(255,0,0).getRGB();
                    
                    stack.push(new int [] {x + 1, y});
                    stack.push(new int [] {x - 1, y});
                    stack.push(new int [] {x, y + 1});
                    stack.push(new int [] {x, y - 1});
                    stack.push(new int [] {x + 1, y + 1});
                    stack.push(new int [] {x - 1, y - 1});
                    stack.push(new int [] {x + 1, y - 1});
                    stack.push(new int [] {x - 1, y + 1});

                    region.add(new int [] {x,y});
                    //image.setRGB(x, y, rgb);
                    // ImageIcon imageIcon = new ImageIcon(image);  
                    // Image image = imageIcon.getImage(); // transform it 
                    
                    // Image newimg = image.getScaledInstance((image.getWidth(jFrame)*2), (image.getHeight(jFrame)*2),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way                 
                    // jFrame.add(jLabel);
                    // jFrame.setVisible(true);
                    // jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    // imageIcon = new ImageIcon(newimg);
                    // jLabel.setIcon(imageIcon);
                    
                    //displaying the growing algorithm

                }
            }
            
        }
        

    }
    public void threshold(BufferedImage image, int threshold) throws IOException {
        
             
        int width = image.getWidth();
        int height = image.getHeight();

        //display
        // JFrame jFrame = new JFrame();
        //             jFrame.setSize(700, 700);
        //             JLabel jLabel = new JLabel("Test Image");

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x,y);
                Color c = new Color(pixel);
                int red = c.getRed();
                int green = c.getGreen();
                int blue = c.getBlue();
                int grey = (red + blue + green) / 3;
                if ( grey >= threshold) {
                    //int rgb = new Color(255,0,0).getRGB();
                    //image.setRGB(x, y, rgb);
                    region.add(new int [] {x,y});

                }
            }
        }
                    //display
                    // ImageIcon imageIcon = new ImageIcon(image);  
                    // Image image = imageIcon.getImage(); // transform it 
                    
                    // Image newimg = image.getScaledInstance((image.getWidth(jFrame)*2), (image.getHeight(jFrame)*2),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way                 
                    // jFrame.add(jLabel);
                    // jFrame.setVisible(true);
                    // jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    // imageIcon = new ImageIcon(newimg);
                    // jLabel.setIcon(imageIcon);
    }
    
}
