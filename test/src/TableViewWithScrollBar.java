package src;

import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.Month;

/**
 * Created by kpant on 7/11/17.
 */
public class TableViewWithScrollBar extends Application {

    private TableView<Data> table1 = new TableView<>(); // table with scrollbars

    private final ObservableList<Data> data =
            FXCollections.observableArrayList(
                    new Data( LocalDate.of(2015, Month.JANUARY, 10), 10.0, 20.0, 30.0),
                    new Data( LocalDate.of(2015, Month.JANUARY, 11), 40.0, 50.0, 60.0),
                    new Data( LocalDate.of(2015, Month.JANUARY, 12), 10.0, 20.0, 30.0),
                    new Data( LocalDate.of(2015, Month.JANUARY, 13), 40.0, 50.0, 60.0),
                    new Data( LocalDate.of(2015, Month.JANUARY, 14), 10.0, 20.0, 30.0),
                    new Data( LocalDate.of(2015, Month.JANUARY, 15), 40.0, 50.0, 60.0),
                    new Data( LocalDate.of(2015, Month.JANUARY, 16), 10.0, 20.0, 30.0),
                    new Data( LocalDate.of(2015, Month.JANUARY, 17), 40.0, 50.0, 60.0),
                    new Data( LocalDate.of(2015, Month.JANUARY, 18), 10.0, 20.0, 30.0),
                    new Data( LocalDate.of(2015, Month.JANUARY, 19), 40.0, 50.0, 60.0),
                    new Data( LocalDate.of(2015, Month.JANUARY, 20), 10.0, 20.0, 30.0),
                    new Data( LocalDate.of(2015, Month.JANUARY, 21), 40.0, 50.0, 60.0),
                    new Data( LocalDate.of(2015, Month.JANUARY, 22), 10.0, 20.0, 30.0),
                    new Data( LocalDate.of(2015, Month.JANUARY, 23), 40.0, 50.0, 60.0),
                    new Data( LocalDate.of(2015, Month.JANUARY, 24), 10.0, 20.0, 30.0),
                    new Data( LocalDate.of(2015, Month.JANUARY, 25), 40.0, 50.0, 60.0),
                    new Data( LocalDate.of(2015, Month.JANUARY, 26), 10.0, 20.0, 30.0),
                    new Data( LocalDate.of(2015, Month.JANUARY, 27), 40.0, 50.0, 60.0),
                    new Data( LocalDate.of(2015, Month.JANUARY, 28), 10.0, 20.0, 30.0),
                    new Data( LocalDate.of(2015, Month.JANUARY, 29), 40.0, 50.0, 60.0),
                    new Data( LocalDate.of(2015, Month.JANUARY, 30), 10.0, 20.0, 30.0)

            );

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new Group());

        stage.setTitle("Table View Sample");
        stage.setWidth(800);
        stage.setHeight(800);

        // setup table columns
        setupTableColumns( table1);

        // fill tables with data
        table1.setItems(data);
//        table1.setTableMenuButtonVisible(true);

        HBox hBox = new HBox();
        hBox.getChildren().addAll( table1);

        ((Group) scene.getRoot()).getChildren().addAll( hBox);

        stage.setScene(scene);
        stage.show();
    }

    private void setupTableColumns( TableView table) {


        TableColumn<Data, LocalDate> dateCol = new TableColumn<>("Date");
        dateCol.setPrefWidth(120);
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Data, Double> value1Col = new TableColumn<>("Value 1");
        value1Col.setPrefWidth(90);
        value1Col.setCellValueFactory(new PropertyValueFactory<>("value1"));

        TableColumn<Data, Double> value2Col = new TableColumn<>("Value 2");
        value2Col.setPrefWidth(90);
        value2Col.setCellValueFactory(new PropertyValueFactory<>("value2"));

        TableColumn<Data, Double> value3Col = new TableColumn<>("Value 3");
        value3Col.setPrefWidth(90);
        value3Col.setCellValueFactory(new PropertyValueFactory<>("value3"));

        table.getColumns().addAll( dateCol, value1Col, value2Col, value3Col);


    }

    public static class Data {

        private final ObjectProperty<LocalDate> date;
        private final SimpleDoubleProperty value1;
        private final SimpleDoubleProperty value2;
        private final SimpleDoubleProperty value3;

        public Data( LocalDate date, double value1, double value2, double value3) {

            this.date = new SimpleObjectProperty<LocalDate>( date);

            this.value1 = new SimpleDoubleProperty( value1);
            this.value2 = new SimpleDoubleProperty( value2);
            this.value3 = new SimpleDoubleProperty( value3);
        }

        public final ObjectProperty<LocalDate> dateProperty() {
            return this.date;
        }
        public final LocalDate getDate() {
            return this.dateProperty().get();
        }
        public final void setDate(final LocalDate date) {
            this.dateProperty().set(date);
        }
        public final SimpleDoubleProperty value1Property() {
            return this.value1;
        }
        public final double getValue1() {
            return this.value1Property().get();
        }
        public final void setValue1(final double value1) {
            this.value1Property().set(value1);
        }
        public final SimpleDoubleProperty value2Property() {
            return this.value2;
        }
        public final double getValue2() {
            return this.value2Property().get();
        }
        public final void setValue2(final double value2) {
            this.value2Property().set(value2);
        }
        public final SimpleDoubleProperty value3Property() {
            return this.value3;
        }
        public final double getValue3() {
            return this.value3Property().get();
        }
        public final void setValue3(final double value3) {
            this.value3Property().set(value3);
        }


    }
}
