package controller.processers;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

import static model.AudioMediaStatus.*;

/**
 * Created by kpant on 7/10/17.
 */
public final class AudioProcesser extends FileProcesser{
    static AudioProcesser audioProcesser;
     Media media;
     MediaPlayer mediaPlayer;


    public static AudioProcesser getAudioProcesser() {
        if (audioProcesser == null)
            audioProcesser = new AudioProcesser();
        return audioProcesser;
    }

    @Override
    public void processFile(String fileLocation) throws Exception {
        switch (getAudioMediaStatus()) {
            case NOT_PLAYED:
                playMedia(fileLocation);
                break;
            case PLAYING:
                /* TIP:
                If mediaPlayer.stop is placed after the line
                *  media = new Media(new File(fileLocation).toURI().toString());
                    mediaPlayer = new MediaPlayer(media);
                    then multiple music play together when multiple different row gets selected,
                    one after another
                */
                mediaPlayer.stop();
                playMedia(fileLocation);
                break;
            default:
                System.out.println("Audio in default case");
        }
    }

    private void playMedia(String fileLocation) {
        media = new Media(new File(fileLocation).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        setAudioMediaStatus(PLAYING);
    }
}
