package jan.comic.TableService;

import jan.comic.Data.DataReadWrite;
import jan.comic.Data.DataXmlExtract;
import jan.comic.SQLServices.SQLWriteQuery;
import javafx.scene.control.TableView;
import org.w3c.dom.Document;

import java.util.List;

public class TableIInitiator {

    private final DataXmlExtract dataXmlExtract;

    public TableIInitiator(DataXmlExtract dataXmlExtract) {
        this.dataXmlExtract = dataXmlExtract;
    }

    public void initialize(TableView<FillTableView.DataItem> DataItemTable, String table, Document doc) {

        if (doc != null) {
            List<FillTableView.DataItem> dataItems = dataXmlExtract.extractData(doc,table);

            if (!dataItems.isEmpty()) {

                FillTableView fillTableView = new FillTableView(DataItemTable);
                fillTableView.fillTableView(dataItems);

            } else {
                System.err.println("DataItems list is empty!");
            }
        } else {
            System.err.println("Document is null!");
        }
    }
}
