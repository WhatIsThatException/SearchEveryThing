package controller;

import controller.service.CacheFileService;
import controller.service.FilePreviewService;
import controller.service.FileSearchService;
import controller.service.FolderPreviewService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.FileModel;
import view.ViewFactory;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by kpant on 6/26/17.
 */
public class MainUIController implements Initializable {
    FileModel fileModel;
    FileSearchService fileSearchService;
    FolderPreviewService folderPreviewService;
    FilePreviewService filePreviewService;
    ViewFactory defaultViewFactory = new ViewFactory();

    private MenuItem showRecordInfo = new MenuItem("show Details");
    private ExecutorService fileSearchExecutor;

    private String[] locationsToSearch = {"/media/kpant/Transcend/","/home/kpant/Videos"};

    public void init(){
        fileSearchExecutor = Executors.newFixedThreadPool(locationsToSearch.length,
                new FolderSearchThreadFactory("parallel"));
    }

    public void stop() throws InterruptedException {
        fileSearchExecutor.shutdown();
        fileSearchExecutor.awaitTermination(3, TimeUnit.SECONDS);
    }

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
        if (!getSearchFieldText.equalsIgnoreCase("") && searchFinished == true) {
            for (FileModel model : fileModel.getData()) {
                String fileName = model.fileNameProperty().get();
                if (fileName.toLowerCase().contains(getSearchFieldText.toLowerCase()) || getSearchFieldText.equalsIgnoreCase("")) {
                    itemFileOrFolder(model);
                }
            }
            recordsTableView.setItems(fm);
        }else if(searchFinished == true) {
            for (FileModel model : fileModel.getData()) {
                itemFileOrFolder(model);
            }
            recordsTableView.setItems(fm);
        }
    }

    private void itemFileOrFolder(FileModel model) {
        if (folderCheckBoxOn == true && model.isFolder()) {
            addFileModels(model);
        } else if ((fileCheckBoxOn == true) && !model.isFolder()) {
            addFileModels(model);
        } else if (folderCheckBoxOn == false && fileCheckBoxOn == false) {
            addFileModels(model);
        }
    }

    private void addFileModels(FileModel model) {
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

boolean searchFinished = false;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();

        for (int i = 0; i < locationsToSearch.length; i++) {
            fileModel = new FileModel(locationsToSearch[i]);
            FileSearchService fileSearchService = new FileSearchService(fileModel);
            fileSearchService.setExecutor(fileSearchExecutor);
            fileSearchService.setOnSucceeded(e -> {
                fileSearchService.setFileSearchCompleted(true);
                searchFinished = true;
            });
            fileSearchService.start();

            CacheFileService cfs = new CacheFileService(locationsToSearch[i]);

        }
        try {
            stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        nameCol.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("fileSize"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("fileLocation"));

        recordsTableView.setItems(fileModel.getData());

        recordsTableView.setContextMenu(new ContextMenu(showRecordInfo));

        recordsTableView.setRowFactory(tv -> {
            TableRow<FileModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                    FileModel fileModel = row.getItem();
                    filePreviewService = new FilePreviewService(fileModel);
                    filePreviewService.setOnSucceeded(event1 -> {
                        recordPreviewPane = filePreviewService.getFileProcesser().getPane();
                        previewPane.setContent(recordPreviewPane);
                    });
                    filePreviewService.restart();

                } else if(!row.isEmpty() && event.getButton() == MouseButton.SECONDARY) {
                    FileModel fileModel = row.getItem();
                        showRecordInfo.setOnAction( e -> {
                        Scene scene = defaultViewFactory.getRecordInfoScene(fileModel);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                    });

                }
            });
            return row;});


    }

    static class FolderSearchThreadFactory implements ThreadFactory {

        static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final String type;
        public FolderSearchThreadFactory(String type) {
            this.type = type;
        }

        @Override
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "LineService-" + poolNumber.getAndIncrement() + "-thread-" + type);
            thread.setDaemon(false);

            return thread;
        }
    }
}
