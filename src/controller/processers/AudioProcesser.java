package controller.processers;

import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

import static model.MediaStatus.PLAYING;
import static model.MediaStatus.getMediaStatus;
import static model.MediaStatus.setMediaStatus;

/**
 * Created by kpant on 7/10/17.
 */
public final class AudioProcesser extends FileProcesser {
    static AudioProcesser audioProcesser;
     Media media;
     MediaPlayer mediaPlayer;


    public static AudioProcesser getAudioProcesser() {
        if (audioProcesser == null)
            audioProcesser = new AudioProcesser();
        return audioProcesser;
    }

    private AudioProcesser() {
        pane = new Pane();
    }

    @Override
    public void processFile(String fileLocation) throws Exception {
        switch (getMediaStatus()) {
            case NOT_PLAYED:
                playMedia(fileLocation);
                break;
            case PLAYING:
                /*If mediaPlayer.stop is placed after the line
                *  media = new Media(new File(fileLocation).toURI().toString());
                    mediaPlayer = new MediaPlayer(media);
                    then multiple music play together when multiple different row gets selected,
                    one after another
                */
                mediaPlayer.stop();
                playMedia(fileLocation);
                break;
        }
//            Runnable playMusic = () -> {
//                    try {
//                        if (getMediaStatus() == MediaStatus.NOT_PLAYED || getMediaStatus() == MediaStatus.STOPPED) {
//                            setMediaStatus(MediaStatus.PLAYING);
//                            mediaPlayer.play();
//
//                        } else if (getMediaStatus() == MediaStatus.PLAYING) {
////                            setMediaStatus(MediaStatus.STOPPED);
//                            mediaPlayer.stop();
//                            throw new InterruptedException();
//                        }
//
//                    }catch(InterruptedException e) {
//                        System.out.println("Caught InterruptedException");
//                    }
//            };
//            mediaPlayer.setOnReady(playMusic);
//        pane.getChildren().add();
    }

    private void playMedia(String fileLocation) {
        media = new Media(new File(fileLocation).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        setMediaStatus(PLAYING);
    }
}
