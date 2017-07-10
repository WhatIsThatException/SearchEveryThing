package controller.processers;

import javafx.scene.layout.Pane;

/**
 * Created by kpant on 7/10/17.
 */
public abstract class FileProcesser {
    Object dataToDisplay = null;
    Pane pane = null;
    boolean someFlag = false;

    public Pane getPane() {
        return pane;
    }

    public abstract void processFile(String fileLocation) throws Exception;
}
