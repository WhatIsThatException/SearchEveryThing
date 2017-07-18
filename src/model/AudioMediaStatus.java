package model;

/**
 * Created by kpant on 7/10/17.
 */
public enum AudioMediaStatus {
    NOT_PLAYED,PLAYING, PAUSED, STOPPED;

    static AudioMediaStatus audioMediaStatus = AudioMediaStatus.NOT_PLAYED;

    public static AudioMediaStatus getAudioMediaStatus() {
        return audioMediaStatus;
    }

    public static void setAudioMediaStatus(AudioMediaStatus audioMediaStatus) {
        AudioMediaStatus.audioMediaStatus = audioMediaStatus;
    }
}
