package jan.bartalsky.comic.Gui;

import jan.bartalsky.comic.Data.DataReadWrite;
import jan.bartalsky.comic.Data.DataXmlExtract;
import jan.bartalsky.comic.Service.FillTableView;
import jan.bartalsky.comic.Service.NewScene;
import jan.bartalsky.comic.Service.XMLParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.w3c.dom.Document;

import java.io.IOException;
import java.nio.Buffer;
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
    private Button BtnComicSave;
    @FXML
    private TextField TxtComicName;
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
    private TableColumn<FillTableView.DataItem, String> ColMainActor;
    @FXML
    private TableView<FillTableView.DataItem> tblMovie;

    public MovieController(TableView<FillTableView.DataItem> tblMovie) {
        this.tblMovie = tblMovie;
    }

    NewScene newScene = new NewScene();
    XMLParser xmlParser = new XMLParser();
    DataReadWrite dataReadWrite = new DataReadWrite(xmlParser);
    DataXmlExtract dataXmlExtract = new DataXmlExtract();
    String table = "Movie_Table";

    @FXML
    public void initialize(){
        Document document = dataReadWrite.DataRead(table);

        if (document != null) {
            List<FillTableView.DataItem> dataItems = dataXmlExtract.extractData(document,table);

            if (!dataItems.isEmpty()) {
                FillTableView fillTableView = new FillTableView(tblMovie);
                fillTableView.fillTableView(dataItems);


            } else {
                System.err.println("DataItems list is empty!");
            }
        } else {
            System.err.println("Document is null!");
        }
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
            newScene.newScene("option-view.fxml", stage, 200, 200);
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}
