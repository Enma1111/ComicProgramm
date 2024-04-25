package jan.comic.Helper;


import jan.comic.ComicApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class StringBuilderHelper {

    private final List<String> colNames;
    private final String searchColumn;

    private static final Logger logger = LoggerFactory.getLogger(StringBuilderHelper.class);

    public StringBuilderHelper(List<String> colNames, String searchColumn) {
        this.colNames = colNames;
        this.searchColumn = searchColumn;
    }

    public String insertQueryBuilder(String table){

        StringBuilder insertQuery = new StringBuilder("INSERT INTO " + table + "(");
        StringBuilder values = new StringBuilder("VALUES (");

        for (int i = 0; i < colNames.size(); i++) {
            insertQuery.append(colNames.get(i));
            values.append("?");
            if (i < colNames.size() - 1) {
                insertQuery.append(", ");
                values.append(", ");
            }
        }

        insertQuery.append(") ");
        values.append(")");

        return insertQuery + values.toString();
    }

    public String searchQueryBuilder(String table, String searchTerm) {

        StringBuilder select = new StringBuilder("SELECT ");
        StringBuilder virtualTable = new StringBuilder(table + "_fts");
        StringBuilder bm25weigthing = new StringBuilder(", 0, 10, 0, 0, 0, 0, 0) AS bm25_score,");
        StringBuilder from = new StringBuilder(" FROM ");
        StringBuilder searchTable = new StringBuilder(table + " t ");
        StringBuilder innerJoin = new StringBuilder("INNER JOIN ");
        StringBuilder on = new StringBuilder(" s ON s." + searchColumn + "_ID" + " = t.ID");
        StringBuilder where = new StringBuilder(" WHERE ");
        StringBuilder match = new StringBuilder(" MATCH '" + searchTerm + "*';");
        StringBuilder bm25 = new StringBuilder("bm25(").append(virtualTable).append(bm25weigthing);
        StringBuilder tableColumns = new StringBuilder();
        for (int i = 0; i < colNames.size(); i++) {
            tableColumns.append("t.").append(colNames.get(i));
            if (i < colNames.size() -1) {
                tableColumns.append(", ");
            }

        }


        StringBuilder query = new StringBuilder();
        query.append(select).append(bm25)
                .append(tableColumns).append(from)
                .append(searchTable).append(innerJoin)
                .append(virtualTable).append(on)
                .append(where).append(virtualTable)
                .append(match);
        logger.info(query.toString());
        return query.toString();
    }
}
