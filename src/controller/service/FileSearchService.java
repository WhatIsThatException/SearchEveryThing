package controller.service;

import Util.FileUtility;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import model.FileModel;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static model.FileModel.getData;

/**
 * Created by kpant on 6/26/17.
 */
public class FileSearchService extends Service<Void> {

    FileModel fileModel;
    private File file;
    private boolean fileSearchCompleted = false;

    private Map<String, FileModel> fileModelMap = new HashMap<>();

    public FileSearchService(FileModel fileModel) {
        this.fileModel = fileModel;
        file = new File(fileModel.getFileToSearchIn());
        this.setOnSucceeded(e -> {

        });
    }

    public Map<String, FileModel> getFileModelMap() {
        return fileModelMap;
    }


    public void addToFileModelMap() {
        searchFiles(file.listFiles());
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                System.out.println("START TIME::"+new Date().toString());
                addToFileModelMap();
                System.out.println("END TIME::"+new Date().toString());
                return null;
            }
        };
    }

    private void searchFiles(File[] allFilesArray) {
        for (File file : allFilesArray) {
            if (file.isDirectory()) {
                listFiles(file);
            } else {
                FileModel fModel = new FileModel(new SimpleStringProperty(file.getName()),
                        new SimpleStringProperty(FileUtility.formatSize(file.length())), new SimpleStringProperty(file.getAbsolutePath()),
                        false);
                getData().add(fModel);
            }
        }
    }

    private void listFiles(File files) {
        for (File file : files.listFiles()) {
            if (file.isDirectory()) {
                FileModel fModel = new FileModel(new SimpleStringProperty(file.getName()),
                        new SimpleStringProperty(FileUtility.formatSize(file.length())), new SimpleStringProperty(file.getAbsolutePath())
                ,true);
                getData().add(fModel);
                listFiles(file);
            } else {
                FileModel fModel = new FileModel(new SimpleStringProperty(file.getName()),
                        new SimpleStringProperty(FileUtility.formatSize(file.length())), new SimpleStringProperty(file.getAbsolutePath()), false);
                if(fModel == null) {
                } else {
                    getData().add(fModel);
                }
            }
        }
    }

    public boolean isFileSearchCompleted() {
        return fileSearchCompleted;
    }

    public void setFileSearchCompleted(boolean fileSearchCompleted) {
        this.fileSearchCompleted = fileSearchCompleted;
    }
}
