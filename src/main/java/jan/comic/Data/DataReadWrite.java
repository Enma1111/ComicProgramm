package jan.comic.Data;

import jan.comic.Service.XMLParser;
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

    public void DataWrite(String[] val, String query) {

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
}