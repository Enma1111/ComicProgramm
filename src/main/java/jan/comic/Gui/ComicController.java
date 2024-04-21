package jan.comic.Gui;

import jan.comic.Data.DataItem;
import jan.comic.SQLServices.TemporaryTable;
import jan.comic.Service.NewScene;
import jan.comic.Service.SearchEngine;
import jan.comic.Service.WarningHelper;
import jan.comic.Service.XMLParser;
import jan.comic.TableService.TableIInitiator;
import jan.comic.Data.DataReadWrite;
import jan.comic.Data.DataXmlExtract;
import jan.comic.SQLServices.SQLWriteQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import java.io.IOException;

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

    public void setTblComic(TableView<DataItem> tblComic) {
        this.tblComic = tblComic;
    }

    String table = "Comic_Table";
    String searchTable = "TempTable";
    String searchColumn = "Comic";
    NewScene newScene = new NewScene();
    XMLParser xmlParser = new XMLParser(table);
    SQLWriteQuery sqlWriteQuery = new SQLWriteQuery(table);
    TemporaryTable temporaryTable = new TemporaryTable(sqlWriteQuery);
    SearchEngine searchEngine = new SearchEngine();
    DataReadWrite dataReadWrite = new DataReadWrite(table, xmlParser, temporaryTable,searchEngine,sqlWriteQuery);
    DataXmlExtract dataXmlExtract = new DataXmlExtract();
    TableIInitiator tableIInitiator = new TableIInitiator(dataXmlExtract);
    WarningHelper warningHelper = new WarningHelper();


    @FXML
    public void initialize() {
        tableIInitiator.initialize(tblComic,table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));
    }

    @FXML
    public void saveComic(ActionEvent actionEvent) {
        String[] val = new String[5];
        val[0] = txtComicName.getText();
        val[1] = txtNumber.getText();
        val[2] = txtPackaging.getText();
        val[3] = txtBox.getText();
        val[4] = txtPublisher.getText();

        tableIInitiator.initialize(tblComic,table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));
    }

    @FXML
    public void comicDelete(ActionEvent   actionEvent) {
        String id = txtDeleteID.getText();
        if (ckBxSureDelete.isSelected()){
            dataReadWrite.dataDelete(sqlWriteQuery.deleteQuery(id));
            tableIInitiator.initialize(tblComic,table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));
            ckBxSureDelete.setSelected(false);
        }else{
            warningHelper.deleteWarning();
        }

    }

    @FXML
    public void comicSearch(ActionEvent actionEvent) {

        final String[] currentTable = {table};

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {

            if(!newValue.isEmpty()){
                Document searchValueDoc;
                Document searchValue;


                if(currentTable[0].equals(searchTable)){
                    searchValue = dataReadWrite.dataReadSearch(sqlWriteQuery.searchQuery(newValue, searchColumn, searchTable),newValue,searchColumn);

                    currentTable[0] = searchTable;
                    tableIInitiator.initialize(tblComic,table,searchValue);

                }else {
                    searchValue =  dataReadWrite.dataRead(sqlWriteQuery.searchQuery(newValue, searchColumn, table));
                    tableIInitiator.initialize(tblComic,searchTable,searchValue);
                }
                tableIInitiator.initialize(tblComic,table,searchValue);
            }
        });
    }

    @FXML
    public void backToOptions(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnBackToOptions.getScene().getWindow();
        try {
            newScene.newScene("option-view.fxml", stage, 200, 200,"Optionen");
        } catch (IOException e) {
            throw new IOException(e);
        }
    }


    public void backToMainTable(ActionEvent actionEvent) {
        tableIInitiator.initialize(tblComic,table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));
    }
}
