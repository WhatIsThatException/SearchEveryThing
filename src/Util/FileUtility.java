package Util;

import controller.processers.FileProcesser;
import model.FileExtensions;
import model.FileModel;

/**
 * Created by kpant on 6/26/17.
 */
public class FileUtility {
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

    public static String formatSize(long size){
        String returnValue;
        if(size < 0) {
            returnValue = "0";
        } else if(size < 1024) {
            returnValue = size + " B";
        } else if(size < (1024*1024)) {
            returnValue = size/1024 + " kB";
        } else {
            returnValue = size/(1024*1024) + " MB";
        }
        return returnValue;

    }

    public static String reverseFileName(String fileName){
        String temp = new String("");
        for(int i = fileName.length(); i >= 1; i--) {
            temp += fileName.charAt(i - 1);
        }
        return temp;
    }

    public static String getFileExtension(FileModel model) {
        String str = reverseFileName(model.getFileName());
        return str.substring(0,str.indexOf("."));
    }

    public static FileProcesser getFileProcesser(String fileExtension) {
        FileProcesser fileProcesser = null;
        for (FileExtensions extensions : FileExtensions.values()) {
            if(fileProcesser == null)
                fileProcesser= extensions.insideFileType(fileExtension);

        }
        return fileProcesser;
    }
}
