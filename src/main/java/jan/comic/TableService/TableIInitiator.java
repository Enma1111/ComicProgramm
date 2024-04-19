package jan.comic.TableService;


import jan.comic.Data.DataXmlExtract;
import javafx.scene.control.TableView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import java.util.List;

public class TableIInitiator {

    private final DataXmlExtract dataXmlExtract;
    private static final Logger logger = LoggerFactory.getLogger(TableIInitiator.class);

    public TableIInitiator(DataXmlExtract dataXmlExtract) {
        this.dataXmlExtract = dataXmlExtract;
    }

    public void initialize(TableView<FillTableView.DataItem> dataItemTable, String table, Document doc) {

        if (doc != null) {
            List<FillTableView.DataItem> dataItems = dataXmlExtract.extractData(doc,table);

            if (!dataItems.isEmpty()) {

                FillTableView fillTableView = new FillTableView(dataItemTable);
                fillTableView.fillTableView(dataItems);

            } else {
                logger.error("DataItems list is empty!");
            }
        } else {
            logger.error("Document is null!");
        }
    }
}
