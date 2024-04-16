package jan.comic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ComicApplication extends Application {
    private static final Logger logger = LoggerFactory.getLogger(ComicApplication.class);
    @Override
    public void start(Stage stage) throws IOException {
        logger.info("Starting Comic Application");
        FXMLLoader fxmlLoader = new FXMLLoader(ComicApplication.class.getResource("option-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 200, 200);
        stage.setTitle("Optionen");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}