package com.example.imagemangler;

import java.awt.*;
import java.awt.image.BufferedImage;

abstract class PixelProcessor {

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
    public static BufferedImage changeAll(BufferedImage img, double brightness, int bitwise, int bitwiseRight){

        for (int x = 0; x < img.getWidth(); x++){
            for(int y = 0; y < img.getHeight(); y++){
                Color pixel = new Color(img.getRGB(x,y));
                pixel = new Color(colorClamp(((pixel.getRed() << bitwise) >> bitwiseRight)+(int)brightness),
                        colorClamp(((pixel.getGreen() << bitwise) >> bitwiseRight)+(int)brightness),
                        colorClamp(((pixel.getBlue() << bitwise) >> bitwiseRight)+(int)brightness));
                img.setRGB(x,y,pixel.getRGB());

            }
        }

        return img;
    }
    //Vähentää img1:en pixelejen väriarvoista img2:en samalla kordinaatilla olevan väriarvon.
    public static BufferedImage imgSubstract(BufferedImage img1, BufferedImage img2){
        for (int x = 0; x < img1.getWidth(); x++){
            for(int y = 0; y < img1.getHeight(); y++){
                Color pixel1 = new Color(img1.getRGB(x,y));
                Color pixel2 = new Color(img2.getRGB(x,y));
                pixel1 = new Color(colorClamp(pixel1.getRed()-pixel2.getRed()),
                        colorClamp(pixel1.getGreen()-pixel2.getGreen()),
                        colorClamp(pixel1.getBlue()-pixel2.getBlue()));
                img1.setRGB(x,y,pixel1.getRGB());

            }
        }
        return img1;
    }
    //Lisää img1:en pixelejen väriarvoista img2:en samalla kordinaatilla olevan väriarvon.
    public static BufferedImage imgAdd(BufferedImage img1, BufferedImage img2){
        for (int x = 0; x < img1.getWidth(); x++){
            for(int y = 0; y < img1.getHeight(); y++){
                Color pixel1 = new Color(img1.getRGB(x,y));
                Color pixel2 = new Color(img2.getRGB(x,y));
                pixel1 = new Color(colorClamp(pixel1.getRed()+pixel2.getRed()),
                        colorClamp(pixel1.getGreen()+pixel2.getGreen()),
                        colorClamp(pixel1.getBlue()+pixel2.getBlue()));
                img1.setRGB(x,y,pixel1.getRGB());

            }
        }
        return img1;
    }
    //Tässä hauska operaatio!
    //Menee jokaisen Img2:en ja Img1:en pixelin arvo bitin läpi (eli ohittaa signin) ja vertaa niitä. Jos molemmat ovat 1, lopullinen bitti on 1.

    public static BufferedImage imgAnd(BufferedImage img1, BufferedImage img2){
        for (int x = 0; x < img1.getWidth(); x++){
            for(int y = 0; y < img1.getHeight(); y++){
                Color pixel1 = new Color(img1.getRGB(x,y));
                Color pixel2 = new Color(img2.getRGB(x,y));
                pixel1 = new Color(colorClamp(pixel1.getRed()&pixel2.getRed()),
                        colorClamp(pixel1.getGreen()&pixel2.getGreen()),
                        colorClamp(pixel1.getBlue()&pixel2.getBlue()));
                img1.setRGB(x,y,pixel1.getRGB());

            }
        }
        return img1;
    }

    //Tässä toinen hauska operaatio!
    //Menee jokaisen Img2:en pixelin arvo bitin läpi (eli ohittaa signin) ja vertaa niitä. Jos vain toinen on 1, lopullinen bitti on 1.
    public static BufferedImage imgXor(BufferedImage img1, BufferedImage img2){
        for (int x = 0; x < img1.getWidth(); x++){
            for(int y = 0; y < img1.getHeight(); y++){
                Color pixel1 = new Color(img1.getRGB(x,y));
                Color pixel2 = new Color(img2.getRGB(x,y));
                pixel1 = new Color(colorClamp(pixel1.getRed()^pixel2.getRed()),
                        colorClamp(pixel1.getGreen()^pixel2.getGreen()),
                        colorClamp(pixel1.getBlue()^pixel2.getBlue()));
                img1.setRGB(x,y,pixel1.getRGB());

            }
        }
        return img1;
    }


    //Klassinen värikääntö. Vähentää kuvan pixelien väriarvot maksimiväriarvosta (joka on tässä kontekstissa 255)
    public static BufferedImage invert(BufferedImage img1){
        for (int x = 0; x < img1.getWidth(); x++){
            for(int y = 0; y < img1.getHeight(); y++){
                Color pixel = new Color(img1.getRGB(x,y));

                pixel = new Color(colorClamp(255-pixel.getRed()),
                        colorClamp(255-pixel.getGreen()),
                        colorClamp(255-pixel.getBlue()));
                img1.setRGB(x,y,pixel.getRGB());

            }
        }
        return img1;
    }

}
