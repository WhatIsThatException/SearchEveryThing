package controller.processers;

import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

import static model.VideoMediaStatus.PLAYING;
import static model.VideoMediaStatus.getVideoMediaStatus;
import static model.VideoMediaStatus.setVideoMediaStatus;

/**
 * Created by kpant on 7/10/17.
 */
public class VideoProcesser extends FileProcesser {
    static VideoProcesser videoProcesser = null;
    MediaPlayer mediaPlayer;
    Media media;
    MediaView mediaView;
    public VideoProcesser() {
        pane = new AnchorPane();
    }

    @Override
    public void processFile(String fileLocation) throws Exception {
        switch (getVideoMediaStatus()) {
            case NOT_PLAYED:
                playVideo(fileLocation);
                break;
            case PLAYING:
                mediaPlayer.stop();
                playVideo(fileLocation);
                break;
            default:
                System.out.println("Audio in default case");
        }


//        pane.getChildren().add();
    }

    @Override
    public AnchorPane getPane(){

        return pane;
    }

    private void playVideo(String fileLocation) {
        System.out.println("VideoProcesser Thread = " + Thread.currentThread().getName());
        media = new Media(new File(fileLocation).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView = new MediaView(mediaPlayer);
        runnable = () -> {
            System.out.println("Inside runnable VideoProcesser Thread = " + Thread.currentThread().getName());
            mediaPlayer.play();
        };
        mediaPlayer.setOnReady(runnable);
        setVideoMediaStatus(PLAYING);

        mediaView.setPreserveRatio(true);
        pane.getChildren().add(mediaView);
    }

    public static FileProcesser getVideoProcesser() {
        if(videoProcesser == null)
            videoProcesser = new VideoProcesser();
        return videoProcesser;
    }
}
