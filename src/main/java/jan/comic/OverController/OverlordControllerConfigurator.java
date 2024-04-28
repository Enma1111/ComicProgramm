package jan.comic.OverController;

import jan.comic.Data.DataReadWrite;
import jan.comic.Helper.PreparedStatementHelper;
import jan.comic.Helper.ValueNullCheckHelper;
import jan.comic.Helper.WarningHelper;
import jan.comic.SQLServices.SQLWriteQuery;
import jan.comic.Scene.NewScene;
import jan.comic.Search.Search;
import jan.comic.TableService.TableIInitiator;
import jan.comic.XMLService.DataXmlExtract;
import jan.comic.XMLService.XMLParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OverlordControllerConfigurator {
    private final NewScene newScene;
    private final XMLParser xmlParser;
    private final PreparedStatementHelper preparedStatementHelper;
    private final ValueNullCheckHelper valueNullCheckHelper;
    private final SQLWriteQuery sqlWriteQuery;
    private final DataReadWrite dataReadWrite;
    private final DataXmlExtract dataXmlExtract;
    private final TableIInitiator tableIInitiator;
    private final WarningHelper warningHelper;
    private final Search search;

    @Autowired
    public OverlordControllerConfigurator(NewScene newScene, XMLParser xmlParser, PreparedStatementHelper preparedStatementHelper,
                                          ValueNullCheckHelper valueNullCheckHelper, SQLWriteQuery sqlWriteQuery,
                                          DataReadWrite dataReadWrite, DataXmlExtract dataXmlExtract, TableIInitiator tableIInitiator,
                                          WarningHelper warningHelper, Search search) {
        this.newScene = newScene;
        this.xmlParser = xmlParser;
        this.preparedStatementHelper = preparedStatementHelper;
        this.valueNullCheckHelper = valueNullCheckHelper;
        this.sqlWriteQuery = sqlWriteQuery;
        this.dataReadWrite = dataReadWrite;
        this.dataXmlExtract = dataXmlExtract;
        this.tableIInitiator = tableIInitiator;
        this.warningHelper = warningHelper;
        this.search = search;
    }

}
