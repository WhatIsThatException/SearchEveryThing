package src;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by kpant on 7/9/17.
 */
public class AudioPlayTest extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Media media = new Media(new File("/home/kpant/Videos/abc.mp3").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        Runnable playMusic = () -> mediaPlayer.play();
        mediaPlayer.setOnReady(playMusic);
    }
}
