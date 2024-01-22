//import for the timer object and the timer task,
//which sets the delay
import java.util.Timer;
import java.util.TimerTask;

//import javax.imageio.ImageIO;
//imports for swing utilities such as the jFrame,jPanel
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
//aid to the timer object, allows for signal to be sent
import java.awt.event.ActionListener;

import java.awt.image.BufferedImage;
import java.io.File;
//IO exception class
import java.io.IOException;

//public class constructor
public class imageAnimation{

        JLabel jLabel;
        JFrame jFrame;
        JPanel jPanel;
        JPanel jPanelPnP;
        JButton jButtonPlay;
        JButton jButtonPause;
        ImageIcon images;
        Timer timer; 
        Timer moveTimer;
        ActionListener listener;
        File imgFile1 = new File("/Users/elliestroke/Desktop/IS/is_possible_2.jpg");
        int width;
        int height;
        BufferedImage part;
        boolean running;
        private static boolean movingForward = true;
        private static int newX = 5;

    

        


    static public void main(String[] args) throws IOException, InterruptedException {
        //calls an instnce of the main function
        imageAnimation ia = new imageAnimation();
        

    }
    
    public imageAnimation() throws InterruptedException {
        //function to set up the JFrame, JLabel, and JPanel
        //which will display the animation
        jLabel = new JLabel();
        jFrame = new JFrame("Test");
        jPanel = new JPanel(null, false);
        jPanelPnP = new JPanel();
        jButtonPause = new JButton("Pause");
        
        jButtonPlay = new JButton("Play");

        // //setting frame location
        // jPanel.setLayout(new GridBagLayout());
        jFrame.setLocation(200, 100);
        // jPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        //jPanel.add(jLabel);
        jFrame.setSize(1000, 1200);
        jFrame.add(jLabel);
        jPanelPnP.setBorder(BorderFactory.createTitledBorder("Playback Options"));

        jPanelPnP.add(jButtonPlay);
        jPanelPnP.add(jButtonPause);
        jPanelPnP.setSize(500,500);
        //jPanelPnP.setBackground(new Color(0, 0, 0, 0));
 
        jFrame.add(jPanelPnP, BorderLayout.SOUTH);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //calls an instance of the animation class which 
        //gives the information to the jLabel
        animation(imgFile1);
    
    }
    private static void updateGlobalVariable() {
        if (movingForward) {
            newX = newX + 2;
        } else {
            newX = newX - 2;
        }
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
            imageSegmentation.regionalGrowth(img.image, 721,241,30);
            imageProcessing.simpleTranslation(imageSegmentation.region, img.image);
            System.out.println(imageProcessing.newRegion.size());
            //imageProcessing.Brightness(imageSegmentation.region, img.image, 100);
            
            
        } catch (IOException e) {
            
        }

        width = img.image.getWidth();
        height = img.image.getHeight();
        
        final Timer translatioTimer = new Timer();
        translatioTimer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                updateGlobalVariable();
                System.out.println("Global Variable: " + newX);

                if (newX >= 20) {
                    // When the threshold for increasing is reached, start decreasing
                    movingForward = false;
                } else if (newX <= 0) {
                    // When the threshold for decreasing is reached, start increasing again
                    movingForward = true;
                }
            }
        }, 0L, 1000L);

        //adding the image effects after a certain delay. seamless insertion into img.image, allows for the image to
        //keep moving even after the image changes
        final Timer changeImageTimer = new Timer();
        changeImageTimer.scheduleAtFixedRate(new TimerTask() {

            //float x = 1;
            //int newX = 5;

            //imageProcessing.simpleTranslation(imageSegmentation.region, img.image);
            //int j = 0;
            public void run() {
                System.out.println(imageProcessing.newRegion.size());

                for (int[] coordinates:imageProcessing.newRegion) {
                    //int[] coordinates = imageProcessing.newRegion.get(j);
                    int startX = coordinates[0];
                    int startY = coordinates[1];
                    int pixel = img.image.getRGB(startX,startY);
                    System.out.println(startX);
                    //img.image.setRGB(startX,startY,255);
                    startX = startX + newX;
                    //imageProcessing.newRegion.remove(coordinates);
                    //imageProcessing.newRegion.add(new int[] {startX,startY});
                    System.out.println(startX);

                    
                    //newRegion.add(j);
                    if (startX < img.image.getWidth() && startX >= 0) {
                        img.image.setRGB(startX,startY,pixel);
                    }
                    //j++;
                }
                
                
                
                //imageProcessing.newRegion.size();
                System.out.println("I'm here with imageChangeTimer");
                    //x = x + 5;
            }
         
        }, 0L, 1000L);
        

        
        

        //the L is for long so 2000L means 2 seconds

        final Timer animationTimer = new Timer();
        TimerTask task = new TimerTask(){
            int a,b = 0;
            @Override
            public void run(){
                part = img.image.getSubimage(a,b, width - a, height - b);
                
                ImageIcon newimg = new ImageIcon(part);
                jLabel.setIcon(newimg);
                    
                a = a + 2; 
                running = true;
                //System.out.println("I'm here with animationTimer");
            }
        };
        animationTimer.scheduleAtFixedRate(task, 0, 80);

        jButtonPause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (running) {
                    changeImageTimer.cancel();
                    animationTimer.cancel();
                    task.cancel();
                    running = false;
                }    
                
            }
        });
        
        // jButtonPlay.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         Timer animationTimer2 = new Timer();
                
        //         animationTimer2.scheduleAtFixedRate(task,2000,500);
        //         running = true;
        //     }
        // });
    }

    // boolean increasing = true;
    //             int threshold = 40;
    //             for (int i = 0; i < imageProcessing.newRegion.size(); i++) { 
    //                 int[] array = imageProcessing.newRegion.get(i);
    //                 int x = array[0];
    //                 int y = array[1];
    //                 //System.out.println(x);
    //                 //System.out.println(y);

    //                 int pixel = img.image.getRGB(x,y);
    //                 img.image.setRGB(x,y,192);
    //                 img.image.setRGB((x + imageProcessing.xOffset), y, 255);
    //                 if (imageProcessing.xOffset >= threshold) {
    //                     // When the threshold is reached, start decreasing the number
    //                     increasing = false;
    //                 }

    //                 if (imageProcessing.xOffset <= 0) {
    //                     // When the number becomes zero, reset and start increasing again
    //                     increasing = true;
    //                 }
    //                 if (increasing) {
    //                     imageProcessing.xOffset++;
    //                 } else {
    //                     imageProcessing.xOffset--;
    //                 }
    //             }
    

}
