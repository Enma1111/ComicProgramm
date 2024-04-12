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
import java.util.List;

public class BookController {
    @FXML
    private TableColumn<FillTableView.DataItem, String> ColPlace;
    @FXML
    private TableColumn<FillTableView.DataItem, String> ColDoubleBookIn;
    @FXML
    private Button BtnDelete;
    @FXML
    private TableColumn<FillTableView.DataItem, String> ColNumber;
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
    private TableColumn<FillTableView.DataItem, String> ColBook;
    @FXML
    private Button BtnBookSave;
    @FXML
    private TextField TxtPublischer;
    @FXML
    private Button BtnSearch;
    @FXML
    private TableColumn<FillTableView.DataItem, String> ColVerlag;
    @FXML
    private TableColumn<FillTableView.DataItem, Integer> ColID;
    @FXML
    private TableView<FillTableView.DataItem> tblBook;

    public BookController(TableView<FillTableView.DataItem> tblBook) {
        this.tblBook = tblBook;
    }

    NewScene newScene = new NewScene();

    XMLParser xmlParser = new XMLParser();
    DataReadWrite dataReadWrite = new DataReadWrite(xmlParser);
    DataXmlExtract dataXmlExtract = new DataXmlExtract();
    String table = "Book_Table";

    @FXML
    public void initialize(){
        Document document = dataReadWrite.DataRead(table);

        if (document != null) {
            List<FillTableView.DataItem> dataItems = dataXmlExtract.extractData(document, table);

            if (!dataItems.isEmpty()) {
                FillTableView fillTableView = new FillTableView(tblBook);
                fillTableView.fillTableView(dataItems);


            } else {
                System.err.println("DataItems list is empty!");
            }
        } else {
            System.err.println("Document is null!");
        }
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
            newScene.newScene("option-view.fxml", stage, 200, 200);
        } catch (IOException e) {
            throw new IOException(e);
        }
    }


}
