package jan.comic.SQLServices;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class TemporaryTable {

    private final SQLWriteQuery sqlWriteQuery;
    private static final String Temp_Table = "TempTable";
    static final String url = "jdbc:sqlite:C:/Users/Reha-TN/Desktop/Collection/Collection.db";


    public TemporaryTable(SQLWriteQuery sqlWriteQuery) {
        this.sqlWriteQuery = sqlWriteQuery;
    }

    public void createTemporaryTable(String tableName, List<Map<String, Object>> searchList) throws SQLException {

        String query = createTemporaryTableQuery((tableName));
        try(Connection connection = DriverManager.getConnection(url)) {
//          Erstellung der Temporaeren Tabelle
            try (PreparedStatement preparedStatementForTempTable = connection.prepareStatement(query)) {

                preparedStatementForTempTable.executeUpdate();
            }
            insertIntoTempTable(connection, searchList, sqlWriteQuery.populateTempTableQuery(Temp_Table));
        }
    }

    @Contract(pure = true)
    private static @NotNull String createTemporaryTableQuery(String tableName) {

        return "CREATE TEMP TABLE TempTable AS SELECT * FROM " + tableName + " WHERE 1=0;";
    }

    private static void insertIntoTempTable(Connection connection, @NotNull List<Map<String, Object>> searchList, String query) throws SQLException {

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
