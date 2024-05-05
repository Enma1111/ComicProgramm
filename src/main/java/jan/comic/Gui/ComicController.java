package jan.comic.Gui;

import jan.comic.Data.DataItem;
import jan.comic.Helper.PreparedStatementHelper;
import jan.comic.Helper.ValueNullCheckHelper;
import jan.comic.Search.Search;
import jan.comic.Scene.NewScene;
import jan.comic.Helper.WarningHelper;
import jan.comic.XMLService.XMLParser;
import jan.comic.TableService.TableIInitiator;
import jan.comic.Data.DataReadWrite;
import jan.comic.XMLService.DataXmlExtract;
import jan.comic.SQLServices.SQLWriteQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ComicController {
    @FXML
    private TableColumn <DataItem, String>  colNumber;
    @FXML
    private TableColumn <DataItem, String>  colDoubleComicIn;
    @FXML
    private TableColumn <DataItem, String>  colBox;
    @FXML
    private TableColumn <DataItem, String>  colComic;
    @FXML
    private TableColumn <DataItem, String>  colVerlag;
    @FXML
    private TableColumn <DataItem, Integer> colID;
    @FXML
    private TableView<DataItem> tblComic;
    @FXML
    private TableColumn <DataItem, String> colPackaging;
    @FXML
    private TextField txtPackaging;
    @FXML
    private Button btnBackToOptions;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField txtNumber;
    @FXML
    private TextField txtComicName;
    @FXML
    private Button btnBackToTable;
    @FXML
    private TextField txtDeleteID;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnComicSave;
    @FXML
    private CheckBox ckBxSureDelete;
    @FXML
    private TextField txtBox;
    @FXML
    private TextField txtSearch;
    @FXML
    private TextField txtPublisher;

    private static final Logger logger = LoggerFactory.getLogger(ComicController.class);
    public void setTblComic(TableView<DataItem> tblComic) {
        this.tblComic = tblComic;
    }


    private String query;
    String table = "Comic_Table";
    String searchColumn = "Comic";
    String optionViewName = "Optionen";
    String optionFXML = "option-view.fxml";
    private final List<String> comicColumns = Arrays.asList("ID","Comic", "Nummer", "Verpackung", "Kiste", "Verlag","Doppelt");
    private final List<String> insertComicColumns = Arrays.asList("Comic", "Nummer", "Verpackung", "Kiste", "Verlag");
    NewScene newScene = new NewScene();
    XMLParser xmlParser = new XMLParser(table);
    PreparedStatementHelper preparedStatementHelper = new PreparedStatementHelper();
    ValueNullCheckHelper valueNullCheckHelper = new ValueNullCheckHelper();
    SQLWriteQuery sqlWriteQuery = new SQLWriteQuery(table, comicColumns,searchColumn);
    DataReadWrite dataReadWrite = new DataReadWrite(table, xmlParser,preparedStatementHelper);
    DataXmlExtract dataXmlExtract = new DataXmlExtract();
    TableIInitiator tableIInitiator = new TableIInitiator(dataXmlExtract);
    WarningHelper warningHelper = new WarningHelper();
    Search search = new Search(xmlParser,tableIInitiator,table);



    @FXML
    public void initialize() {
//        comicTableConfigurator.comicViewInitialize(tblComic,txtSearch);
        tableIInitiator.initialize(tblComic,table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.isEmpty()) {
                logger.info(oldValue);
                query = sqlWriteQuery.searchQuery(newValue);
                search.performSearch(query,tblComic);
                logger.info(newValue);
            }
        });
    }

    @FXML
    public void saveComic(ActionEvent actionEvent) {
//        comicTableConfigurator.comicSafe(txtComicName,txtNumber,txtPackaging,txtBox,txtPublisher,tblComic);
        String[] val = new String[5];
        val[0] = txtComicName.getText();
        val[1] = txtNumber.getText();
        val[2] = txtPackaging.getText();
        val[3] = txtBox.getText();
        val[4] = txtPublisher.getText();

        valueNullCheckHelper.comicValueChecker(val);
        dataReadWrite.dataWrite(sqlWriteQuery.saveQuery(insertComicColumns), val);

        query = sqlWriteQuery.readQuery(table);
        Document doc = dataReadWrite.dataRead(query);
        tableIInitiator.initialize(tblComic,table, doc);
    }

    @FXML
    public void comicDelete(ActionEvent   actionEvent) {
//        comicTableConfigurator.comicDelete(txtDeleteID,ckBxSureDelete,tblComic);
        String id = txtDeleteID.getText();
        if (ckBxSureDelete.isSelected()){
            dataReadWrite.dataDelete(sqlWriteQuery.deleteQuery(),id);
            tableIInitiator.initialize(tblComic,table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));
            ckBxSureDelete.setSelected(false);
        }else{
            warningHelper.deleteWarning();
        }

    }

    @FXML
    public void comicSearch(ActionEvent actionEvent) {
//        if (btnSearch)
//        logger.info(oldValue);
//        query = sqlWriteQuery.searchQuery(newValue);
//        search.performSearch(query,tblComic);
//        logger.info(newValue);
    }

    @FXML
    public void backToOptions(ActionEvent actionEvent) throws IOException {
//        comicTableConfigurator.backToOptions(btnBackToOptions);
        Stage stage = (Stage) btnBackToOptions.getScene().getWindow();
        try {
            newScene.newScene(optionFXML, stage, 200, 200, optionViewName);
        } catch (IOException e) {
            throw new IOException(e);
        }
    }


    public void backToMainTable(ActionEvent actionEvent) {
//        comicTableConfigurator.backToMainTable(tblComic);
        tableIInitiator.initialize(tblComic,table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));
    }
}
