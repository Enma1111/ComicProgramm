package jan.comic.Scene;

import jan.comic.ComicApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.io.IOException;
@Service
public class NewScene {

    public void newScene(String newWindow, @NotNull Stage stage, int xValue, int yValue, String windowName) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ComicApplication.class.getResource(newWindow));
            Scene scene = new Scene(fxmlLoader.load(), xValue, yValue);
            stage.setTitle(windowName);
            stage.setScene(scene);
        } catch (IOException e){
            throw new IOException(e);
        }
    }
}
