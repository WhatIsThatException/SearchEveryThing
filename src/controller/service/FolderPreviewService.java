package controller.service;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import model.FileModel;

/**
 * Created by kpant on 7/7/17.
 */
public class FolderPreviewService extends Service<Void> {
    FileModel folderToPreview;

    public FolderPreviewService(FileModel folderToPreview) {
        this.folderToPreview = folderToPreview;
    }
    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                return null;
            }
        };
    }
}
