package jan.comic.Data;

import jan.comic.SQLServices.SQLWriteQuery;
import jan.comic.SQLServices.TemporaryTable;
import jan.comic.Service.SearchEngine;
import jan.comic.Service.XMLParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.sql.*;

//Klasse um di Vorbereiteten SQL Abfragen durchzuführen

public class DataReadWrite {

    String tableName;
    private final XMLParser xmlParser;
    private final TemporaryTable temporaryTable;
    private final SearchEngine searchEngine;
    private final SQLWriteQuery sqlWriteQuery;
//    static final String url = "jdbc:sqlite:C:/Users/Reha-TN/Desktop/Collection/Collection.db";
    static final String url = "jdbc:sqlite:C:/Users/Jan/Desktop/Collection/Collection.db";
    private static final Logger logger = LoggerFactory.getLogger(DataReadWrite.class);

    public DataReadWrite(String tableName, XMLParser xmlParser, TemporaryTable temporaryTable, SearchEngine searchEngine, SQLWriteQuery sqlWriteQuery) {
        this.tableName = tableName;
        this.xmlParser = xmlParser;
        this.temporaryTable = temporaryTable;
        this.searchEngine = searchEngine;
        this.sqlWriteQuery = sqlWriteQuery;
    }

    public Document dataRead(String query){

        try(Connection connection = DriverManager.getConnection(url)) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            return xmlParser.createXml(resultSet);

        } catch (SQLException | TransformerException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public void dataWrite(String query) {

        try (Connection connection = DriverManager.getConnection(url)) {
            logger.info(query);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                logger.info("A new row has been inserted.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dataDelete(String query) {
        try (Connection connection = DriverManager.getConnection(url)) {

            logger.info(query);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            logger.info("Line has been Deleted");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Document dataReadSearch(String query, String searchTerm, String searchColumn){

        try(Connection connection = DriverManager.getConnection(url)) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            temporaryTable.createTemporaryTable(dataRead(sqlWriteQuery.searchQuery(searchTerm,searchColumn,tableName));


            return xmlParser.createXml(resultSet);

        } catch (SQLException | TransformerException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}