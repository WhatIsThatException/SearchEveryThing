package controller.processers;

import javafx.scene.layout.AnchorPane;

/**
 * Created by kpant on 7/10/17.
 */
public abstract class FileProcesser {
    Object dataToDisplay = null;
    AnchorPane pane = null;
    boolean someFlag = false;

    public AnchorPane getPane() {
        return pane;
    }

    public abstract void processFile(String fileLocation) throws Exception;
}
