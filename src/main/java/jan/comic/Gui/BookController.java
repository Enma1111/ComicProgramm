package jan.comic.Gui;

import jan.comic.Data.DataItem;
import jan.comic.Helper.PreparedStatementHelper;
import jan.comic.Helper.ValueNullCheckHelper;
import jan.comic.Scene.NewScene;
import jan.comic.Helper.WarningHelper;
import jan.comic.Search.Search;
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

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    private String query;
    String table = "Book_Table";
    String searchColumn = "Buch";
    private final List<String> bookColumns = Arrays.asList("Buch","Ort","Verlag");
    NewScene newScene = new NewScene();
    XMLParser xmlParser = new XMLParser(table);
    PreparedStatementHelper preparedStatementHelper = new PreparedStatementHelper();
    ValueNullCheckHelper valueNullCheckHelper = new ValueNullCheckHelper();
    SQLWriteQuery sqlWriteQuery = new SQLWriteQuery(table,bookColumns,searchColumn);
    DataReadWrite dataReadWrite = new DataReadWrite(table, xmlParser,preparedStatementHelper);
    DataXmlExtract dataXmlExtract = new DataXmlExtract();
    TableIInitiator tableIInitiator = new TableIInitiator(dataXmlExtract);
    WarningHelper warningHelper = new WarningHelper();
    Search search = new Search(xmlParser,tableIInitiator,table);

    @FXML
    public void initialize() {
        tableIInitiator.initialize(tblBook,table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.isEmpty()) {
                logger.info(oldValue);
                query = sqlWriteQuery.searchQuery(newValue);
                search.performSearch(query,tblBook);
                logger.info(newValue);
            }
        });
    }

    @FXML
    public void saveBook(ActionEvent actionEvent) {
        String[] val = new String[3];
        val[0] = txtBookName.getText();
        val[1] = txtBox.getText();
        val[2] = txtPublisher.getText();

        valueNullCheckHelper.comicValueChecker(val);
        dataReadWrite.dataWrite(sqlWriteQuery.saveQuery(), val);

        query = sqlWriteQuery.readQuery(table);
        Document doc = dataReadWrite.dataRead(query);
        tableIInitiator.initialize(tblBook,table, doc);
    }

    @FXML
    public void bookDelete(ActionEvent actionEvent) {
        String id = txtDeleteID.getText();
        if (ckBxSureDelete.isSelected()){
            dataReadWrite.dataDelete(sqlWriteQuery.deleteQuery(),id);
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
