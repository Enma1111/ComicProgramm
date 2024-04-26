package jan.comic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.IOException;

public class ComicApplication extends Application {
    private static final Logger logger = LoggerFactory.getLogger(ComicApplication.class);
    @Override
    public void start(@NotNull Stage stage) throws IOException {
        logger.info("Starting Comic Application");
        FXMLLoader fxmlLoader = new FXMLLoader(ComicApplication.class.getResource("option-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 200, 200);
        stage.setTitle("Optionen");
        Image icon = new Image(getClass().getResourceAsStream("gear-icon.png"));
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}