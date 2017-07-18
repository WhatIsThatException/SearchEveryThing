package controller.processers;

import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by kpant on 7/10/17.
 */
public class TextProcesser extends FileProcesser {

    static TextProcesser textProcesser = null;

    public static FileProcesser getTextProcesser() {
        if(textProcesser == null)
            textProcesser = new TextProcesser();
        return textProcesser;
    }

    @Override
    public void processFile(String fileLocation) throws IOException {
        InputStream inputStream = new FileInputStream(fileLocation);
        Scanner sc = null;

        //text file: 25.1_-_Marvel_Graph.txt, size 1.5MB
        System.out.println("Data reading started = " + new Date());
        if (inputStream != null) {
            StringBuilder txtData = new StringBuilder("");
            try {
                sc = new Scanner(inputStream, "UTF-8");
                while (sc.hasNextLine()) {
                    txtData.append(sc.nextLine());
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
            dataToDisplay = txtData.toString();
        }
        System.out.println("Data reading finished = " + new Date());

    }

    @Override
    public AnchorPane getPane(){
        TextArea txtArea = new TextArea();
        txtArea.setEditable(false);
        txtArea.setText((String) dataToDisplay);
        txtArea.setPrefHeight(778);
        txtArea.setWrapText(true);
        pane.getChildren().add(txtArea);
        return pane;
    }
}
