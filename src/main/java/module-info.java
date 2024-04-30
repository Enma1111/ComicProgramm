module jan.comic {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.beans;
    requires spring.core;
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
    requires jakarta.annotation;

//    opens jan.comic to javafx.fxml;
    exports jan.comic;
    exports jan.comic.GuiController;
    opens jan.comic.GuiController to spring.core;
    exports jan.comic.Data;
    opens jan.comic.Data to spring.core;
    exports jan.comic.SQLServices;
    opens jan.comic.SQLServices to spring.core;
    exports jan.comic.TableService;
    opens jan.comic.TableService to spring.core;
    exports jan.comic.Search;
    opens jan.comic.Search to spring.core;
    exports jan.comic.Helper;
    opens jan.comic.Helper to spring.core;
    exports jan.comic.Scene;
    opens jan.comic.Scene to spring.core;
    exports jan.comic.XMLService;
    opens jan.comic.XMLService to spring.core;
    exports jan.comic.AppConfiguration;
    opens jan.comic.AppConfiguration to spring.core;
    exports jan.comic.TableConfigurator;
    opens jan.comic.TableConfigurator to spring.core;
    exports jan.comic.OverController;
    opens jan.comic to spring.core;

}
