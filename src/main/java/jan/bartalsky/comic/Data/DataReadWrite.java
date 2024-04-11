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
