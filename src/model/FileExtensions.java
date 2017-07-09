package model;

import controller.processers.*;

/**
 * Created by kpant on 7/8/17.
 */
public enum FileExtensions implements FileExtensionInterface {

    AudioType("MP3") {
        @Override
        public FileProcesser processFileTypeAndGetProcesser() {
            return new AudioProcesser();
        }
    }, TextType("TXT", "RTF", "SH") {
        @Override
        public FileProcesser processFileTypeAndGetProcesser() {
            return new TextProcesser();
        }
    }, VideoType("AVI", "MP4") {
        @Override
        public FileProcesser processFileTypeAndGetProcesser() {
            return new VideoProcesser();
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

    private String[] listOfFileExtensions;

    FileExtensions(String... str) {
        this.listOfFileExtensions = str;
    }


    public FileProcesser insideFileType(String fileExtension) {
        for (FileExtensions extensions : FileExtensions.values()) {
            for (String str : listOfFileExtensions) {
                if (str.equalsIgnoreCase(fileExtension))
                    return extensions.processFileTypeAndGetProcesser();
            }
        }
        return null;
    }

}
