package com.example.imagemangler;

import javafx.scene.image.Image;
import javafx.scene.image.PixelBuffer;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.nio.IntBuffer;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

abstract class ImageTool {
    /*
    Tässä tiedostossa on 2 funktiota, jotka on otettu StackOverflowsta.
    Nämä funktiot kuitenkin säästävät merkittävästi aikaa verratuna Javan omiin versioihin.
    Tällaisessa ohjelmassa, jossa importataan paljon on mielestäni todella tärkeää, että importtaus on nopeaa.
    Ja siis nämä pyörivät yli 50 kertaa nopeammin.
     */



    // Source - https://stackoverflow.com/a/9417836
// Posted by Ocracoke, modified by community. See post 'Timeline' for change history
// Retrieved 2026-02-18, License - CC BY-SA 3.0
    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        java.awt.Image tmp = img.getScaledInstance(newW, newH, java.awt.Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    // Source - https://stackoverflow.com/a/75703543
// Posted by Kevin Bähre, modified by community. See post 'Timeline' for change history
// Retrieved 2026-02-18, License - CC BY-SA 4.0

    public static Image getImage(BufferedImage img){
        //converting to a good type, read about types here: https://openjfx.io/javadoc/13/javafx.graphics/javafx/scene/image/PixelBuffer.html
        BufferedImage newImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB_PRE);
        newImg.createGraphics().drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null);

        //converting the BufferedImage to an IntBuffer
        int[] type_int_agrb = ((DataBufferInt) newImg.getRaster().getDataBuffer()).getData();
        IntBuffer buffer = IntBuffer.wrap(type_int_agrb);

        //converting the IntBuffer to an Image, read more about it here: https://openjfx.io/javadoc/13/javafx.graphics/javafx/scene/image/PixelBuffer.html
        PixelFormat<IntBuffer> pixelFormat = PixelFormat.getIntArgbPreInstance();
        PixelBuffer<IntBuffer> pixelBuffer = new PixelBuffer(newImg.getWidth(), newImg.getHeight(), buffer, pixelFormat);
        return new WritableImage(pixelBuffer);
    }


    public static BufferedImage pickImage() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an Image");

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Image Files (JPG, PNG, GIF, BMP)",
                "jpg", "jpeg", "png", "gif", "bmp"
        );
        fileChooser.setFileFilter(filter);



        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();
            try {
                return ImageIO.read(fileToOpen);
            } catch (IOException e) {
                System.err.println("Error reading image file: " + e.getMessage());
                return null;
            }
        }

        // Käyttäjä sulkee tiedoston valinnan
        return null;
    }

    public static BufferedImage pickImage(File dir) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an Image");

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Image Files (JPG, PNG, GIF, BMP)",
                "jpg", "jpeg", "png", "gif", "bmp"
        );
        fileChooser.setFileFilter(filter);

        fileChooser.setCurrentDirectory(dir);

        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();
            try {
                return ImageIO.read(fileToOpen);
            } catch (IOException e) {
                System.err.println("Error reading image file: " + e.getMessage());
                return null;
            }
        }

        // Käyttäjä sulkee tiedoston valinnan
        return null;
    }






}
