package src.fileReading;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by kpant on 7/15/17.
 */
public class ReadingUsingCommonsIO {
    public static void main(String[] args) throws IOException {
        List<String> str = FileUtils.readLines(new File("/home/kpant/Videos/25.1_-_Marvel_Graph.txt"));
        System.out.println("new Date() end= " + new Date());
        String result = "";
        for(String listOfString: str) {
            result += listOfString;
        }
        System.out.println("result = " + result);
        System.out.println("new Date() end= " + new Date());

    }
}
