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


import java.io.IOException;

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
    private TextField TxtPublischer;
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
    NewScene newScene = new NewScene();
    XMLParser xmlParser = new XMLParser();
    DataReadWrite dataReadWrite = new DataReadWrite(xmlParser, table);
    DataXmlExtract dataXmlExtract = new DataXmlExtract();
    SQLWriteQuery sqlWriteQuery = new SQLWriteQuery(table);
    TableIInitiator tableIInitiator = new TableIInitiator(dataXmlExtract);
    WarningHelper warningHelper = new WarningHelper();
    TemporaryTable temporaryTable = new TemporaryTable(dataReadWrite, sqlWriteQuery, xmlParser);
    SearchEngine searchEngine = new SearchEngine();

    @FXML
    public void initialize(){
        tableIInitiator.initialize(tblComic, table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));
    }

    @FXML
    public void SaveComic(ActionEvent actionEvent) {
        String[] val = new String[5];
        val[0] = TxtComicName.getText();
        val[1] = TxtNumber.getText();
        val[2] = TxtPackaging.getText();
        val[3] = TxtBox.getText();
        val[4] = TxtPublischer.getText();

//        for (int i = 0; i < val.length; i++) {
//            if (val[i].isEmpty()) {
//                val[i] = "";
//            }
//        }

        dataReadWrite.dataWrite(sqlWriteQuery.saveQuery(val));
        tableIInitiator.initialize(tblComic, table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));
    }

    @FXML
    public void ComicDelete(ActionEvent actionEvent) {
        String id = TxtDeleteID.getText();
        if (CkBxSureDelete.isSelected()){
            dataReadWrite.dataDelete(sqlWriteQuery.deleteQuery(id));
            tableIInitiator.initialize(tblComic, table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));
            CkBxSureDelete.setSelected(false);
        }else{
            warningHelper.deleteWarning();
        }

    }

    @FXML
    public void ComicSearch(ActionEvent actionEvent) {
        TxtSearch.textProperty().addListener((observable, oldValue, newValue) -> {

        });
        String searchTerm = TxtSearch.getText();
        Document searchValue = dataReadWrite.dataRead(sqlWriteQuery.searchQuery(searchTerm, "Comic"));
        tableIInitiator.initialize(tblComic,table,searchValue);
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
    public void BackToMainTable(ActionEvent actionEvent) {
        tableIInitiator.initialize(tblComic, table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));
    }
}
