package model;

import controller.processers.*;

/**
 * Created by kpant on 7/8/17.
 */
public enum FileExtensions implements FileExtensionInterface {

    AudioType("MP3") {
        @Override
        public FileProcesser processFileTypeAndGetProcesser() {
            return AudioProcesser.getAudioProcesser();
        }
    }, TextType("TXT", "RTF", "SH", "JAVA", "PHP") {
        @Override
        public FileProcesser processFileTypeAndGetProcesser() {
            return new TextProcesser();
        }
    }, VideoType("AVI", "MP4", "FLV") {
        @Override
        public FileProcesser processFileTypeAndGetProcesser() {
            return VideoProcesser.getVideoProcesser();
        }
    }, DocType("PDF", "DOC") {
        @Override
        public FileProcesser processFileTypeAndGetProcesser() {
            return new DocProcesser();
        }
    }, ZipType("ZIP", "RAR") {
        @Override
        public FileProcesser processFileTypeAndGetProcesser() {
            return new ZipProcesser();
        }
    };
    String[] textType;

    private String[] listOfFileExtensions;

    FileExtensions(String... str) {
        this.listOfFileExtensions = str;
    }


    public FileProcesser insideFileType(String fileExtension) {
            for (String str : listOfFileExtensions) {
                if (str.equalsIgnoreCase(fileExtension))
                    return processFileTypeAndGetProcesser();
            }
        return null;
    }

}
