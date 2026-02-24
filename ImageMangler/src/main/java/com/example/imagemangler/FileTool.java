package com.example.imagemangler;



import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

abstract class FileTool{



    //Etsii tiedoston resursseista sen nimen perusteella ja palauttaa sen polun.
    public static String Find(String file){
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL url = classloader.getResource(file);
        return url.toString();
    }

    /*
    Tallentaa BufferedImagen PNG tiedostoon ja jakaa sille satunnaisen numeron 0-9999
    (HUOM! jos tiedostoja on se 10000, tekee loputtoman WHILE loopin)
    Oletettavasti tämä ei ole ongelma.
    Jos joku oikeasti käyttää siihen niin paljon aikaa, voivat maksaa mulle ohjelmistotuotannosta palkkaa.
    Tämä ei siis ole passiivi agressiivinen kommentti opettajalle.
     */
    public static Boolean Save(BufferedImage bufimg){
        Random rand = new Random();
        int randomnum = rand.nextInt(10000);
        File output = new File("img"+randomnum+".png");
        while(output.exists()){
            randomnum = rand.nextInt(10000);
            output = new File("img"+randomnum+".png");
        }
        try{
            ImageIO.write(bufimg, "png",output);
            return true;
        } catch(Exception e){
            return false;
        }

    }

    //Avaa tiedoston valinnan ja antaa käyttäjän valita oletuskansion, jonka ohjelma avaa importtauksessa
    public static File pickDirectory() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select the default import directory");

        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);


        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try {
                return fileChooser.getSelectedFile();
            } catch (Exception e) {
                System.err.println("Error reading file: " + e.getMessage());
                return null;
            }
        }

        //Käyttäjä sulkee tiedoston valinnan
        return null;
    }

    //Kirjoittaa käyttäjätietoja
    public static void writeSave(saveFile save){
        try{
            FileOutputStream output = new FileOutputStream("ImgMng.mangle");
            ObjectOutputStream objectout = new ObjectOutputStream(output);
            objectout.writeObject(save);
            output.close();
            objectout.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Lukee käyttäjätietoja
    public static saveFile readSave(File file){
        try{
            FileInputStream input = new FileInputStream(file);
            ObjectInputStream objectinput = new ObjectInputStream(input);
            saveFile save = (saveFile)objectinput.readObject();
            input.close();
            objectinput.close();
            return save;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
