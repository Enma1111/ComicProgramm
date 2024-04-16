package jan.comic.Gui;

import jan.comic.Service.NewScene;
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

    @FXML
    public void EnterComicPnl(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) BtnComic.getScene().getWindow();
        try {
            newScene.newScene("comic-view.fxml", stage, 1150, 700,"Comics");
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    @FXML
    public void EnterBookPnl(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) BtnBook.getScene().getWindow();
        try {
            newScene.newScene("book-view.fxml", stage, 1150, 700,"Bücher");
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    @FXML
    public void EnterMoviePnl(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) BtnMovie.getScene().getWindow();
        try {
            newScene.newScene("movie-view.fxml", stage, 1150, 700,"Filme");
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}