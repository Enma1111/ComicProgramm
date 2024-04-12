package jan.bartalsky.comic.Data;

import jan.bartalsky.comic.Service.XMLParser;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.sql.*;

public class DataReadWrite {

    private final XMLParser xmlParser;

    public DataReadWrite(XMLParser xmlParser) {
        this.xmlParser = xmlParser;
    }

    public Document DataRead(String tableName){
        String url = "jdbc:sqlite:C:/Users/Reha-TN/Desktop/Collection/Collection.db";
        String query = "SELECT * FROM " + tableName;

        try(Connection connection = DriverManager.getConnection(url)) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            return xmlParser.createXml(tableName, resultSet);


        } catch (SQLException | ParserConfigurationException | TransformerException e) {
            throw new RuntimeException(e);
        }

    }

    public void DataWrite(String tableName, String column2, String column3, String column4, String column5, String column6, String column7){
        String url = "jdbc:sqlite:C:/Users/Reha-TN/Desktop/Collection/Collection.db";
        String query = "INSERT INTO " + tableName + " (" + + "\nVALUES";

        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Reha-TN/Desktop/Collection/Collection.db")) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//
//
//
//            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
