package jan.comic.TableConfigurator;

import jan.comic.Data.DataItem;
import jan.comic.Data.DataReadWrite;
import jan.comic.Helper.ValueNullCheckHelper;
import jan.comic.Helper.WarningHelper;
import jan.comic.OverController.OverlordViewControllerConfigurator;
import jan.comic.SQLServices.SQLWriteQuery;
import jan.comic.Scene.NewScene;
import jan.comic.Search.Search;
import jan.comic.TableService.TableIInitiator;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import java.io.IOException;
import java.util.List;
@Component
public class MovieViewConfigurator extends OverlordViewControllerConfigurator {

    private static final Logger logger = LoggerFactory.getLogger(MovieViewConfigurator.class);

    private String table;
    private List<String> insertColumn;
    private String optionViewName;
    private String optionFXML;
    private NewScene newScene;
    private ValueNullCheckHelper valueNullCheckHelper;
    private SQLWriteQuery sqlWriteQuery;
    private DataReadWrite dataReadWrite;
    private TableIInitiator tableIInitiator;
    private WarningHelper warningHelper;
    private Search search;

    public MovieViewConfigurator(String table, List<String> insertColumn, String optionViewName,
                                 String optionFXML, NewScene newScene, ValueNullCheckHelper valueNullCheckHelper,
                                 SQLWriteQuery sqlWriteQuery, DataReadWrite dataReadWrite,
                                 TableIInitiator tableIInitiator, WarningHelper warningHelper, Search search) {
        super(table, insertColumn, optionViewName, optionFXML, newScene, valueNullCheckHelper,
                sqlWriteQuery, dataReadWrite, tableIInitiator, warningHelper, search);

    }

    public void movieViewInitialize(TableView<DataItem> tblView, @NotNull TextField txtSearch){
        tableIInitiator.initialize(tblView,table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.isEmpty()) {
                logger.info(oldValue);
                String query = sqlWriteQuery.searchQuery(newValue);
                search.performSearch(query,tblView);
                logger.info(newValue);
            }
        });
    }

    public void movieSafe(@NotNull TextField txtMovieName, @NotNull TextField txtMainActor, @NotNull TextField txtBox,
                          @NotNull TextField txtDistributor, @NotNull TextField txtFormat, TableView<DataItem> tblMovie) {

        String[] val =new String[5];
        val[0] = txtMovieName.getText();
        val[1] = txtMainActor.getText();
        val[2] = txtBox.getText();
        val[3] = txtDistributor.getText();
        val[4] = txtFormat.getText();

        valueNullCheckHelper.comicValueChecker(val);
        dataReadWrite.dataWrite(sqlWriteQuery.saveQuery(insertColumn), val);

        String query = sqlWriteQuery.readQuery(table);
        Document doc = dataReadWrite.dataRead(query);
        tableIInitiator.initialize(tblMovie,table, doc);
    }

    public void movieDelete(@NotNull TextField txtDeleteID, @NotNull CheckBox ckBxSureDelete, TableView<DataItem> tblMovie) {

        String id = txtDeleteID.getText();
        if (ckBxSureDelete.isSelected()){
            dataReadWrite.dataDelete(sqlWriteQuery.deleteQuery(),id);
            tableIInitiator.initialize(tblMovie,table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));
            ckBxSureDelete.setSelected(false);
        }else{
            warningHelper.deleteWarning();
        }
    }

    public void movieSafe(){

    }

    public void backToOptions(@NotNull Button btnBackToOptions) throws IOException {
        Stage stage = (Stage) btnBackToOptions.getScene().getWindow();
        try {
            newScene.newScene(optionFXML, stage, 200, 200, optionViewName);
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    public void backToMainTable(TableView<DataItem> tblMovie) {
        tableIInitiator.initialize(tblMovie,table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));
    }
}
