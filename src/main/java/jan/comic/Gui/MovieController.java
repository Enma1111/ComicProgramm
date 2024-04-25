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

public class MovieController {
    @FXML
    private TableColumn<DataItem, String> colPlace;
    @FXML
    private TableColumn<DataItem, String> colDoubleMovieIn;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnBackToOptions;
    @FXML
    private TextField txtDeleteID;
    @FXML
    private TableColumn<DataItem, String>  colDistributor;
    @FXML
    private TableColumn<DataItem, String>  colMovie;
    @FXML
    private TextField txtSearch;
    @FXML
    private TextField txtDistributor;
    @FXML
    private CheckBox ckBxSureDelete;
    @FXML
    private TextField txtBox;
    @FXML
    private Button btnSearch;
    @FXML
    private TableColumn<DataItem, Integer>  colID;
    @FXML
    private TableColumn<DataItem, String> colMainActor;
    @FXML
    private TableView<DataItem> tblMovie;
    @FXML
    private TableColumn<DataItem, String> colFormat;
    @FXML
    private Button btnMovieSave;
    @FXML
    private TextField txtMovieName;
    @FXML
    private TextField txtMainActor;
    @FXML
    private TextField txtFormat;
    @FXML
    private Button btnBackToTable;

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
    private String query;
    String table = "Movie_Table";
    String searchColumn = "Film";
    private final List<String> movieColumns = Arrays.asList("ID","Film","Hauptdarsteller","Ort","Vertrieb","Format","Doppelt");
    private final List<String> insertMovieColumns = Arrays.asList("Film","Hauptdarsteller","Ort","Vertrieb","Format");
    NewScene newScene = new NewScene();
    XMLParser xmlParser = new XMLParser(table);
    PreparedStatementHelper preparedStatementHelper = new PreparedStatementHelper();
    ValueNullCheckHelper valueNullCheckHelper = new ValueNullCheckHelper();
    SQLWriteQuery sqlWriteQuery = new SQLWriteQuery(table,movieColumns,searchColumn);
    DataReadWrite dataReadWrite = new DataReadWrite(table, xmlParser,preparedStatementHelper);
    DataXmlExtract dataXmlExtract = new DataXmlExtract();
    TableIInitiator tableIInitiator = new TableIInitiator(dataXmlExtract);
    WarningHelper warningHelper = new WarningHelper();
    Search search = new Search(xmlParser,tableIInitiator,table);

    @FXML
    public void initialize() {
        tableIInitiator.initialize(tblMovie,table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.isEmpty()) {
                logger.info(oldValue);
                query = sqlWriteQuery.searchQuery(newValue);
                search.performSearch(query,tblMovie);
                logger.info(newValue);
            }
        });
    }

    @FXML
    public void movieSearch(ActionEvent actionEvent) {
    }

    @FXML
    public void movieDelete(ActionEvent actionEvent) {
        String id = txtDeleteID.getText();
        if (ckBxSureDelete.isSelected()){
            dataReadWrite.dataDelete(sqlWriteQuery.deleteQuery(),id);
            tableIInitiator.initialize(tblMovie,table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));
            ckBxSureDelete.setSelected(false);
        }else{
            warningHelper.deleteWarning();
        }
    }

    @FXML
    public void saveMovie(ActionEvent actionEvent) {
        String[] val =new String[5];
        val[0] = txtMovieName.getText();
        val[1] = txtMainActor.getText();
        val[2] = txtBox.getText();
        val[3] = txtDistributor.getText();
        val[4] = txtFormat.getText();

        valueNullCheckHelper.comicValueChecker(val);
        dataReadWrite.dataWrite(sqlWriteQuery.saveQuery(insertMovieColumns), val);

        query = sqlWriteQuery.readQuery(table);
        Document doc = dataReadWrite.dataRead(query);
        tableIInitiator.initialize(tblMovie,table, doc);
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

    @FXML
    public void backToMainTable(ActionEvent actionEvent) {
        tableIInitiator.initialize(tblMovie,table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));
    }
}
