package jan.comic.TableService;

import jan.comic.Data.DataReadWrite;
import jan.comic.Data.DataXmlExtract;
import jan.comic.SQLServices.SQLWriteQuery;
import javafx.scene.control.TableView;
import org.w3c.dom.Document;

import java.util.List;

public class TableIInitiator {

//    private final DataReadWrite dataReadWrite;
    private final DataXmlExtract dataXmlExtract;
//    private final SQLWriteQuery sqlWriteQuery;

    public TableIInitiator(DataXmlExtract dataXmlExtract) {
//        this.dataReadWrite = dataReadWrite;
        this.dataXmlExtract = dataXmlExtract;
//        this.sqlWriteQuery = sqlWriteQuery;
    }

    public void initialize(TableView<FillTableView.DataItem> DataItemTable, String table, Document doc) {
//        Document document = dataReadWrite.dataRead(sqlWriteQuery.readQuery());

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
