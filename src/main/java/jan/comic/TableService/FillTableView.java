package jan.comic.TableService;

import jan.comic.Data.DataItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FillTableView {

    private final TableView<DataItem> tableView;
    private static final Logger logger = LoggerFactory.getLogger(FillTableView.class);

    public FillTableView(TableView<DataItem> tableView) {
        this.tableView = tableView;
    }


    public void fillTableView(List<DataItem> dataItems) {
//        dataItems = new ArrayList<>();
//        dataItems.add(new DataItem("1","asdf", "fdsf","df","dsad","22","dsa" ));
        ObservableList<DataItem> items = FXCollections.observableArrayList(dataItems);
        if (tableView != null) {
            tableView.setItems(items);
        }else {
            logger.error("TableView instance is null!");
        }
    }
}
