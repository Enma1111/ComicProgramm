module jan.bartalsky.comic {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires java.desktop;
    requires org.slf4j;
    requires org.jetbrains.annotations;


    opens jan.comic to javafx.fxml;
    exports jan.comic;
    exports jan.comic.Service;
    opens jan.comic.Service to javafx.fxml;
    exports jan.comic.Gui;
    opens jan.comic.Gui to javafx.fxml;
    exports jan.comic.Data;
}
