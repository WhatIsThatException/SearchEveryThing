package controller.service;

import Util.FileUtility;
import controller.processers.FileProcesser;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import model.FileModel;

import static Util.FileUtility.getFileExtension;
import static Util.FileUtility.reverseFileName;

/**
 * Created by kpant on 7/7/17.
 */
public class FilePreviewService extends Service<Void> {
    FileModel model;
    private FileProcesser fileProcesser;
    String fileExtension = "";


    public FileProcesser getFileProcesser() {
        return fileProcesser;
    }

    public FilePreviewService(FileModel model) {
        this.model = model;
        this.fileExtension = reverseFileName(getFileExtension(model));

    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                System.out.println("FIlePreviewService, createTask() Thread = " + Thread.currentThread().getName());
               fileProcesser = FileUtility.getFileProcesserFromUtil(fileExtension);
               getFileProcesser().processFile(model.getFileLocation());
                return null;
            }


        };
    }
}
