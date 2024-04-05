package jan.bartalsky.comic.Data;

import jan.bartalsky.comic.Service.XMLParser;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import java.sql.*;

public class DataReadWrite {

    XMLParser xmlParser = new XMLParser();

    public Document DataRead(String tableName){
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Reha-TN/Desktop/Collection/Collection.db")) {

            String query = "Select * From " + tableName;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            Document document = xmlParser.CreateXml(tableName, resultSet);

            resultSet.close();
            preparedStatement.close();

            return document;

        } catch (SQLException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

    }

    public void DataWrite(String tableName){
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Reha-TN/Desktop/Collection/Collection.db")) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);

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
