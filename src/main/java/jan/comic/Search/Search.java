package jan.comic.Search;

import jan.comic.Data.DataItem;
import jan.comic.XMLService.XMLParser;
import jan.comic.TableService.TableIInitiator;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.scene.control.TableView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.sql.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Search {

    private final XMLParser xmlParser;
    private final TableIInitiator tableIInitiator;
    private final String table;
    private static final Logger logger = LoggerFactory.getLogger(Search.class);

//    static final String URL = "jdbc:sqlite:C:/Users/Reha-TN/Desktop/Collection/Collection.db";
    private final String URL = "jdbc:sqlite:C:/Users/Jan/Desktop/Collection/Collection.db";

    public Search(XMLParser xmlParser, TableIInitiator tableIInitiator, String table) {
        this.xmlParser = xmlParser;
        this.tableIInitiator = tableIInitiator;
        this.table = table;
    }


    public void performSearch(String query,TableView<DataItem> tableView){

        Platform.runLater(() -> {

            final int timeout = 5000;
            AtomicBoolean isRunning = new AtomicBoolean(true);

            Task<Document> searchTask = new Task<Document>() {
                @Override
                protected Document call() throws Exception {
                    try(Connection connection = DriverManager.getConnection(URL)) {
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        ResultSet resultSet = preparedStatement.executeQuery();

                        return xmlParser.createXml(resultSet);

                    } catch (SQLException | TransformerException | ParserConfigurationException e) {
                        throw new RuntimeException(e);
                    }
                }
            };

            searchTask.setOnSucceeded((WorkerStateEvent event) -> {
                Document searchResult = searchTask.getValue();
                if (tableView != null) {
                    Platform.runLater(() -> tableIInitiator.initialize(tableView, table, searchResult));
                } else {
                    logger.error("tableView Instanz ist null");
                }
                isRunning.set(false);
            });

            searchTask.setOnFailed((WorkerStateEvent event) -> {

                Throwable exception = searchTask.getException();
                exception.printStackTrace();
                isRunning.set(false);
            });


            Thread timeoutThread = new Thread(() -> {
                try {
                    Thread.sleep(timeout);
                    if(isRunning.get()) {
                        searchTask.cancel();
                        Platform.runLater(() -> logger.info("timeout erreicht"));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            timeoutThread.setDaemon(true);
            timeoutThread.start();

            new Thread(searchTask).start();

            if (!timeoutThread.isAlive()) {
                logger.error("Thread wurde nicht gestartet");
            }
        });
    }
}
//        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
//
//            if(!newValue.isEmpty()){
//                Document searchValueDoc;
//                Document searchValue;
//
//
//                if(currentTable[0].equals(searchTable)){
//                    searchValue = dataReadWrite.dataReadSearch(sqlWriteQuery.searchQuery(newValue, searchColumn, searchTable),newValue,searchColumn);
//
//                    currentTable[0] = searchTable;
//                    tableIInitiator.initialize(tblComic,table,searchValue);
//
//                }else {
//                    searchValue =  dataReadWrite.dataRead(sqlWriteQuery.searchQuery(newValue, searchColumn, table));
//                    tableIInitiator.initialize(tblComic,searchTable,searchValue);
//                }
//                tableIInitiator.initialize(tblComic,table,searchValue);
//            }
//        });
