package jan.comic.Service;

import jan.comic.Data.DataReadWrite;
import jan.comic.Data.DataXmlExtract;
import javafx.scene.control.TableView;
import org.w3c.dom.Document;

import java.util.List;

public class TableIInitiator {

    private final DataReadWrite dataReadWrite;
    private final DataXmlExtract dataXmlExtract;

    public TableIInitiator(DataReadWrite dataReadWrite, DataXmlExtract dataXmlExtract) {
        this.dataReadWrite = dataReadWrite;
        this.dataXmlExtract = dataXmlExtract;
    }

    public void initialize(TableView<FillTableView.DataItem> DataItemTable, String table) {
        Document document = dataReadWrite.DataRead(table);

        if (document != null) {
            List<FillTableView.DataItem> dataItems = dataXmlExtract.extractData(document,table);

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
