//import for the timer object and the timer task,
//which sets the delay
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
//import javax.imageio.ImageIO;
//imports for swing utilities such as the jFrame,jPanel
import javax.swing.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
//aid to the timer object, allows for signal to be sent
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
//IO exception class
import java.io.IOException;

//public class constructor
public class imageAnimation{

        JLabel jLabel;
        JFrame jFrame;
        JPanel jPanel;
        ImageIcon images;
        Timer timer; 
        Timer moveTimer;
        JButton jButton;
        ActionListener listener;
        File imgFile1 = new File("/Users/elliestroke/Desktop/IS/is_possible_2.jpg");
        int width;
        int height;
        BufferedImage part;
        BufferedImage scaledImage;

    

        


    static public void main(String[] args) throws IOException, InterruptedException {
        //calls an instnce of the main function
        imageAnimation ia = new imageAnimation();

    }
    
    public imageAnimation() throws InterruptedException {
        //function to set up the JFrame, JLabel, and JPanel
        //which will display the animation
        jLabel = new JLabel();
        jFrame = new JFrame("Scary Cottage");
        jPanel = new JPanel(null, false);

        // //setting frame location
        // jPanel.setLayout(new GridBagLayout());
        jFrame.setLocation(200, 100);
        // jPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        //jPanel.add(jLabel);
        jFrame.setSize(1000, 1200);
        jFrame.add(jLabel);
 
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //calls an instance of the animation class which 
        //gives the information to the jLabel
        animation(imgFile1);
    
    }
    public void animation(File file) throws InterruptedException{

        //creation of instances of classes
        imageProcessing imageProcessing = new imageProcessing();
        imageSegmentation imageSegmentation = new imageSegmentation();
        image img = new image();

        //populating the image class by reading from the image file and using the regional growth method
        //one time for entire image
        try {
            img.populateImage(file);
            
            imageSegmentation.regionalGrowth(img.image, 3000, 100,30);
            
            
        } catch (IOException e) {
            
        }


        AffineTransform at = new AffineTransform();
        at.scale(.3,.3);
        AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        

        // delayed timer function is from https://stackoverflow.com/questions/5525176/java-simple-timertask-for-each-value-of-array

        //
        // final Timer changeImageTimer = new Timer();
        // changeImageTimer.scheduleAtFixedRate(new TimerTask() {


            
        //     public void run() {

        //     }
        // }, width, height);


        final Timer animationTimer = new Timer();
        animationTimer.scheduleAtFixedRate(new TimerTask() {
            
            float x = .5f;
            int a,b = 0;
            //int i = 1;

            
            public void run() {
                
                
                    //  try {
                    //      //getting sky pixels
                    //      //populating both public image and region array with arrays of pixels
                    //      newImage.threshold(imgFile1, 200);
                    //      newImage1.Brightness(newImage, i);
                    // } catch (IOException e) {
                    // }
                    
                    //i = i - 2;
                    
                    // try {
                    try {
                        imageProcessing.Brightness(imageSegmentation, img.image, x);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    scaledImage = scaleOp.filter(img.image, scaledImage);
                    width = scaledImage.getWidth();
                    height = scaledImage.getHeight();
                    //     newImage1.Brightness(newImage,i);
                    //     //can we move this up and use the whole image at once?
                        
                    // } catch (IOException e) {
                        
                    // }
                    
                    part = scaledImage.getSubimage(a,b, width - a, height - b);
                    
                    
                    // // transform it 
                    //Image image = imageIcon.getImage(); 
                    //Image newimg = part.getScaledInstance((width/4), (height/4),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                    ImageIcon img = new ImageIcon(part);
                    jLabel.setIcon(img);
                    
                    //if (a)
                    a = a +10;
                    
                    //b++;
                    
                    //i++;
                    //resets the array after the last image has been shown 
                    //for continuity
                    x = (x + .1f);
                    // if (x >= 2) {
                    //     x = .1f;
                    // }
            }
        }, 500L, 100L);
    }

}
