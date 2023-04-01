package testing;
import java.awt.image.BufferedImage;
//import for images
import java.io.File;
//file extension library
import java.io.IOException;

//java exceptions
import javax.imageio.ImageIO;
//image imports
import javax.swing.*;
//output extension
//import java.awt.image.RescaleOp;

public class input {

        public input(){
            BufferedImage image = null;
            try {
                //reads in the image from the file extension
                image = ImageIO.read(new File("/Users/elliestroke/Desktop/algorithm analysis/JuniorIS/happy-cottage.jpeg"));
            } catch (IOException e) {
            }
            //learning about frames from 
            //https://www.edureka.co/blog/java-jframe/#:~:text=JFrame%20in%20Java%3A%20Introduction%20to,starts%20with%20the%20JFrame%20window.
            //creating an instance of the image
            ImageIcon imageIcon = new ImageIcon(image);
            JFrame jFrame = new JFrame();

            //setting the size of the image frame, creating an instance
            jFrame.setSize(500, 500);
            JLabel jLabel = new JLabel("Test Image");

            //setting up where the image will be displayed
            jLabel.setIcon(imageIcon);
            jFrame.add(jLabel);
            jFrame.setVisible(true);


            //code to add buttons which will soon be able to edit the pictures
            // JButton button = new JButton();
            // button.setText("Button");
            // jFrame.add(button);
            
            // //setting parameters to close the image
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {

    }
    // class edit image: darken
}
// class edit image: increase shadows
// class edit image: increase contrast
