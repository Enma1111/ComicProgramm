package jan.comic.GuiController;

import jan.comic.Scene.NewScene;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class LoadingController {
    @javafx.fxml.FXML
    private ProgressBar prgrsBar;
    @javafx.fxml.FXML
    private Label lblProgress;
    NewScene newScene = new NewScene();

    private final String[] loadingMessages = {"Sammlung wird erzeugt", "Abhängichkeiten werden injeziert",
            "Sammlung wird gezählt", "Es ist zu viel"};
    private int messageIndex = 0;


    public void initialize() throws IOException {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, (EventHandler<ActionEvent>) event -> {
                    lblProgress.setText(loadingMessages[messageIndex]);
                    messageIndex = (messageIndex + 1) % loadingMessages.length;
                }),
                new KeyFrame(Duration.seconds(2), e -> {
                    prgrsBar.setProgress(0.25); // 25% Fortschritt nach 2 Sekunden
                    lblProgress.setText(loadingMessages[messageIndex]);
                    messageIndex = (messageIndex + 1) % loadingMessages.length;
                }),
                new KeyFrame(Duration.seconds(4), e -> {
                    prgrsBar.setProgress(0.5); // 50% Fortschritt nach 4 Sekunden
                    lblProgress.setText(loadingMessages[messageIndex]);
                    messageIndex = (messageIndex + 1) % loadingMessages.length;
                }),
                new KeyFrame(Duration.seconds(6), e -> {
                    prgrsBar.setProgress(0.75); // 75% Fortschritt nach 6 Sekunden
                    lblProgress.setText(loadingMessages[messageIndex]);
                    messageIndex = (messageIndex + 1) % loadingMessages.length;
                }),
                new KeyFrame(Duration.seconds(8), e -> {
                    prgrsBar.setProgress(1);// 100% Fortschritt nach 8 Sekunden
                    try {
                        loadView();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
        );
        timeline.setCycleCount(1);
        timeline.play();
    }

    private void loadView() throws IOException {
        Stage stage = (Stage) prgrsBar.getScene().getWindow();
        try {
            newScene.newScene("option-view.fxml", stage, 250, 200,"Optionen");
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}
