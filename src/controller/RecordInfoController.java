package controller;

import Util.FileUtility;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.FileModel;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by kpant on 7/15/17.
 */
public class RecordInfoController implements Initializable {

    FileModel fileModel;
    Desktop desktop = Desktop.getDesktop();

    public RecordInfoController(FileModel fileModel) {
        this.fileModel = fileModel;
    }

    @FXML // fx:id="fileNameLabel"
    private Label fileNameLabel; // Value injected by FXMLLoader

    @FXML // fx:id="modifiedDateLabelAns"
    private Label modifiedDateLabelAns; // Value injected by FXMLLoader

    @FXML // fx:id="openWithBtn"
    private Button openWithBtn; // Value injected by FXMLLoader

    @FXML // fx:id="fileSizeLabel"
    private Label fileSizeLabel; // Value injected by FXMLLoader

    @FXML // fx:id="locationOfFileLabelAns"
    private Label locationOfFileLabelAns; // Value injected by FXMLLoader

    @FXML // fx:id="nameOfTheFileLabelAns"
    private Label nameOfTheFileLabelAns; // Value injected by FXMLLoader

    @FXML // fx:id="fileLocationLabel"
    private Label fileLocationLabel; // Value injected by FXMLLoader

    @FXML // fx:id="sizeOfFileLabelAns"
    private Label sizeOfFileLabelAns; // Value injected by FXMLLoader

    @FXML // fx:id="lastModifiedLabel"
    private Label lastModifiedLabel; // Value injected by FXMLLoader

    @FXML // fx:id="openFldrLocationBtn"
    private Button openFldrLocationBtn; // Value injected by FXMLLoader

    @FXML
    void openFolderLocationAction(ActionEvent event) {
        System.out.println("fileModel.getFileLocation() = " + fileModel.getFileLocation());
            if (Desktop.isDesktopSupported()) {
                new Thread(() -> {
                    try {
                        Desktop.getDesktop().open(new File(FileUtility.getFileDirectoryWithoutFileName(fileModel.getFileLocation())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
        }
    }

    @FXML
    void openWithAction(ActionEvent event) {
        boolean flag = false;
        Task task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Desktop.getDesktop().open(new File(fileModel.getFileLocation()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        new Thread(task).start();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("All item initialized from RecordInfoController");
        nameOfTheFileLabelAns.setText(fileModel.getFileName());
        sizeOfFileLabelAns.setText(fileModel.getFileSize());
        locationOfFileLabelAns.setText(fileModel.getFileLocation());
        try {
            modifiedDateLabelAns.setText(FileUtility.getFileCreationAndModifiedDates(fileModel.getFileLocation()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
