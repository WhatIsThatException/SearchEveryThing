package view;

import controller.RecordInfoController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import model.FileModel;

/**
 * Created by kpant on 7/15/17.
 */
public class ViewFactory {
    private static final String DEFAULT_CSS = "style.css";
    private static final String RECORD_DETAIL_LAYOUT_FXML = "RecordInfoUI.fxml";
    private static final String MAIN_UI_FXML = "MainUI.fxml";

    public Scene getRecordInfoScene(FileModel fileModel){
       RecordInfoController recordInfoController = new RecordInfoController(fileModel);
       return initializeScene(RECORD_DETAIL_LAYOUT_FXML,recordInfoController);
    }

    private Scene initializeScene(String fxmlPath, RecordInfoController controller) {
        FXMLLoader loader;
        Parent parent;
        Scene scene;
        try{
            loader = new FXMLLoader(getClass().getResource(fxmlPath));
            loader.setController(controller);
            parent  = loader.load();
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
        scene = new Scene(parent);
//        scene.getStylesheets().add(getClass().getResource(DEFAULT_CSS).toExternalForm());
        return scene;
    }

}
