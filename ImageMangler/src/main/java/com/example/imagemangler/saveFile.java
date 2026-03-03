package com.example.imagemangler;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;

public class saveFile implements Serializable {

    //Oletus kansio/tiedosto joka avautuu kun importtaa jotain
    private File defaultDir;

    //Viime kerta kun sovellus avattiin
    private String saveDate;


    public int getCanvasX() {
        return canvasX;
    }

    public void setCanvasX(int canvasX) {
        this.canvasX = canvasX;
        FileTool.writeSave(this);
    }

    public int getCanvasY() {
        return canvasY;
    }

    public void setCanvasY(int canvasY) {
        this.canvasY = canvasY;
        FileTool.writeSave(this);
    }

    int canvasX;
    int canvasY;

    public saveFile(File defaultDir) {
        this.defaultDir = defaultDir;
        this.saveDate = LocalDate.now().toString();
        canvasX = 512;
        canvasY = 512;
        FileTool.writeSave(this);
    }



    public saveFile() {
        this.defaultDir = FileSystemView.getFileSystemView().getDefaultDirectory();
        this.saveDate = LocalDate.now().toString();
        FileTool.writeSave(this);
    }

    public void setSaveData(saveFile save){
        this.saveDate = save.saveDate;
        this.defaultDir = save.defaultDir;
        FileTool.writeSave(save);

    }

    public File getDefaultDir() {
        return defaultDir;
    }

    public void setDefaultDir(File defaultDir) {
        this.defaultDir = defaultDir;
    }

    public String getSaveDate() {
        return saveDate;
    }

    public void updateSaveDate() {
        this.saveDate = LocalDate.now().toString();
    }

}