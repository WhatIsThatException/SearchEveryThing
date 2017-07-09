package controller.processers;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by kpant on 7/10/17.
 */
public class TextProcesser extends FileProcesser {

    public TextProcesser() {
        pane = new Pane();
    }

    @Override
    public void processFile(String fileLocation) throws IOException {
        InputStream inputStream = new FileInputStream(fileLocation);
        if (inputStream != null) {
            int b;
            String txtData = "";
            while ((b = inputStream.read()) != -1) {
                txtData += (char) b;
            }
            dataToDisplay = txtData;
            System.out.println("txtData = " + txtData);
            inputStream.close();
        }
        TextArea txtArea = new TextArea();
        txtArea.setText((String) dataToDisplay);
        System.out.println("textArea Data: " + txtArea.getText());
        pane.getChildren().add(txtArea);
    }
}
