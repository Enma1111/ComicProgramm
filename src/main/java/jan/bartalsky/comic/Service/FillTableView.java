package jan.bartalsky.comic.Service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.TableView;
import java.util.List;

public class FillTableView {

    private TableView<DataItem> tableView;

    public FillTableView(TableView<DataItem> tableView) {
        this.tableView = tableView;
    }

    public static class DataItem{
        private final String value;

        public DataItem(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public void fillTableView(List<DataItem> dataItems) {
        ObservableList<DataItem> items = FXCollections.observableArrayList(dataItems);
        tableView.setItems(items);
    }
}
