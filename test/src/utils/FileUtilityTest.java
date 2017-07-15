package src.utils;

import Util.FileUtility;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by kpant on 7/15/17.
 */
public class FileUtilityTest {
    @Test
    public void getFileDirectoryWithoutFileNameTest() {
        String fileLocation = "/home/kpant/Videos/Record1.txt";
        String expectedOutput = "/home/kpant/Videos";
       String str = FileUtility.getFileDirectoryWithoutFileName(fileLocation);
       assertNotNull(str);
       assertEquals(str, expectedOutput);
    }


}
