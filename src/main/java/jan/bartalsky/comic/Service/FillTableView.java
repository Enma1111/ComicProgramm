package jan.bartalsky.comic.Service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import java.util.List;

public class FillTableView {

    private final TableView<DataItem> tableView;

    public FillTableView(TableView<DataItem> tableView) {
        this.tableView = tableView;
    }

    public static class DataItem{
        private final String id;
        private final String comicName;
        private final String number;
        private final String packaging;
        private final String box;
        private final String doubleComicIn;
        private final String publisher;
        private final String movieName;
        private final String distributer;
        private final String format;
        private final String bookName;

        public DataItem(String id, String comicName, String number, String packaging, String box, String doubleComicIn, String publisher, String movieName, String distributer, String format, String bookName) {
            this.id = id;
            this.comicName = comicName;
            this.number = number;
            this.packaging = packaging;
            this.box = box;
            this.doubleComicIn = doubleComicIn;
            this.publisher = publisher;
            this.movieName = movieName;
            this.distributer = distributer;
            this.format = format;
            this.bookName = bookName;
        }
    }

    public void fillTableView(List<DataItem> dataItems) {
//        dataItems = new ArrayList<>();
//        dataItems.add(new DataItem("1","asdf", "fdsf","df","dsad","22","dsa" ));
        ObservableList<DataItem> items = FXCollections.observableArrayList(dataItems);
        if (tableView != null) {
            tableView.setItems(items);
        }else {
            System.err.println("TableView instance is null!");
        }
    }
}
