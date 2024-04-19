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
    private TableColumn <FillTableView.DataItem, String>  colNumber;
    @FXML
    private TableColumn <FillTableView.DataItem, String>  colDoubleComicIn;
    @FXML
    private TableColumn <FillTableView.DataItem, String>  colBox;
    @FXML
    private TableColumn <FillTableView.DataItem, String>  colComic;
    @FXML
    private TableColumn <FillTableView.DataItem, String>  colVerlag;
    @FXML
    private TableColumn <FillTableView.DataItem, Integer> colID;
    @FXML
    private TableView<FillTableView.DataItem> tblComic;
    @FXML
    private TableColumn <FillTableView.DataItem, String> colPackaging;
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

    public void setTblComic(TableView<FillTableView.DataItem> tblComic) {
        this.tblComic = tblComic;
    }

    String table = "Comic_Table";
    String searchTable = "TempTable";
    String searchColumn = "Comic";
    NewScene newScene = new NewScene();
    XMLParser xmlParser = new XMLParser(table);
    DataReadWrite dataReadWrite = new DataReadWrite(table);
    DataXmlExtract dataXmlExtract = new DataXmlExtract();
    SQLWriteQuery sqlWriteQuery = new SQLWriteQuery(table);
    TableIInitiator tableIInitiator = new TableIInitiator(dataXmlExtract);
    WarningHelper warningHelper = new WarningHelper();
    TemporaryTable temporaryTable = new TemporaryTable(sqlWriteQuery);
    SearchEngine searchEngine = new SearchEngine();

    @FXML
    public void initialize() throws SQLException, ParserConfigurationException, TransformerException {
        String query = sqlWriteQuery.readQuery(table);
        ResultSet result = dataReadWrite.dataRead(query);
        Document doc = xmlParser.createXml(result);
        tableIInitiator.initialize(tblComic, table, doc);
    }

    @FXML
    public void saveComic(ActionEvent actionEvent) throws SQLException, ParserConfigurationException, TransformerException {
        String[] val = new String[5];
        val[0] = txtComicName.getText();
        val[1] = txtNumber.getText();
        val[2] = txtPackaging.getText();
        val[3] = txtBox.getText();
        val[4] = txtPublisher.getText();

//        for (int i = 0; i < val.length; i++) {
//            if (val[i].isEmpty()) {
//                val[i] = "";
//            }
//        }

        dataReadWrite.dataWrite(sqlWriteQuery.saveQuery(val));
        Document doc = xmlParser.createXml(dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));
        tableIInitiator.initialize(tblComic, table, doc);
    }

    @FXML
    public void comicDelete(ActionEvent actionEvent) throws SQLException, ParserConfigurationException, TransformerException {
        String id = txtDeleteID.getText();
        if (ckBxSureDelete.isSelected()){
            dataReadWrite.dataDelete(sqlWriteQuery.deleteQuery(id));
            Document doc = xmlParser.createXml( dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));
            tableIInitiator.initialize(tblComic, table, doc);
            ckBxSureDelete.setSelected(false);
        }else{
            warningHelper.deleteWarning();
        }

    }

    @FXML
    public void comicSearch(ActionEvent actionEvent) {

//        final String[] currentTable = {table};
//
//        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
//
//            if(!newValue.isEmpty()){
//                String searchTerm = txtSearch.getText();
//                Document searchValueDoc;
//                ResultSet searchValue;
//
//
//                try {
//                    if(!currentTable[0].equals(searchTable)){
//                        searchValue = dataReadWrite.dataRead(sqlWriteQuery.searchQuery(searchTerm, searchColumn, table));
//                        currentTable[0] = searchTable;
//                    }else {
//                        searchValue = dataReadWrite.dataRead(sqlWriteQuery.searchQuery(searchTerm, searchColumn, searchTable));
//                    }
//
//                    temporaryTable.createTemporaryTable(table, searchEngine.search(searchValue, table,searchTerm));
//                    searchValueDoc = xmlParser.createXml(searchValue);
//                    tableIInitiator.initialize(tblComic,table,searchValueDoc);
//                } catch (ParserConfigurationException | SQLException | TransformerException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
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


    public void backToMainTable(ActionEvent actionEvent) throws SQLException, ParserConfigurationException, TransformerException {
        Document doc = xmlParser.createXml(dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));
        tableIInitiator.initialize(tblComic, table, doc);
    }
}
