package com.example.imagemangler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PixelProcessor {


    BufferedImage bufimg2;
    enum edittype{
        addition,subtraction,and,xor
    }

    //Rajoittaa int arvon 0 ja 255 välille (tai niihin), koska RGB arvot menevät niiden välillä
    private static int colorClamp(int color){
        if(color > 255){
            return 255;
        }
        if(color < 0){
            return 0;
        }
        return color;
    }

    //Käy läpi jokaisen pixelin kuvassa ja muokkaa sitä käyttöliittymän eri asetuksilla (Asetuksien selostukset ImageMangler.java:ssa)
    public void changeAll(double brightness, int bitwise, int bitwiseRight){

        try{
            Thread thread1 = new Thread(new changeallmulti(brightness,bitwise,bitwiseRight,0));
            thread1.start();
            Thread thread2 = new Thread(new changeallmulti(brightness,bitwise,bitwiseRight,ImageMangler.bufimg.getWidth()/4));
            thread2.start();
            Thread thread3 = new Thread(new changeallmulti(brightness,bitwise,bitwiseRight,ImageMangler.bufimg.getWidth()/2));
            thread3.start();
            Thread thread4 = new Thread(new changeallmulti(brightness,bitwise,bitwiseRight,ImageMangler.bufimg.getWidth()/4*3));
            thread4.start();
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();

        } catch(Exception e){
            return;
        }
        return;
    }

    class changeallmulti extends Thread{

        double brightness;
        int bitwise;
        int bitwiseRight;
        int startx;
        int starty;

        changeallmulti(double brightness, int bitwise, int bitwiseRight, int startx){
            this.brightness = brightness;
            this.bitwise = bitwise;
            this.bitwiseRight = bitwiseRight;
            this.startx = startx;
        }

        public void run(){
            for (int x = 0; x < ImageMangler.bufimg.getWidth()/4; x++){
                for(int y = 0; y < ImageMangler.bufimg.getHeight(); y++){
                    Color pixel = new Color(ImageMangler.bufimg.getRGB(x+startx,y+starty));
                    pixel = new Color(colorClamp(((pixel.getRed() << bitwise) >> bitwiseRight)+(int)brightness),
                            colorClamp(((pixel.getGreen() << bitwise) >> bitwiseRight)+(int)brightness),
                            colorClamp(((pixel.getBlue() << bitwise) >> bitwiseRight)+(int)brightness));
                    ImageMangler.bufimg.setRGB(x+startx,y,pixel.getRGB());

                }
            }
        }
    }


    //Vähentää img1:en pixelejen väriarvoista img2:en samalla kordinaatilla olevan väriarvon.
    public void imgSubtract(BufferedImage img2) throws InterruptedException {
        this.bufimg2 = img2;
        try{
            Thread thread1 = new Thread(new imageSubtractMulti(0));
            thread1.start();
            Thread thread2 = new Thread(new imageSubtractMulti(ImageMangler.bufimg.getWidth()/4));
            thread2.start();
            Thread thread3 = new Thread(new imageSubtractMulti(ImageMangler.bufimg.getWidth()/2));
            thread3.start();
            Thread thread4 = new Thread(new imageSubtractMulti(ImageMangler.bufimg.getWidth()/4*3));
            thread4.start();
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();

        } catch(Exception e){
            throw e;
        }
    }
    //Lisää img1:en pixelejen väriarvoista img2:en samalla kordinaatilla olevan väriarvon.
    public void imgAdd(BufferedImage img2) throws InterruptedException {
        this.bufimg2 = img2;
        try{
            Thread thread1 = new Thread(new imageAddMulti(0));
            thread1.start();
            Thread thread2 = new Thread(new imageAddMulti(ImageMangler.bufimg.getWidth()/4));
            thread2.start();
            Thread thread3 = new Thread(new imageAddMulti(ImageMangler.bufimg.getWidth()/2));
            thread3.start();
            Thread thread4 = new Thread(new imageAddMulti(ImageMangler.bufimg.getWidth()/4*3));
            thread4.start();
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();

        } catch(Exception e){
            throw e;
        }
    }
    //Tässä hauska operaatio!
    //Menee jokaisen Img2:en ja Img1:en pixelin arvo bitin läpi (eli ohittaa signin) ja vertaa niitä. Jos molemmat ovat 1, lopullinen bitti on 1.
    public void imgAnd(BufferedImage img2) throws InterruptedException {
        this.bufimg2 = img2;
        try{
            Thread thread1 = new Thread(new imageAndMulti(0));
            thread1.start();
            Thread thread2 = new Thread(new imageAndMulti(ImageMangler.bufimg.getWidth()/4));
            thread2.start();
            Thread thread3 = new Thread(new imageAndMulti(ImageMangler.bufimg.getWidth()/2));
            thread3.start();
            Thread thread4 = new Thread(new imageAndMulti(ImageMangler.bufimg.getWidth()/4*3));
            thread4.start();
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();

        } catch(Exception e){
            throw e;
        }
    }

    //Tässä toinen hauska operaatio!
    //Menee jokaisen Img2:en pixelin arvo bitin läpi (eli ohittaa signin) ja vertaa niitä. Jos vain toinen on 1, lopullinen bitti on 1.
    public void imgXor(BufferedImage img2) throws InterruptedException {
        this.bufimg2 = img2;
        try{
            Thread thread1 = new Thread(new imageXorMulti(0));
            thread1.start();
            Thread thread2 = new Thread(new imageXorMulti(ImageMangler.bufimg.getWidth()/4));
            thread2.start();
            Thread thread3 = new Thread(new imageXorMulti(ImageMangler.bufimg.getWidth()/2));
            thread3.start();
            Thread thread4 = new Thread(new imageXorMulti(ImageMangler.bufimg.getWidth()/4*3));
            thread4.start();
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();

        } catch(Exception e){
            throw e;
        }
    }

    class imageAddMulti extends Thread{


        int startx;


        imageAddMulti(int startx){
            this.startx = startx;
        }

        public void run(){
                for (int x = 0; x < ImageMangler.bufimg.getWidth() / 4; x++) {
                    for (int y = 0; y < ImageMangler.bufimg.getHeight(); y++) {
                        Color pixel1 = new Color(ImageMangler.bufimg.getRGB(x + startx, y));
                        Color pixel2 = new Color(bufimg2.getRGB(x + startx, y));
                        pixel1 = new Color(colorClamp(pixel1.getRed() + pixel2.getRed()),
                                colorClamp(pixel1.getGreen() + pixel2.getGreen()),
                                colorClamp(pixel1.getBlue() + pixel2.getBlue()));
                        ImageMangler.bufimg.setRGB(x + startx, y, pixel1.getRGB());
                    }
                }
        }
    }

    class imageSubtractMulti extends Thread{


        int startx;


        imageSubtractMulti(int startx){
            this.startx = startx;
        }

        public void run(){
            for (int x = 0; x < ImageMangler.bufimg.getWidth() / 4; x++) {
                for (int y = 0; y < ImageMangler.bufimg.getHeight(); y++) {
                    Color pixel1 = new Color(ImageMangler.bufimg.getRGB(x + startx, y));
                    Color pixel2 = new Color(bufimg2.getRGB(x + startx, y));
                    pixel1 = new Color(colorClamp(pixel1.getRed() - pixel2.getRed()),
                            colorClamp(pixel1.getGreen() - pixel2.getGreen()),
                            colorClamp(pixel1.getBlue() - pixel2.getBlue()));
                    ImageMangler.bufimg.setRGB(x + startx, y, pixel1.getRGB());

                }
            }
        }
    }

    class imageAndMulti extends Thread{

        int startx;

        imageAndMulti(int startx){
            this.startx = startx;
        }

        public void run(){
            for (int x = 0; x < ImageMangler.bufimg.getWidth() / 4; x++) {
                for (int y = 0; y < ImageMangler.bufimg.getHeight(); y++) {
                    Color pixel1 = new Color(ImageMangler.bufimg.getRGB(x + startx, y));
                    Color pixel2 = new Color(bufimg2.getRGB(x + startx, y));
                    pixel1 = new Color(colorClamp(pixel1.getRed() & pixel2.getRed()),
                            colorClamp(pixel1.getGreen() & pixel2.getGreen()),
                            colorClamp(pixel1.getBlue() & pixel2.getBlue()));
                    ImageMangler.bufimg.setRGB(x + startx, y, pixel1.getRGB());

                }
            }
        }
    }

    class imageXorMulti extends Thread{

        int startx;

        imageXorMulti(int startx){
            this.startx = startx;
        }

        public void run(){
            for (int x = 0; x < ImageMangler.bufimg.getWidth() / 4; x++) {
                for (int y = 0; y < ImageMangler.bufimg.getHeight(); y++) {
                    Color pixel1 = new Color(ImageMangler.bufimg.getRGB(x + startx, y));
                    Color pixel2 = new Color(bufimg2.getRGB(x + startx, y));
                    pixel1 = new Color(colorClamp(pixel1.getRed() ^ pixel2.getRed()),
                            colorClamp(pixel1.getGreen() ^ pixel2.getGreen()),
                            colorClamp(pixel1.getBlue() ^ pixel2.getBlue()));
                    ImageMangler.bufimg.setRGB(x + startx, y, pixel1.getRGB());

                }
            }
        }
    }


    class imageInvertMulti extends Thread{


        int startx;


        imageInvertMulti(int startx){
            this.startx = startx;
        }

        public void run(){
            for (int x = 0; x < ImageMangler.bufimg.getWidth() / 4; x++) {
                for (int y = 0; y < ImageMangler.bufimg.getHeight(); y++) {
                    Color pixel1 = new Color(ImageMangler.bufimg.getRGB(x + startx, y));
                    pixel1 = new Color(colorClamp(255-pixel1.getRed()),
                            colorClamp(255-pixel1.getGreen()),
                            colorClamp(255-pixel1.getBlue()));
                    ImageMangler.bufimg.setRGB(x + startx, y, pixel1.getRGB());

                }
            }
        }
    }

    //Klassinen värikääntö. Vähentää kuvan pixelien väriarvot maksimiväriarvosta (joka on tässä kontekstissa 255)
    public void invert() throws InterruptedException {
        Thread thread1 = new Thread(new imageInvertMulti(0));
        thread1.start();
        Thread thread2 = new Thread(new imageInvertMulti(ImageMangler.bufimg.getWidth()/4));
        thread2.start();
        Thread thread3 = new Thread(new imageInvertMulti(ImageMangler.bufimg.getWidth()/2));
        thread3.start();
        Thread thread4 = new Thread(new imageInvertMulti(ImageMangler.bufimg.getWidth()/4*3));
        thread4.start();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();

    }

}
