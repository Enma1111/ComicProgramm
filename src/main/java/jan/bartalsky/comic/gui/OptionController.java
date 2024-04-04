package jan.bartalsky.comic.Gui;

import jan.bartalsky.comic.Data.DataReadWrite;
import jan.bartalsky.comic.Service.NewScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class OptionController {

    @FXML
    private Button BtnMovie;
    @FXML
    private Button BtnComic;
    @FXML
    private Button BtnBook;
    NewScene newScene = new NewScene();
    DataReadWrite dataReadWrite = new DataReadWrite();

    @FXML
    public void EnterComicPnl(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) BtnComic.getScene().getWindow();
        try {
            newScene.newScene("comic-view.fxml", stage, 575, 627);
            dataReadWrite.DataRead("Comic_Table");
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
    @FXML
    public void EnterBookPnl(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) BtnBook.getScene().getWindow();
        try {
            newScene.newScene("book-view.fxml", stage, 575, 627);
            dataReadWrite.DataRead("Book_Table");
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    @FXML
    public void EnterMoviePnl(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) BtnMovie.getScene().getWindow();
        try {
            newScene.newScene("movie-view.fxml", stage, 575, 627);
            dataReadWrite.DataRead("Movie_Table");
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}