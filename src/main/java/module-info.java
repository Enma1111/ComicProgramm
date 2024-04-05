module jan.bartalsky.comic {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires java.desktop;


    opens jan.bartalsky.comic to javafx.fxml;
    exports jan.bartalsky.comic;
    exports jan.bartalsky.comic.Service;
    opens jan.bartalsky.comic.Service to javafx.fxml;
    exports jan.bartalsky.comic.Gui;
    opens jan.bartalsky.comic.Gui to javafx.fxml;
}
