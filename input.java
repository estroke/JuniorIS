import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class input {

        public static void main(String[] args){
            BufferedImage image = null;
        try {
            image = ImageIO.read(new File("/Users/elliestroke/Desktop/algorithm analysis/JuniorIS/happy-cottage.jpeg"));
        } catch (IOException e) {
        }
        ImageIcon imageIcon = new ImageIcon(image);
        JFrame jFrame = new JFrame();

            
        jFrame.setSize(500, 500);
        JLabel jLabel = new JLabel();

        jLabel.setIcon(imageIcon);
        jFrame.add(jLabel);
        jFrame.setVisible(true);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
}
