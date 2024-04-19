package jan.comic.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;

public class DataReadWrite {

    String tableName;
    static final String url = "jdbc:sqlite:C:/Users/Reha-TN/Desktop/Collection/Collection.db";
    private static final Logger logger = LoggerFactory.getLogger(DataReadWrite.class);

    public DataReadWrite(String tableName) {
        this.tableName = tableName;
    }

    public ResultSet dataRead(String query){

        try(Connection connection = DriverManager.getConnection(url)) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            return preparedStatement.executeQuery();

        } catch (SQLException e) {
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
}