package Util;

import controller.processers.FileProcesser;
import model.FileExtensions;
import model.FileModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;

/**
 * Created by kpant on 6/26/17.
 */
public class FileUtility {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

//    public static void searchFiles(File[] allFilesArray){
//        for(File file: allFilesArray) {
//            if(file.isDirectory()) {
//                listFiles(file);
//                System.out.println("Folder = " + file);
//            } else {
//                System.out.println("file = " + file);
//            }
//        }
//    }
//
//    private static void listFiles(File files) {
//        for(File file: files.listFiles()) {
//            if(file.isDirectory()) {
//                System.out.println("folder = " + file);
//                listFiles(file);
//            } else {
//                System.out.println("file = "+file);
//            }
//        }
//    }

    public static String formatSize(long size) {
        String returnValue;
        if (size < 0) {
            returnValue = "0";
        } else if (size < 1024) {
            returnValue = size + " B";
        } else if (size < (1024 * 1024)) {
            returnValue = size / 1024 + " kB";
        } else {
            returnValue = size / (1024 * 1024) + " MB";
        }
        return returnValue;

    }

    public static String reverseFileName(String fileName) {
        String temp = new String("");
        for (int i = fileName.length(); i >= 1; i--) {
            temp += fileName.charAt(i - 1);
        }
        return temp;
    }

    public static String getFileExtension(FileModel model) {
        String str = reverseFileName(model.getFileName());
        return str.substring(0, str.indexOf("."));
    }

    public static FileProcesser getFileProcesserFromUtil(String fileExtension) {
        FileProcesser fileProcesser = null;
        for (FileExtensions extensions : FileExtensions.values()) {
            if (fileProcesser == null)
                fileProcesser = extensions.insideFileType(fileExtension);

        }
        return fileProcesser;
    }

    public static String getFileCreationAndModifiedDates(String string) throws IOException {
        Path path = Paths.get(string);
        BasicFileAttributes view
                = Files.getFileAttributeView(path, BasicFileAttributeView.class)
                .readAttributes();

        string = dateFormat.format(view.lastAccessTime().toMillis());

        return string;
    }

    public static String getFileDirectoryWithoutFileName(String fileLocation) {
        if (fileLocation != null && !fileLocation.equalsIgnoreCase("") && fileLocation.contains("/")) {
            return fileLocation.substring(0, fileLocation.lastIndexOf("/"));
        }
        return null;
    }

}
