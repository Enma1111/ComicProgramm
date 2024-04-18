package jan.comic.Data;

import jan.comic.Service.XMLParser;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.sql.*;

public class DataReadWrite {

    private final XMLParser xmlParser;
    String tableName;

    public DataReadWrite(XMLParser xmlParser, String tableName) {
        this.xmlParser = xmlParser;
        this.tableName = tableName;
    }

    public Document dataRead(String query){

        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Reha-TN/Desktop/Collection/Collection.db")) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            return xmlParser.createXml(tableName, resultSet);

        } catch (SQLException | ParserConfigurationException | TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    public void dataWrite(String query) {

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Reha-TN/Desktop/Collection/Collection.db")) {
            System.out.println(query);
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new row has been inserted.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dataDelete(String query) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Reha-TN/Desktop/Collection/Collection.db")) {
            System.out.println(query);

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();

            System.out.println("Line has been Deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}