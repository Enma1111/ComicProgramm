package jan.comic.OverController;

import jan.comic.Data.DataReadWrite;
import jan.comic.Helper.ValueNullCheckHelper;
import jan.comic.Helper.WarningHelper;
import jan.comic.SQLServices.SQLWriteQuery;
import jan.comic.Scene.NewScene;
import jan.comic.Search.Search;
import jan.comic.TableService.TableIInitiator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OverlordViewControllerConfigurator {

    private final String table;
    private final List<String> insertColumn;
    private final String optionViewName;
    private final String optionFXML;
    private final NewScene newScene;
    private final ValueNullCheckHelper valueNullCheckHelper;
    private final SQLWriteQuery sqlWriteQuery;
    private final DataReadWrite dataReadWrite;
    private final TableIInitiator tableIInitiator;
    private final WarningHelper warningHelper;
    private final Search search;

    @Autowired
    public OverlordViewControllerConfigurator(String table, List<String> insertColumn, String optionViewName,
                                              String optionFXML, NewScene newScene, ValueNullCheckHelper valueNullCheckHelper,
                                              SQLWriteQuery sqlWriteQuery, DataReadWrite dataReadWrite,
                                              TableIInitiator tableIInitiator, WarningHelper warningHelper, Search search) {
        this.table = table;
        this.insertColumn = insertColumn;
        this.optionViewName = optionViewName;
        this.optionFXML = optionFXML;
        this.newScene = newScene;
        this.valueNullCheckHelper = valueNullCheckHelper;
        this.sqlWriteQuery = sqlWriteQuery;
        this.dataReadWrite = dataReadWrite;
        this.tableIInitiator = tableIInitiator;
        this.warningHelper = warningHelper;
        this.search = search;
    }

}
