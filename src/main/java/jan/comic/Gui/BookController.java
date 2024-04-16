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

public class BookController {
    @FXML
    private Button BtnDelete;
    @FXML
    private Button BtnBackToOptions;
    @FXML
    private TextField TxtDeleteID;
    @FXML
    private TextField TxtComicName;
    @FXML
    private TextField TxtNumber;
    @FXML
    private TextField TxtSearch;
    @FXML
    private TextField TxtPlace;
    @FXML
    private CheckBox CkBxSureDelete;
    @FXML
    private Button BtnBookSave;
    @FXML
    private TextField TxtPublischer;
    @FXML
    private Button BtnSearch;
    @FXML
    private TableColumn<FillTableView.DataItem, String> colPlace;
    @FXML
    private TableColumn<FillTableView.DataItem, String> colBook;
    @FXML
    private TableColumn<FillTableView.DataItem, String> colPublisher;
    @FXML
    private TableColumn<FillTableView.DataItem, Integer> colID;
    @FXML
    private TableView<FillTableView.DataItem> tblBook;


    String table = "Book_Table";
    NewScene newScene = new NewScene();
    XMLParser xmlParser = new XMLParser();
    DataReadWrite dataReadWrite = new DataReadWrite(xmlParser);
    DataXmlExtract dataXmlExtract = new DataXmlExtract();
    TableIInitiator tableIInitiator = new TableIInitiator(dataReadWrite, dataXmlExtract);


    @FXML
    public void initialize(){
        tableIInitiator.initialize(tblBook, table);
    }

    @FXML
    public void SaveBook(ActionEvent actionEvent) {
    }

    @FXML
    public void BookDelete(ActionEvent actionEvent) {
    }

    @FXML
    public void BookSearch(ActionEvent actionEvent) {
    }

    @FXML
    public void BackToOptions(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) BtnBackToOptions.getScene().getWindow();
        try {
            newScene.newScene("option-view.fxml", stage, 200, 200, "Optionen");
        } catch (IOException e) {
            throw new IOException(e);
        }
    }


}
