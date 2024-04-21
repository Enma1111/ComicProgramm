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

import java.io.IOException;

public class BookController {
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnBackToOptions;
    @FXML
    private TextField txtDeleteID;
    @FXML
    private TextField txtSearch;
    @FXML
    private CheckBox ckBxSureDelete;
    @FXML
    private Button btnBookSave;
    @FXML
    private TextField txtPublisher;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField txtBox;
    @FXML
    private TextField txtBookName;
    @FXML
    private TableColumn<DataItem, String> colPlace;
    @FXML
    private TableColumn<DataItem, String> colBook;
    @FXML
    private TableColumn<DataItem, String> colPublisher;
    @FXML
    private TableColumn<DataItem, Integer> colID;
    @FXML
    private TableView<DataItem> tblBook;
    @FXML
    private Button btnBackToTable;

    String table = "Book_Table";
    NewScene newScene = new NewScene();
    XMLParser xmlParser = new XMLParser(table);
    SQLWriteQuery sqlWriteQuery = new SQLWriteQuery(table);
    TemporaryTable temporaryTable = new TemporaryTable(sqlWriteQuery);
    SearchEngine searchEngine = new SearchEngine();
    DataReadWrite dataReadWrite = new DataReadWrite(table, xmlParser,temporaryTable,searchEngine,sqlWriteQuery);
    DataXmlExtract dataXmlExtract = new DataXmlExtract();
    TableIInitiator tableIInitiator = new TableIInitiator(dataXmlExtract);
    WarningHelper warningHelper = new WarningHelper();

    @FXML
    public void initialize() {
        tableIInitiator.initialize(tblBook,table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));
    }

    @FXML
    public void saveBook(ActionEvent actionEvent) {
        String[] val = new String[3];
        val[0] = txtBookName.getText();
        val[1] = txtBox.getText();
        val[2] = txtPublisher.getText();

        dataReadWrite.dataWrite(sqlWriteQuery.saveQuery(val));
        tableIInitiator.initialize(tblBook,table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));
    }

    @FXML
    public void bookDelete(ActionEvent actionEvent) {
        String id = txtDeleteID.getText();
        if (ckBxSureDelete.isSelected()){
            dataReadWrite.dataDelete(sqlWriteQuery.deleteQuery(id));
            tableIInitiator.initialize(tblBook,table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));
            ckBxSureDelete.setSelected(false);
        }else{
            warningHelper.deleteWarning();
        }
    }

    @FXML
    public void bookSearch(ActionEvent actionEvent) {
    }

    @FXML
    public void backToOptions(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnBackToOptions.getScene().getWindow();
        try {
            newScene.newScene("option-view.fxml", stage, 200, 200, "Optionen");
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    @FXML
    public void backToMainTable(ActionEvent actionEvent) {
        tableIInitiator.initialize(tblBook,table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));
    }
}
