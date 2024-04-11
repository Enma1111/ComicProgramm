package jan.bartalsky.comic.Gui;

import jan.bartalsky.comic.Data.DataReadWrite;
import jan.bartalsky.comic.Data.DataXmlExtract;
import jan.bartalsky.comic.Service.FillTableView;
import jan.bartalsky.comic.Service.NewScene;
import jan.bartalsky.comic.Service.XMLParser;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.w3c.dom.Document;

import java.io.IOException;
import java.util.List;

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

    public void setTblComic(TableView<FillTableView.DataItem> tblComic) {
        this.tblComic = tblComic;
    }

    NewScene newScene = new NewScene();
    XMLParser xmlParser = new XMLParser();
    DataReadWrite dataReadWrite = new DataReadWrite(xmlParser);
    DataXmlExtract dataXmlExtract = new DataXmlExtract();

    @FXML
    public void initialize(){
        Document document = dataReadWrite.DataRead("Comic_Table");

        if (document != null) {
            List<FillTableView.DataItem> dataItems = dataXmlExtract.extractData(document);

            if (!dataItems.isEmpty()) {
                FillTableView fillTableView = new FillTableView(tblComic);
                fillTableView.fillTableView(dataItems);


            } else {
                System.err.println("DataItems list is empty!");
            }
        } else {
            System.err.println("Document is null!");
        }
    }

    @FXML
    public void SaveComic(ActionEvent actionEvent) {

    }

    @FXML
    public void ComicDelete(ActionEvent actionEvent) {
    }

    @FXML
    public void ComicSearch(ActionEvent actionEvent) {
    }

    @FXML
    public void BackToOptions(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) BtnBackToOptions.getScene().getWindow();
        try {
            newScene.newScene("option-view.fxml", stage, 320, 240);
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}
