package src.fileReading;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by kpant on 7/15/17.
 */
public class ReadingUsingScanner {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = null;
        Scanner sc = null;
        System.out.println("new Date() started= " + new Date());
        StringBuilder result = new StringBuilder("");
        try {
                inputStream = new FileInputStream("/home/kpant/Videos/25.1_-_Marvel_Graph.txt");
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                StringBuilder line = new StringBuilder(sc.nextLine());
                // System.out.println(line);
                result.append(line);
            }
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }
        System.out.println("result = " + result);
        System.out.println("new Date() end= " + new Date());

        //Below program gives same time

//        Path path = Paths.get("/home/kpant/Videos/25.1_-_Marvel_Graph.txt");
//        System.out.println("new Date() started= " + new Date());
//        List<String> str = Files.readAllLines(path);
//        String result = "";
//        for(String listOfString: str) {
//            result += listOfString;
//        }
//        System.out.println("result = " + result);
//        System.out.println("new Date() end= " + new Date());

    }
}
