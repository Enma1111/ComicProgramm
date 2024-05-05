module jan.comic {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires java.desktop;
    requires org.slf4j;
    requires org.jetbrains.annotations;
    requires java.xml;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
    requires org.kordamp.ikonli.typicons;

    exports jan.comic;
    exports jan.comic.Gui;
    opens jan.comic.Gui to javafx.fxml;
    exports jan.comic.Data;
    opens jan.comic.Data to javafx.fxml;
    exports jan.comic.SQLServices;
    opens jan.comic.SQLServices to javafx.fxml;
    exports jan.comic.TableService;
    opens jan.comic.TableService to javafx.fxml;
    exports jan.comic.Search;
    opens jan.comic.Search to javafx.fxml;
    exports jan.comic.Helper;
    opens jan.comic.Helper to javafx.fxml;
    exports jan.comic.Scene;
    opens jan.comic.Scene to javafx.fxml;
    exports jan.comic.XMLService;
    opens jan.comic.XMLService to javafx.fxml;


}
