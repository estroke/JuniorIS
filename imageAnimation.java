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
            imageSegmentation.regionalGrowth(img.image, 1000, 100,50);
            //imageProcessing.Brightness(imageSegmentation.region, img.image, 100);
            
            
        } catch (IOException e) {
            
        }

        width = img.image.getWidth();
        height = img.image.getHeight();
        

        //adding the image effects after a certain delay. seamless insertion into img.image, allows for the image to
        //keep moving even after the image changes
        final Timer changeImageTimer = new Timer();
        changeImageTimer.scheduleAtFixedRate(new TimerTask() {

            float x = 1;
            
            public void run() {
                
                try {
                        imageProcessing.Brightness(imageSegmentation.region, img.image, x);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println("I'm here with imageChangeTimer");
                    x = x + 5;
            }
        }, 5000L, 8000L);

        //the L is for long so 2000L means 2 seconds

        final Timer animationTimer = new Timer();
        animationTimer.scheduleAtFixedRate(new TimerTask() {            
            
            int a,b = 0;

            public void run() {
                

                part = img.image.getSubimage(a,b, width - a, height - b);
                
                ImageIcon newimg = new ImageIcon(part);
                jLabel.setIcon(newimg);
                    
                a = a + 2; 
                System.out.println("I'm here with animationTimer");
            }
        }, 1000L, 100L);
    }

}
