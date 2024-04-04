package jan.bartalsky.comic.Data;

import jan.bartalsky.comic.Service.XMLParser;

import javax.xml.parsers.ParserConfigurationException;
import java.sql.*;

public class DataReadWrite {

    XMLParser xmlParser = new XMLParser();

    public void DataRead(String tableName){
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Reha-TN/Desktop/Collection/Collection.db")) {

            String query = "Select * From " + tableName;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            xmlParser.CreateXml(tableName, resultSet);

            resultSet.close();
            preparedStatement.close();

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
