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


    public saveFile(File defaultDir) {
        this.defaultDir = defaultDir;
        this.saveDate = LocalDate.now().toString();
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