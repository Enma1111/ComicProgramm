package jan.comic.SQLServices;


import jan.comic.Helper.StringBuilderHelper;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Arrays;
import java.util.List;

//Diese Klasse dient dazu verschiedene SQL Abfragen zu erstellen

public class SQLWriteQuery {

    private final String tableName;
    private List<String> colNames;
    private String query;
    private static final Logger logger = LoggerFactory.getLogger(SQLWriteQuery.class);
    private final StringBuilderHelper stringBuilderHelper;

    public SQLWriteQuery(String tableName, List<String> colNames, String searchColumn) {
        this.tableName = tableName;
        stringBuilderHelper  = new StringBuilderHelper(colNames,searchColumn);
    }

    public String getTableName() {
        return tableName;
    }

    public List<String> getColNames() {
        return colNames;
    }

    public void setColNames(List<String> colNames) {
        this.colNames = colNames;
    }

    public String readQuery(String tableName){
        query = "SELECT * FROM " + tableName + ";";
        logger.info(query);
        return query;
    }

    public String searchQuery(@NotNull String searchTerm){

        query = stringBuilderHelper.searchQueryBuilder(tableName,searchTerm);
        logger.info(query);
        return query;
    }

    public String saveQuery(List<String> insertColumnNames){

        query = stringBuilderHelper.insertQueryBuilder(tableName,insertColumnNames);
        logger.info(query);
        return query;
    }

    public String deleteQuery(){

        query = "DELETE FROM " + tableName + " WHERE ID = ?;";
        logger.info(query);
        return query;
    }

}
//                query = insertQuery + "Film,Hauptdarsteller,Ort,Vertrieb,Format) VALUES " +
//                        "(" + movieName + "," + mainActor + "," + box + "," + distributor + "," + format + ")";
//                query = insertQuery + "Comic,Nummer,Verpackung,Kiste,Verlag) VALUES " +
//                        "(" + comicName + "," + number + "," + packaging + "," + box + "," + publisher + ")";
//
//        String searchTermUpper = searchTerm.substring(0,1).toUpperCase();
//        if (searchTable.equals(tempTable)){
//            query = "SELECT * FROM " + tableName + " WHERE " + colName + " LIKE '" + searchTerm + "%';";
//            logger.info(query);
//        } else if (searchTermUpper.length() == 1){
//            searchTermUpper = searchTermUpper + "%";
//            query = "SELECT * FROM " + tableName + " WHERE " + colName + " LIKE '" + searchTermUpper + "';";
//            logger.info(query);
//        }
//    public String populateTempTableQuery(String tempTable) {
//        StringBuilder insertQuery = new StringBuilder();
//        StringBuilder values = new StringBuilder("VALUES (");
//
//        for (int i = 0; i < comicColumns.size(); i++) {
//            insertQuery.append(comicColumns.get(i));
//            values.append("?");
//            if (i < comicColumns.size() - 1) {
//                insertQuery.append(", ");
//                values.append(", ");
//            }
//        }
//
//        insertQuery.append(") ");
//        values.append(")");
//
//        return insertQuery.toString() + values.toString();
//    }