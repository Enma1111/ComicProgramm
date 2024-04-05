package jan.bartalsky.comic.Gui;

import jan.bartalsky.comic.Service.FillTableView;
import jan.bartalsky.comic.Service.NewScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

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

    public void setTblComic(TableView<FillTableView.DataItem> tblComic) {
        this.tblComic = tblComic;
    }

    public TableView<FillTableView.DataItem> getTblComic() {
        return tblComic;
    }

    NewScene newScene = new NewScene();


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
