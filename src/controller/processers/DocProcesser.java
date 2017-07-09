package controller.processers;

import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * Created by kpant on 7/10/17.
 */
public class DocProcesser extends FileProcesser {

    public DocProcesser() {
        pane = new Pane();
    }

    @Override
    public void processFile(String fileLocation) throws Exception {
        Media media = new Media(new File(fileLocation).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
//        pane.getChildren().add();
    }
}
