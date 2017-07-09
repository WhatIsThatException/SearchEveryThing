package model;

import controller.processers.FileProcesser;

/**
 * Created by kpant on 7/8/17.
 */
public interface FileExtensionInterface {
     FileProcesser processFileTypeAndGetProcesser();
}
