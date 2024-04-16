package Converter;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Converter {

    public void convertPNGtoJPG(String imgPath, String outputPath) {
        try {

            BufferedImage inputImg = ImageIO.read(new File(imgPath));
            BufferedImage outputImg = new BufferedImage(inputImg.getWidth(), inputImg.getHeight(), BufferedImage.TYPE_INT_RGB);
            outputImg.createGraphics().drawImage(inputImg, 0, 0, Color.WHITE, null);
            ImageIO.write(outputImg, "jpg", new File(outputPath+"/converted.jpg"));
            
            JOptionPane.showMessageDialog(null, "Image converted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error while converting image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void convertJPGtoPNG(String imgPath, String outputPath) {
        try {
            BufferedImage inputImg = ImageIO.read(new File(imgPath));
            BufferedImage outputImg = new BufferedImage(inputImg.getWidth(), inputImg.getHeight(), BufferedImage.TYPE_INT_RGB);
            outputImg.createGraphics().drawImage(inputImg, 0, 0, Color.WHITE, null);
            ImageIO.write(outputImg, "png", new File(outputPath+"/converted.png"));
            
            JOptionPane.showMessageDialog(null, "Image converted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error while converting image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
