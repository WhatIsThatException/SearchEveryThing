package controller;

import controller.service.FilePreviewService;
import controller.service.FileSearchService;
import controller.service.FolderPreviewService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import model.FileModel;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by kpant on 6/26/17.
 */
public class MainUIController implements Initializable {
    FileModel fileModel = new FileModel("/home/kpant/Videos");
    FileSearchService fileSearchService;
    FolderPreviewService folderPreviewService;
    FilePreviewService filePreviewService;
    boolean folderCheckBoxOn = false;
    boolean fileCheckBoxOn = false;

    @FXML
    private TableColumn<FileModel, SimpleStringProperty> sizeCol;

    @FXML
    private TableColumn<FileModel, SimpleStringProperty> locationCol;

    @FXML
    private TextField searchField;

    @FXML
    private TableView<FileModel> recordsTableView;

    @FXML
    private TableColumn<FileModel, SimpleStringProperty> nameCol;

    @FXML
    private ScrollPane previewPane;

    @FXML
    private CheckBox fileCheckBox;

    @FXML
    private Label searchLabel;

    @FXML
    private AnchorPane recordPreviewPane;

    @FXML
    private CheckBox folderCheckBox;

    ObservableList<FileModel> fm = FXCollections.observableArrayList();

    @FXML
    void searchLabelClicked(ActionEvent event) {

    }

    @FXML
    void searchFieldAction(ActionEvent event) {
        fm.clear();
        String getSearchFieldText = searchField.getText();
        if (getSearchFieldText != "") {
            for (FileModel model : fileModel.getData()) {
                String fileLocation = model.fileLocationProperty().get();
                if (fileLocation.toLowerCase().contains(getSearchFieldText.toLowerCase()) || getSearchFieldText.equalsIgnoreCase("")) {
                    if(folderCheckBoxOn == true && model.isFolder()) {
                        addFileModels(model);
                    } else if((fileCheckBoxOn == true) && !model.isFolder()) {
                        addFileModels(model);
                    } else if(folderCheckBoxOn == false && fileCheckBoxOn == false){
                        addFileModels(model);
                    }
                }
            }
            recordsTableView.setItems(fm);
        }
    }

    private void addFileModels(FileModel model) {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("fileSize"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("fileLocation"));
        fm.add(model);
    }

    @FXML
    void folderCheckBoxAction(ActionEvent event) {
        folderCheckBoxOn = !folderCheckBoxOn;
        searchFieldAction(event);
    }


    @FXML
    void fileCheckBoxAction(ActionEvent event) {
        fileCheckBoxOn = !fileCheckBoxOn;
        searchFieldAction(event);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FileSearchService fileSearchService = new FileSearchService(fileModel);
        fileSearchService.start();


        recordsTableView.setRowFactory(tv -> {
            TableRow<FileModel> row = new TableRow<FileModel>();
            row.setOnMouseClicked(event -> {
                if(!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                    FileModel fileModel = row.getItem();
                    filePreviewService = new FilePreviewService(fileModel);
                    filePreviewService.restart();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                        previewPane.setContent(filePreviewService.getFileProcesser().getPane());
                }
            });
        return row;});

        nameCol.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("fileSize"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("fileLocation"));

        recordsTableView.setItems(fileModel.getData());
    }


}
