module jan.bartalsky.comic {
    requires javafx.controls;
    requires javafx.fxml;


    opens jan.bartalsky.comic to javafx.fxml;
    exports jan.bartalsky.comic;
    exports jan.bartalsky.comic.Service;
    opens jan.bartalsky.comic.Service to javafx.fxml;
}