package jan.bartalsky.comic.Data;

import jan.bartalsky.comic.Service.XMLParser;

import javax.xml.parsers.ParserConfigurationException;
import java.sql.*;

public class DataReadWrite {

    XMLParser xmlParser = new XMLParser();

    public void DataRead(String dbName){
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");

            Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:\\Users\\Reha-TN\\Desktop;shutdown=true");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM" + dbName);

            xmlParser.CreateXml(dbName, resultSet);

            resultSet.close();
            statement.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public void DataWrite(String dbName, String columnName){
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");

            Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:\\Users\\Reha-TN\\Desktop;shutdown=true");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM" + dbName);

            while (resultSet.next()) {

                String data = resultSet.getString(columnName);

            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
