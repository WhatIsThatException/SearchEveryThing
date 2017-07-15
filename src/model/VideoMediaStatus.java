package model;

/**
 * Created by kpant on 7/10/17.
 */
public enum VideoMediaStatus {
    NOT_PLAYED,PLAYING, PAUSED, STOPPED;

    static VideoMediaStatus videoMediaStatus = VideoMediaStatus.NOT_PLAYED;

    public static VideoMediaStatus getVideoMediaStatus() {
        return videoMediaStatus;
    }

        public static void setVideoMediaStatus(VideoMediaStatus audioMediaStatus) {
        VideoMediaStatus.videoMediaStatus = audioMediaStatus;
    }
}
