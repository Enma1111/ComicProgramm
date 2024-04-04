package jan.bartalsky.comic.Gui;

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
    private TableColumn <String, String>  ColNumber;
    @FXML
    private TableColumn <String, String>  ColDoubleComicIn;
    @FXML
    private TextField TxtDeleteID;
    @FXML
    private TableColumn <String, String>  ColBox;
    @FXML
    private TableColumn <String, String>  ColComic;
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
    private TableColumn <String, String>  ColVerlag;
    @FXML
    private TableColumn <Integer, Integer> ColID;
    @FXML
    private TextField TxtNumber;
    @FXML
    private TextField TxtPublischer;
    @FXML
    private TextField TxtBox;
    @FXML
    private Button BtnBackToOptions;

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
