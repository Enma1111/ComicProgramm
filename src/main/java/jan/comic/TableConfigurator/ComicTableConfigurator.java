package jan.comic.TableConfigurator;

import jan.comic.Data.DataReadWrite;
import jan.comic.Helper.PreparedStatementHelper;
import jan.comic.Helper.ValueNullCheckHelper;
import jan.comic.Helper.WarningHelper;
import jan.comic.OverController.OverlordControllerConfigurator;
import jan.comic.SQLServices.SQLWriteQuery;
import jan.comic.Scene.NewScene;
import jan.comic.Search.Search;
import jan.comic.TableService.TableIInitiator;
import jan.comic.XMLService.DataXmlExtract;
import jan.comic.XMLService.XMLParser;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ComicTableConfigurator extends OverlordControllerConfigurator {

    public ComicTableConfigurator(NewScene newScene, XMLParser xmlParser,
                                  PreparedStatementHelper preparedStatementHelper, ValueNullCheckHelper valueNullCheckHelper,
                                  SQLWriteQuery sqlWriteQuery, DataReadWrite dataReadWrite, DataXmlExtract dataXmlExtract,
                                  TableIInitiator tableIInitiator, WarningHelper warningHelper, Search search) {
        super(newScene, xmlParser, preparedStatementHelper,valueNullCheckHelper,sqlWriteQuery,dataReadWrite,dataXmlExtract,tableIInitiator,warningHelper,search);
    }

}