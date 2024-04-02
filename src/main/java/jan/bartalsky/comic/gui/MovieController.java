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
import java.nio.Buffer;

public class MovieController {
    @FXML
    private TableColumn <String, String>  ColPlace;
    @FXML
    private TableColumn <String, String>  ColDoubleMovieIn;
    @FXML
    private Button BtnDelete;
    @FXML
    private Button BtnBackToOptions;
    @FXML
    private TextField TxtDeleteID;
    @FXML
    private TableColumn <String, String>  ColDistributor;
    @FXML
    private TableColumn <String, String>  ColMovie;
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
    private TableColumn <Integer, Integer>  ColID;

    NewScene newScene = new NewScene();

    @FXML
    public void MovieDelete(ActionEvent actionEvent) {
    }

    @FXML
    public void MovieSearch(ActionEvent actionEvent) {
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
    public void SaveMovie(ActionEvent actionEvent) {
    }
}
