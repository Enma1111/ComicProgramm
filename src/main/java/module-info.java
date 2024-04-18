module jan.bartalsky.comic {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires java.desktop;
    requires org.slf4j;
    requires org.jetbrains.annotations;
    requires java.xml;


    opens jan.comic to javafx.fxml;
    exports jan.comic;
    exports jan.comic.Service;
    opens jan.comic.Service to javafx.fxml;
    exports jan.comic.Gui;
    opens jan.comic.Gui to javafx.fxml;
    exports jan.comic.Data;
    opens jan.comic.Data to javafx.fxml;
    exports jan.comic.SQLServices;
    opens jan.comic.SQLServices to javafx.fxml;
    exports jan.comic.TableService;
    opens jan.comic.TableService to javafx.fxml;
}
