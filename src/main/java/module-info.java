module jan.bartalsky.comic {
    requires javafx.controls;
    requires javafx.fxml;


    opens jan.bartalsky.comic to javafx.fxml;
    exports jan.bartalsky.comic;
    exports jan.bartalsky.comic.gui.Service;
    opens jan.bartalsky.comic.gui.Service to javafx.fxml;
}