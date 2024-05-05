package jan.comic.Data;

import jan.comic.Helper.PreparedStatementHelper;
import jan.comic.XMLService.XMLParser;
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
    private final PreparedStatementHelper preparedStatementHelper;


    static final String URL = "jdbc:sqlite:C:/Users/Jan/Desktop/Collection/Collection.db";
//    static final String URL = "jdbc:sqlite:C:/Users/Reha-TN/Desktop/Collection/Collection.db";

    private static final Logger logger = LoggerFactory.getLogger(DataReadWrite.class);

    public DataReadWrite(String tableName, XMLParser xmlParser, PreparedStatementHelper preparedStatementHelper) {
        this.tableName = tableName;
        this.xmlParser = xmlParser;
        this.preparedStatementHelper = preparedStatementHelper;
    }

    public Document dataRead(String query){

        try(Connection connection = DriverManager.getConnection(URL)) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            return xmlParser.createXml(resultSet);

        } catch (SQLException | TransformerException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public void dataWrite(String query, String[] values)  {

        try (Connection connection = DriverManager.getConnection(URL)) {
            logger.info(query);

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatementHelper.insertPreparedStatement(preparedStatement, values);

            logger.info("A new row has been inserted.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dataDelete(String query, String id) {
        try (Connection connection = DriverManager.getConnection(URL)) {

            logger.info(query);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatementHelper.deletePreparedStatement(preparedStatement,id);
            logger.info("Line has been Deleted");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

//int rowsInserted = preparedStatement.executeUpdate();
//            if (rowsInserted > 0) {
//
//            }