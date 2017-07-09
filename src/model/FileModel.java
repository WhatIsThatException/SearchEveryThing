package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;

/**
 * Created by kpant on 6/26/17.
 */
public class FileModel {
    private File files;
    private SimpleStringProperty fileSize;
    private SimpleStringProperty fileLocation;
    private SimpleStringProperty fileName;
    private String fileToSearchIn;
    private boolean isFolder;
private ObservableList<FileModel> data = FXCollections.observableArrayList();

    public FileModel(String fileToSearchIn) {
        this.fileToSearchIn = fileToSearchIn;
    }

    public FileModel(SimpleStringProperty fileName, SimpleStringProperty fileSize, SimpleStringProperty fileLocation,
                     boolean isFolder) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileLocation = fileLocation;
        this.isFolder = isFolder;
    }

    public FileModel(String name, double v, File absoluteFile) {
    }


    public String getFileToSearchIn() {
        return fileToSearchIn;
    }


    public String getFileSize() {
        return fileSize.get();
    }

    public SimpleStringProperty fileSizeProperty() {
        return fileSize;
    }

    public String getFileLocation() {
        return fileLocation.get();
    }

    public SimpleStringProperty fileLocationProperty() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation.set(fileLocation);
    }

    public String getFileName() {
        return fileName.get();
    }

    public SimpleStringProperty fileNameProperty() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName.set(fileName);
    }


    public ObservableList<FileModel> getData() {
        return data;
    }

    public boolean isFolder() {
        return isFolder;
    }
}
