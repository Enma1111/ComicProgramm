package jan.bartalsky.comic.Gui;

import jan.bartalsky.comic.Service.NewScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class BookController {
    @FXML
    private TableColumn ColPlace;
    @FXML
    private TableColumn ColDoubleBookIn;
    @FXML
    private Button BtnDelete;
    @FXML
    private TableColumn ColNumber;
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
    private TableColumn ColBook;
    @FXML
    private Button BtnBookSave;
    @FXML
    private TextField TxtPublischer;
    @FXML
    private Button BtnSearch;
    @FXML
    private TableColumn ColVerlag;
    @FXML
    private TableColumn ColID;

    NewScene newScene = new NewScene();

    @FXML
    public void BookSearch(ActionEvent actionEvent) {
    }

    @FXML
    public void BookDelete(ActionEvent actionEvent) {
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

    @FXML
    public void SaveBook(ActionEvent actionEvent) {
    }
}
