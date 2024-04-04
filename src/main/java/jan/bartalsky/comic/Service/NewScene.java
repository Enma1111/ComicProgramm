package jan.bartalsky.comic.Service;

import jan.bartalsky.comic.ComicApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NewScene {

    public void newScene(String newWindow, Stage stage, int xValue, int yValue) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ComicApplication.class.getResource(newWindow));
            Scene scene = new Scene(fxmlLoader.load(), xValue, yValue);
            stage.setTitle("Hello!");
            stage.setScene(scene);
        } catch (IOException e){
            throw new IOException(e);
        }
    }
}
