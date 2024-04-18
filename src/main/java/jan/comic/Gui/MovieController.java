package jan.comic.Gui;

import jan.comic.Data.DataReadWrite;
import jan.comic.Data.DataXmlExtract;
import jan.comic.SQLServices.SQLWriteQuery;
import jan.comic.Service.*;
import jan.comic.TableService.FillTableView;
import jan.comic.TableService.TableIInitiator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.IOException;

public class MovieController {
    @FXML
    private TableColumn<FillTableView.DataItem, String>  ColPlace;
    @FXML
    private TableColumn<FillTableView.DataItem, String> ColDoubleMovieIn;
    @FXML
    private Button BtnDelete;
    @FXML
    private Button BtnBackToOptions;
    @FXML
    private TextField TxtDeleteID;
    @FXML
    private TableColumn<FillTableView.DataItem, String>  ColDistributor;
    @FXML
    private TableColumn<FillTableView.DataItem, String>  ColMovie;
    @FXML
    private TextField TxtSearch;
    @FXML
    private TextField TxtDistributor;
    @FXML
    private CheckBox CkBxSureDelete;
    @FXML
    private TextField TxtBox;
    @FXML
    private Button BtnSearch;
    @FXML
    private TableColumn<FillTableView.DataItem, Integer>  ColID;
    @FXML
    private TableColumn<FillTableView.DataItem, String> colMainActor;
    @FXML
    private TableView<FillTableView.DataItem> tblMovie;
    @FXML
    private TableColumn<FillTableView.DataItem, String> colFormat;
    @FXML
    private Button BtnMovieSave;
    @FXML
    private TextField TxtMovieName;
    @FXML
    private TextField TxtMainActor;
    @FXML
    private TextField TxtFormat;

    String table = "Movie_Table";
    NewScene newScene = new NewScene();
    XMLParser xmlParser = new XMLParser();
    DataReadWrite dataReadWrite = new DataReadWrite(xmlParser,table);
    DataXmlExtract dataXmlExtract = new DataXmlExtract();
    SQLWriteQuery sqlWriteQuery = new SQLWriteQuery(table);
    TableIInitiator tableIInitiator = new TableIInitiator(dataXmlExtract);
    WarningHelper warningHelper = new WarningHelper();
    @FXML
    private Button BtnBackToTable;

    @FXML
    public void initialize(){
        tableIInitiator.initialize(tblMovie, table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));
    }

    @FXML
    public void MovieSearch(ActionEvent actionEvent) {
        String[] val = new String[5];
        val[0] = TxtMovieName.getText();
        val[1] = TxtMainActor.getText();
        val[2] = TxtBox.getText();
        val[3] = TxtDistributor.getText();
        val[4] = TxtFormat.getText();

//        for (int i = 0; i < val.length; i++) {
//            if (val[i].isEmpty()) {
//                val[i] = "";
//            }
//        }

        dataReadWrite.dataWrite(sqlWriteQuery.saveQuery(val));
        tableIInitiator.initialize(tblMovie, table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));
    }

    @FXML
    public void MovieDelete(ActionEvent actionEvent) {
        String id = TxtDeleteID.getText();
        if (CkBxSureDelete.isSelected()){
            dataReadWrite.dataDelete(sqlWriteQuery.deleteQuery(id));
            tableIInitiator.initialize(tblMovie, table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));
            CkBxSureDelete.setSelected(false);
        }else{
            warningHelper.deleteWarning();
        }
    }

    @FXML
    public void SaveMovie(ActionEvent actionEvent) {
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
        tableIInitiator.initialize(tblMovie, table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));
    }
}
