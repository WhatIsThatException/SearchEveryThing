package controller.processers;

import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by kpant on 7/10/17.
 */
public class TextProcesser extends FileProcesser {

    public TextProcesser() {
        pane = new AnchorPane();
    }

    @Override
    public void processFile(String fileLocation) throws IOException {
        InputStream inputStream = new FileInputStream(fileLocation);
        if (inputStream != null) {
            int b;
            String txtData = "";
            try {
                while ((b = inputStream.read()) != -1) {
                    txtData += (char) b;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                inputStream.close();
            }
            dataToDisplay = txtData;
        }
        TextArea txtArea = new TextArea();
        txtArea.setEditable(false);
        txtArea.setText((String) dataToDisplay);
        txtArea.setPrefHeight(778);
        txtArea.setWrapText(true);
        System.out.println("textArea Data: " + txtArea.getText());
        pane.getChildren().add(txtArea);
    }
}
