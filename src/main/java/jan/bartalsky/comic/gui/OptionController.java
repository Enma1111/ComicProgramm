package jan.bartalsky.comic.Gui;

import jan.bartalsky.comic.Data.DataReadWrite;
import jan.bartalsky.comic.Data.DataXmlExtract;
import jan.bartalsky.comic.Service.FillTableView;
import jan.bartalsky.comic.Service.NewScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import org.w3c.dom.Document;
import java.io.IOException;
import java.util.List;

public class OptionController {

    @FXML
    private Button BtnMovie;
    @FXML
    private Button BtnComic;
    @FXML
    private Button BtnBook;

    NewScene newScene = new NewScene();
    TableView<FillTableView.DataItem> tableView = new TableView<>();
    DataReadWrite dataReadWrite = new DataReadWrite();
    FillTableView fillTableView = new FillTableView(tableView);
    DataXmlExtract dataXmlExtract = new DataXmlExtract();

    @FXML
    public void EnterComicPnl(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) BtnComic.getScene().getWindow();

        try {
            newScene.newScene("comic-view.fxml", stage, 575, 627);
            Document document = dataReadWrite.DataRead("Comic_Table");
            List<FillTableView.DataItem> dataItems = dataXmlExtract.extractData(document); // Methode zum Extrahieren der Daten
            fillTableView.fillTableView(dataItems);
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