package jan.comic.TableConfigurator;

import jan.comic.OverController.OverlordOptionViewConfigurator;
import jan.comic.Scene.NewScene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class OptionViewConfigurator extends OverlordOptionViewConfigurator {

    private final NewScene newScene;
    private final String comicViewName;
    private final String comicViewFxml;
    private final String movieViewName;
    private final String movieViewFxml;
    private final String bookViewName;
    private final String bookViewFXML;

    public OptionViewConfigurator(NewScene newScene, String comicViewName, String comicViewFxml, String movieViewName,
                                  String movieViewFxml, String bookViewName, String bookViewFXML) {
        super(newScene, comicViewName, comicViewFxml, movieViewName, movieViewFxml, bookViewName, bookViewFXML);
        this.newScene = newScene;
        this.comicViewName = comicViewName;
        this.comicViewFxml = comicViewFxml;
        this.movieViewName = movieViewName;
        this.movieViewFxml = movieViewFxml;
        this.bookViewName = bookViewName;
        this.bookViewFXML = bookViewFXML;
    }

    public void enterComicView(@NotNull Button btnComic) throws IOException {
        Stage stage = (Stage) btnComic.getScene().getWindow();
        try {
            newScene.newScene(comicViewFxml, stage, 1200, 700,comicViewName);
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    public void enterMovieView(@NotNull Button btnMovie) throws IOException {
        Stage stage = (Stage) btnMovie.getScene().getWindow();
        try {
            newScene.newScene(movieViewFxml, stage, 1150, 700,movieViewName);
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    public void enterBookView(@NotNull Button btnBook) throws IOException {
        Stage stage = (Stage) btnBook.getScene().getWindow();
        try {
            newScene.newScene(bookViewFXML, stage, 1150, 700,bookViewName);
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}
