package jan.comic.SQLServices;

import jan.comic.Data.DataReadWrite;
import jan.comic.Service.XMLParser;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.sql.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TemporaryTable {

    private final DataReadWrite dataReadWrite;
    private final SQLWriteQuery sqlWriteQuery;
    private final XMLParser xmlParser;
    private static final String tempTable = "TempTable";

    public TemporaryTable(DataReadWrite dataReadWrite, SQLWriteQuery sqlWriteQuery, XMLParser xmlParser) {
        this.dataReadWrite = dataReadWrite;
        this.sqlWriteQuery = sqlWriteQuery;
        this.xmlParser = xmlParser;
    }

    public Document createTemporaryTable(String tableName, List<Map<String, Object>> searchList) throws SQLException {

        String query = createTemporaryTableQuery((tableName));
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Reha-TN/Desktop/Collection/Collection.db")) {

            try (PreparedStatement preparedStatementForTempTable = connection.prepareStatement(query)) {

                preparedStatementForTempTable.executeUpdate();
            }
            insertIntoTempTable(connection, searchList, sqlWriteQuery.poulateTempTableQuery(tempTable, searchList));

            String readQuery = sqlWriteQuery.readQuery(tempTable);
            try (PreparedStatement preparedStatementToReadFromTempTable = connection.prepareStatement(readQuery)) {

                ResultSet resultSet = preparedStatementToReadFromTempTable.executeQuery();

                return xmlParser.createXml(tableName, resultSet);
            } catch (SQLException | ParserConfigurationException | TransformerException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static String createTemporaryTableQuery(String tableName) {

        return "CREATE TEMP TABLE TempTable AS SELECT * FROM " + tableName + " WHERE 1=0;";
    }

    private static void insertIntoTempTable(Connection connection, List<Map<String, Object>> searchList, String query) throws SQLException {

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                for (Map<String, Object> data : searchList) {
                    int index = 1;
                    for (Object value : data.values()) {
                        preparedStatement.setObject(index++, value);
                    }
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

    }
}
