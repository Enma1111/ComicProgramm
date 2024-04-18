package jan.comic.Gui;

import jan.comic.Data.DataReadWrite;
import jan.comic.Data.DataXmlExtract;
import jan.comic.SQLServices.SQLWriteQuery;
import jan.comic.SQLServices.TemporaryTable;
import jan.comic.Service.*;
import jan.comic.TableService.FillTableView;
import jan.comic.TableService.TableIInitiator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.w3c.dom.Document;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ComicController {
    @FXML
    private Button BtnDelete;
    @FXML
    private TableColumn <FillTableView.DataItem, String>  ColNumber;
    @FXML
    private TableColumn <FillTableView.DataItem, String>  ColDoubleComicIn;
    @FXML
    private TextField TxtDeleteID;
    @FXML
    private TableColumn <FillTableView.DataItem, String>  ColBox;
    @FXML
    private TableColumn <FillTableView.DataItem, String>  ColComic;
    @FXML
    private Button BtnComicSave;
    @FXML
    private TextField TxtComicName;
    @FXML
    private TextField TxtSearch;
    @FXML
    private CheckBox CkBxSureDelete;
    @FXML
    private Button BtnSearch;
    @FXML
    private TableColumn <FillTableView.DataItem, String>  ColVerlag;
    @FXML
    private TableColumn <FillTableView.DataItem, Integer> ColID;
    @FXML
    private TextField TxtNumber;
    @FXML
    private TextField TxtPublisher;
    @FXML
    private TextField TxtBox;
    @FXML
    private Button BtnBackToOptions;
    @FXML
    private TableView<FillTableView.DataItem> tblComic;
    @FXML
    private TableColumn <FillTableView.DataItem, String> ColPackaging;
    @FXML
    private TextField TxtPackaging;
    @FXML
    private Button BtnBackToTable;

    public void setTblComic(TableView<FillTableView.DataItem> tblComic) {
        this.tblComic = tblComic;
    }

    String table = "Comic_Table";
    String searchTable = "TempTable";
    String searchColumn = "Comic";
    NewScene newScene = new NewScene();
    XMLParser xmlParser = new XMLParser();
    DataReadWrite dataReadWrite = new DataReadWrite(xmlParser, table);
    DataXmlExtract dataXmlExtract = new DataXmlExtract();
    SQLWriteQuery sqlWriteQuery = new SQLWriteQuery(table);
    TableIInitiator tableIInitiator = new TableIInitiator(dataXmlExtract);
    WarningHelper warningHelper = new WarningHelper();
    TemporaryTable temporaryTable = new TemporaryTable(sqlWriteQuery);
    SearchEngine searchEngine = new SearchEngine();

    @FXML
    public void initialize() throws SQLException, ParserConfigurationException, TransformerException {
        tableIInitiator.initialize(tblComic, table, xmlParser.createXml(table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table))));
    }

    @FXML
    public void SaveComic(ActionEvent actionEvent) throws SQLException, ParserConfigurationException, TransformerException {
        String[] val = new String[5];
        val[0] = TxtComicName.getText();
        val[1] = TxtNumber.getText();
        val[2] = TxtPackaging.getText();
        val[3] = TxtBox.getText();
        val[4] = TxtPublisher.getText();

//        for (int i = 0; i < val.length; i++) {
//            if (val[i].isEmpty()) {
//                val[i] = "";
//            }
//        }

        dataReadWrite.dataWrite(sqlWriteQuery.saveQuery(val));
        tableIInitiator.initialize(tblComic, table, xmlParser.createXml(table,dataReadWrite.dataRead(sqlWriteQuery.readQuery(table))));
    }

    @FXML
    public void ComicDelete(ActionEvent actionEvent) throws SQLException, ParserConfigurationException, TransformerException {
        String id = TxtDeleteID.getText();
        if (CkBxSureDelete.isSelected()){
            dataReadWrite.dataDelete(sqlWriteQuery.deleteQuery(id));
            tableIInitiator.initialize(tblComic, table, xmlParser.createXml(table,dataReadWrite.dataRead(sqlWriteQuery.readQuery(table))));
            CkBxSureDelete.setSelected(false);
        }else{
            warningHelper.deleteWarning();
        }

    }

    @FXML
    public void ComicSearch(ActionEvent actionEvent) {

        final String[] currentTable = {table};

        TxtSearch.textProperty().addListener((observable, oldValue, newValue) -> {

            if(!newValue.isEmpty()){
                String searchTerm = TxtSearch.getText();
                Document searchValueDoc;
                ResultSet searchValue;


                try {
                    if(!currentTable[0].equals(searchTable)){
                        searchValue = dataReadWrite.dataRead(sqlWriteQuery.searchQuery(searchTerm, searchColumn, table));
                        currentTable[0] = searchTable;
                    }else {
                        searchValue = dataReadWrite.dataRead(sqlWriteQuery.searchQuery(searchTerm, searchColumn, searchTable));
                    }

                    temporaryTable.createTemporaryTable(table, searchEngine.search(searchValue, table,searchTerm));
                    searchValueDoc = xmlParser.createXml(table,searchValue);
                    tableIInitiator.initialize(tblComic,table,searchValueDoc);
                } catch (ParserConfigurationException | SQLException | TransformerException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @FXML
    public void BackToOptions(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) BtnBackToOptions.getScene().getWindow();
        try {
            newScene.newScene("option-view.fxml", stage, 200, 200,"Optionen");
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    @FXML
    public void BackToMainTable(ActionEvent actionEvent) throws SQLException, ParserConfigurationException, TransformerException {
        tableIInitiator.initialize(tblComic, table, xmlParser.createXml(table,dataReadWrite.dataRead(sqlWriteQuery.readQuery(table))));
    }
}
