package jan.comic.Gui;

import jan.comic.Data.DataItem;
import jan.comic.Service.NewScene;
import jan.comic.Service.SearchEngine;
import jan.comic.Service.WarningHelper;
import jan.comic.Service.XMLParser;
import jan.comic.TableService.TableIInitiator;
import jan.comic.Data.DataReadWrite;
import jan.comic.Data.DataXmlExtract;
import jan.comic.SQLServices.SQLWriteQuery;
import jan.comic.SQLServices.TemporaryTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

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

    String table = "Movie_Table";
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
        tableIInitiator.initialize(tblMovie,table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));
    }

    @FXML
    public void movieSearch(ActionEvent actionEvent) {
        String[] val = new String[5];
        val[0] = txtMovieName.getText();
        val[1] = txtMainActor.getText();
        val[2] = txtBox.getText();
        val[3] = txtDistributor.getText();
        val[4] = txtFormat.getText();

        dataReadWrite.dataWrite(sqlWriteQuery.saveQuery(val));
        tableIInitiator.initialize(tblMovie,table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));
    }

    @FXML
    public void movieDelete(ActionEvent actionEvent) {
        String id = txtDeleteID.getText();
        if (ckBxSureDelete.isSelected()){
            dataReadWrite.dataDelete(sqlWriteQuery.deleteQuery(id));
            tableIInitiator.initialize(tblMovie,table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));
            ckBxSureDelete.setSelected(false);
        }else{
            warningHelper.deleteWarning();
        }
    }

    @FXML
    public void saveMovie(ActionEvent actionEvent) {
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
