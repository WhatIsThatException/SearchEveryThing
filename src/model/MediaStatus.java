package model;

/**
 * Created by kpant on 7/10/17.
 */
public enum MediaStatus {
    NOT_PLAYED,PLAYING, PAUSED, STOPPED;

    static MediaStatus mediaStatus = MediaStatus.NOT_PLAYED;

    public static MediaStatus getMediaStatus() {
        return mediaStatus;
    }

    public static void setMediaStatus(MediaStatus mediaStatus) {
        MediaStatus.mediaStatus = mediaStatus;
    }
}
