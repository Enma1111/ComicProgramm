package jan.comic.Gui;

import jan.comic.Service.NewScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.stage.Stage;


import java.io.IOException;

public class OptionController {

    @FXML
    private Button btnMovie;
    @FXML
    private Button btnComic;
    @FXML
    private Button btnBook;

    NewScene newScene = new NewScene();

    @FXML
    public void enterComicPnl(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnComic.getScene().getWindow();
        try {
            newScene.newScene("comic-view.fxml", stage, 1150, 700,"Comics");
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    @FXML
    public void enterBookPnl(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnBook.getScene().getWindow();
        try {
            newScene.newScene("book-view.fxml", stage, 1150, 700,"Bücher");
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    @FXML
    public void enterMoviePnl(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnMovie.getScene().getWindow();
        try {
            newScene.newScene("movie-view.fxml", stage, 1150, 700,"Filme");
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}