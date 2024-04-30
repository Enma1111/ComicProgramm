package jan.comic.GuiController;

import jakarta.annotation.PostConstruct;
import jan.comic.TableConfigurator.OptionViewConfigurator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class OptionController {

    @FXML
    private Button btnMovie;
    @FXML
    private Button btnComic;
    @FXML
    private Button btnBook;

    private  final OptionViewConfigurator optionViewConfigurator;

    @Autowired
    public OptionController(OptionViewConfigurator optionViewConfigurator) {
        this.optionViewConfigurator = optionViewConfigurator;
    }

//    NewScene newScene = new NewScene();
    String comicViewName = "Comics";
    String bookViewName = "Bücher";
    String movieViewName = "Filme";


    @FXML
    public void enterComicPnl(ActionEvent actionEvent) throws IOException {
        optionViewConfigurator.enterComicView(btnComic);
//        Stage stage = (Stage) btnComic.getScene().getWindow();
//        try {
//            newScene.newScene("comic-view.fxml", stage, 1200, 700,comicViewName);
//        } catch (IOException e) {
//            throw new IOException(e);
//        }
    }

    @FXML
    public void enterBookPnl(ActionEvent actionEvent) throws IOException {
        optionViewConfigurator.enterMovieView(btnBook);
//        Stage stage = (Stage) btnBook.getScene().getWindow();
//        try {
//            newScene.newScene("book-view.fxml", stage, 1150, 700,bookViewName);
//        } catch (IOException e) {
//            throw new IOException(e);
//        }
    }

    @FXML
    public void enterMoviePnl(ActionEvent actionEvent) throws IOException {
        optionViewConfigurator.enterMovieView(btnMovie);
//        Stage stage = (Stage) btnMovie.getScene().getWindow();
//        try {
//            newScene.newScene("movie-view.fxml", stage, 1150, 700,movieViewName);
//        } catch (IOException e) {
//            throw new IOException(e);
//        }
    }
}