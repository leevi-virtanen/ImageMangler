package com.example.imagemangler;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.awt.image.BufferedImage;
import java.io.File;

public class ImageMangler extends Application {
    private Image img;
    //Käytetään BufferedImagea,jotta voisi pixeli pixeliltä käsitellä kuvaa ja helposti exporttaa ja importtaa
    private BufferedImage bufimg;
    //Lopullinen prosessoitu kuva näkyy aina mainviewissä
    private ImageView mainview;
    //Arvo joka lisätään kuvan jokaisen pixelin väriarvoihin
    private Slider brightness;
    /*
    Määrittää monta bittiä kuvan väriarvot liikkuvat vasemmalle.
    Pääasiassa vain tummentaa, mutta menetetyn bitin takia tuottaa vähän artifakteja
     */
    private Slider bitshift;
    /*
    Määrittää monta bittiä kuvan väriarvot liikkuvat oikealle.
    Pääasiassa tekee kuvasta kirkkaammaan, mutta ei lineaarisesti kuin brightness.
    Tuottaa enemmän artifakteja kuin vasemmalle liikuttaminen.
     */
    private Slider bitshiftRight;



    saveFile save;

    //Kuvan koko. Kaikki kuvat uudelleen määritellään tähän kokoon
    private int canvasSizeX = 512;
    private int canvasSizeY = 512;

    public int getCanvasSizeX() {
        return canvasSizeX;
    }

    public void setCanvasSizeX(int canvasSizeX) {
        this.canvasSizeX = canvasSizeX;
    }

    public int getCanvasSizeY() {
        return canvasSizeY;
    }

    public void setCanvasSizeY(int canvasSizeY) {
        this.canvasSizeY = canvasSizeY;
    }

    //Eventhandlereiden funktioista erikseen tietoa niiden käyttämissä luokissa.
    //Näissä pääasiassa tiedostojen lataamista ja funktioita muista luokista.
    public EventHandler<ActionEvent> importImg(VBox box){
        if(save.getDefaultDir() != null){
            box.setVisible(true);
            System.out.println("ImportStart");
            try{
                bufimg = ImageTool.resize(ImageTool.pickImage(save.getDefaultDir()),canvasSizeX,canvasSizeY);
                img = ImageTool.getImage(bufimg);
                mainview.setImage(img);
            } catch (Exception e){
                return null;
            }
        } else{
            box.setVisible(true);
            System.out.println("ImportStart");
            try{
                bufimg = ImageTool.resize(ImageTool.pickImage(),canvasSizeX,canvasSizeY);
                img = ImageTool.getImage(bufimg);
                mainview.setImage(img);
            } catch (Exception e){
                return null;
            }
        }



        return null;
    }

    public EventHandler<ActionEvent> setDefaultDirectory(){
        save.setDefaultDir(FileTool.pickDirectory());
        return null;
    }

    public EventHandler<ActionEvent> addition(){
        System.out.println("AdditionStart");
        if(save.getDefaultDir() != null){
            try {
                java.awt.image.BufferedImage bufimg2 = ImageTool.resize(ImageTool.pickImage(save.getDefaultDir()),canvasSizeX,canvasSizeY);
                img = ImageTool.getImage(PixelProcessor.imgAdd(bufimg,bufimg2));
                mainview.setImage(img);
            } catch (Exception e){
                return null;
            }
        } else{
            try {
                java.awt.image.BufferedImage bufimg2 = ImageTool.resize(ImageTool.pickImage(),canvasSizeX,canvasSizeY);
                img = ImageTool.getImage(PixelProcessor.imgAdd(bufimg,bufimg2));
                mainview.setImage(img);
            } catch (Exception e){
                return null;
            }
        }


        return null;
    }

    public EventHandler<ActionEvent> subtraction(){
        System.out.println("SubtractionStart");
        if(save.getDefaultDir() != null){
            try {
                java.awt.image.BufferedImage bufimg2 = ImageTool.resize(ImageTool.pickImage(save.getDefaultDir()),canvasSizeX,canvasSizeY);
                img = ImageTool.getImage(PixelProcessor.imgSubstract(bufimg,bufimg2));
                mainview.setImage(img);
            } catch (Exception e) {
                return null;
            }
        } else {
            try {
                java.awt.image.BufferedImage bufimg2 = ImageTool.resize(ImageTool.pickImage(),canvasSizeX,canvasSizeY);
                img = ImageTool.getImage(PixelProcessor.imgSubstract(bufimg,bufimg2));
                mainview.setImage(img);
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }

    public EventHandler<ActionEvent> and(){
        System.out.println("SubtractionStart");
        if(save.getDefaultDir() != null){
            try {
                java.awt.image.BufferedImage bufimg2 = ImageTool.resize(ImageTool.pickImage(save.getDefaultDir()),canvasSizeX,canvasSizeY);
                img = ImageTool.getImage(PixelProcessor.imgAnd(bufimg,bufimg2));
                mainview.setImage(img);
            } catch (Exception e){
                return null;
            }
        } else{
            try {
                java.awt.image.BufferedImage bufimg2 = ImageTool.resize(ImageTool.pickImage(),canvasSizeX,canvasSizeY);
                img = ImageTool.getImage(PixelProcessor.imgAnd(bufimg,bufimg2));
                mainview.setImage(img);
            } catch (Exception e){
                return null;
            }
        }
        return null;
    }

    public EventHandler<ActionEvent> xor(){
        System.out.println("SubtractionStart");
        if(save.getDefaultDir() != null){
            try {
                java.awt.image.BufferedImage bufimg2 = ImageTool.resize(ImageTool.pickImage(save.getDefaultDir()),canvasSizeX,canvasSizeY);
                img = ImageTool.getImage(PixelProcessor.imgXor(bufimg,bufimg2));
                mainview.setImage(img);
            } catch (Exception e){
                return null;
            }
        } else{
            try {
                java.awt.image.BufferedImage bufimg2 = ImageTool.resize(ImageTool.pickImage(),canvasSizeX,canvasSizeY);
                img = ImageTool.getImage(PixelProcessor.imgXor(bufimg,bufimg2));
                mainview.setImage(img);
            } catch (Exception e){
                return null;
            }
        }
        return null;
    }

    public EventHandler<ActionEvent> updateImage(){
        bufimg = PixelProcessor.changeAll(bufimg,brightness.getValue(),(int)bitshift.getValue(),(int)bitshiftRight.getValue());
        img = ImageTool.getImage(bufimg);
        mainview.setImage(img);
        return null;
    }

    public EventHandler<ActionEvent> exportImage(){
        try{
            FileTool.Save(bufimg);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public EventHandler<ActionEvent> invert(){
        img = ImageTool.getImage(PixelProcessor.invert(bufimg));
        mainview.setImage(img);
        return null;
    }




    @Override
    public void start(Stage stage) {
        mainview = new ImageView();
        HBox mainbox = new HBox(10);
        HBox TopButtons = new HBox(5);
        HBox BotButtons = new HBox(5);
        VBox Right = new VBox(10);
        HBox bitwise = new HBox(3);
        BotButtons.setAlignment(Pos.CENTER_RIGHT);
        Right.setAlignment(Pos.CENTER_RIGHT);
        mainbox.setPadding(new Insets(50,50,50,50));

        try{
            File savefile = new File("ImgMng.mangle");
            save = FileTool.readSave(savefile);
            save.updateSaveDate();
            FileTool.writeSave(save);
        } catch (Exception e){
            save = new saveFile(FileTool.pickDirectory());
        }



        VBox controls = new VBox(10);
        TopButtons.setAlignment(Pos.CENTER_RIGHT);
        controls.setVisible(false);
        Button importbutton = new Button("Import");
        Button exportbutton = new Button("Export");
        Button pickDir = new Button("Choose default directory");
        Button applybutton = new Button("Apply");
        applybutton.setPrefSize(100,50);
        Button addition = new Button("Addition");
        Button subtraction = new Button("Subtraction");

        importbutton.setPrefSize(150,50);

        Button and = new Button("And");
        Button xor = new Button("XOR");
        Button invert = new Button("Invert");

        //määritellään jokaiselle napille funktio
        applybutton.setOnAction(e -> updateImage());
        //Tässä laitetaan importImg:een argumentiksi controls, jotta se näyttäisi kaikki asetukset importin jälkeen.
        importbutton.setOnAction(e -> importImg(controls));
        exportbutton.setOnAction(e -> exportImage());
        pickDir.setOnAction(e -> setDefaultDirectory());
        xor.setOnAction(e -> xor());

        pickDir.setStyle("-fx-background-color: brown;-fx-text-fill: white");
        addition.setStyle("-fx-background-color: green; -fx-text-fill: White");
        subtraction.setStyle("-fx-background-color: red; -fx-text-fill: White");



        and.setStyle("-fx-background-color: yellow");
        xor.setStyle("-fx-background-color: blue; -fx-text-fill: White");


        Text logo = new Text("ImageMangler");
        logo.setFont(Font.loadFont(FileTool.Find("RubikBeastly-Regular.ttf"),30));
        controls.setAlignment(Pos.CENTER_RIGHT);
        brightness = new Slider();
        bitshift = new Slider();
        bitshiftRight = new Slider();
        mainbox.setAlignment(Pos.CENTER);
        mainbox.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.DOTTED, CornerRadii.EMPTY, new BorderWidths(10))));

        brightness.setMax(50);
        brightness.setMin(-50);

        bitshift.setMax(2);
        bitshift.setMin(0);

        //bitshiftit 0-2, koska muuten ne tuottavat liian rajuja efektejä
        bitshiftRight.setMax(2);
        bitshiftRight.setMin(0);

        addition.setOnAction(e -> addition());
        subtraction.setOnAction(e -> subtraction());
        and.setOnAction(e -> and());
        invert.setOnAction(e -> invert());

        Text brightnessLabel = new Text("brightness");
        Text bitshiftLabel = new Text("bitshift (left)");
        Text bitshiftRightLabel = new Text("bitshift (right)");

        Scene scene = new Scene(mainbox, 960, 640);
        stage.setTitle("ImageMangler");

        TopButtons.getChildren().setAll(addition,subtraction,and,xor);
        BotButtons.getChildren().setAll(invert);
        Right.getChildren().setAll(logo,importbutton,controls);
        bitwise.getChildren().setAll(bitshiftLabel,
                bitshift,bitshiftRightLabel,
                bitshiftRight);
        controls.getChildren().setAll(TopButtons,
                brightnessLabel,brightness, bitwise,BotButtons,applybutton, pickDir ,exportbutton);
        mainbox.getChildren().setAll(mainview,Right);
        stage.setScene(scene);
        stage.show();
    }
}
