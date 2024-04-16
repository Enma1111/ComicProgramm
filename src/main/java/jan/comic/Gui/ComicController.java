package jan.comic.Gui;

import jan.comic.Data.DataReadWrite;
import jan.comic.Data.DataXmlExtract;
import jan.comic.Service.*;
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
    @FXML
    private TextField TxtPackaging;

    public void setTblComic(TableView<FillTableView.DataItem> tblComic) {
        this.tblComic = tblComic;
    }

    String table = "Comic_Table";
    NewScene newScene = new NewScene();
    XMLParser xmlParser = new XMLParser();
    DataReadWrite dataReadWrite = new DataReadWrite(xmlParser);
    DataXmlExtract dataXmlExtract = new DataXmlExtract();
    SQLWriteQuery sqlWriteQuery = new SQLWriteQuery(table);
    TableIInitiator tableIInitiator = new TableIInitiator(dataReadWrite, dataXmlExtract);

    @FXML
    public void initialize(){
        tableIInitiator.initialize(tblComic, table);
    }

    @FXML
    public void SaveComic(ActionEvent actionEvent) {
        String[] val = new String[5];
        val[0] = TxtComicName.getCharacters().toString();
        val[1] = TxtNumber.getCharacters().toString();
        val[2] =TxtPackaging.getCharacters().toString();
        val[3] = TxtBox.getCharacters().toString();
        val[4] = TxtPublischer.getCharacters().toString();

        for (int i = 0; i < val.length; i++) {
            if (val[i].isEmpty()) {
                val[i] = "";
            }
        }

        dataReadWrite.DataWrite(val, sqlWriteQuery.saveQuery(val));
        tableIInitiator.initialize(tblComic, table);
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
            newScene.newScene("option-view.fxml", stage, 200, 200,"Optionen");
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}
