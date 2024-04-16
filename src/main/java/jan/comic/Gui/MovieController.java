package jan.comic.Gui;

import jan.comic.Data.DataReadWrite;
import jan.comic.Data.DataXmlExtract;
import jan.comic.Service.FillTableView;
import jan.comic.Service.NewScene;
import jan.comic.Service.TableIInitiator;
import jan.comic.Service.XMLParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.w3c.dom.Document;


import java.io.IOException;
import java.util.List;

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
    private TextField TxtNumber;
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

    String table = "Movie_Table";
    NewScene newScene = new NewScene();
    XMLParser xmlParser = new XMLParser();
    DataReadWrite dataReadWrite = new DataReadWrite(xmlParser);
    DataXmlExtract dataXmlExtract = new DataXmlExtract();
    TableIInitiator tableIInitiator = new TableIInitiator(dataReadWrite, dataXmlExtract);


    @FXML
    public void initialize(){
        tableIInitiator.initialize(tblMovie, table);
    }

    @FXML
    public void MovieSearch(ActionEvent actionEvent) {
    }

    @FXML
    public void MovieDelete(ActionEvent actionEvent) {
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
}
