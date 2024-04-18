package jan.comic.SQLServices;

import java.util.List;

public class SQLWriteQuery {

    String tableName;

    String query;

    public SQLWriteQuery(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String readQuery(String tableName){
        query = "SELECT * FROM " + tableName + ";";
        System.out.println(query);
        return query;
    }

    public String searchQuery(String searchTerm, String colName){
        if (searchTerm.length() == 1){
            searchTerm = searchTerm + "%";
        }
        query = "SELECT * FROM " + tableName + " WHERE " + colName + " LIKE '" + searchTerm + "';";
        System.out.println(query);
        return query;
    }

    public String saveQuery(String[] val){

         switch (tableName){
            case "Comic_Table" ->{

                String comicName = val[0] != null ? "'" + val[0] + "'" : "NULL";
                String number = val[1].isEmpty() ? "'" + val[1] + "'" : "Einzelband";
                String packaging = val[2].isEmpty() ? "'" + val[2] + "'" : "Offen";
                String box = val[3] != null ? "'" + val[3] + "'" : "NULL";
                String publisher = val[4] != null ? "'" + val[4] + "'" : "NULL";

                query = "INSERT INTO " + tableName + " (Comic,Nummer,Verpackung,Kiste,Verlag) VALUES " +
                        "(" + comicName + "," + number + "," + packaging + "," + box + "," + publisher + ")";
            }
            case "Movie_Table" ->{

                String movieName = val[0] != null ? "'" + val[0] + "'" : "NULL";
                String mainActor = val[1] != null ? "'" + val[1] + "'" : "NULL";
                String box = val[2] != null ? "'" + val[2] + "'" : "NULL";
                String distributor = val[3] != null ? "'" + val[3] + "'" : "NULL";
                String format = val[4] != null ? "'" + val[4] + "'" : "NULL";

                query = "INSERT INTO " + tableName + " (Film,Hauptdarsteller,Ort,Vertrieb,Format) VALUES " +
                        "(" + movieName + "," + mainActor + "," + box + "," + distributor + "," + format + ")";
            }
            case "Book_Table" ->{

                String bookName = val[0] != null ? "'" + val[0] + "'" : "NULL";
                String box = val[1] != null ? "'" + val[1] + "'" : "NULL";
                String publisher = val[2] != null ? "'" + val[2] + "'" : "NULL";

                query = "INSERT INTO " + tableName + " (Buch,Ort,Verlag) VALUES " +
                        "(" + bookName + "," + box + "," + publisher + ")";
            }
            default -> {
                System.out.println("Unknown table name: " + tableName);
                return "";
            }
        }
        return query;
    }

    public String deleteQuery(String id){
        return query = "DELETE FROM " + tableName + " WHERE ID = " + id + ";";
    }

    public String poulateTempTableQuery(String tempTable, List<String> columnNames) {
        StringBuilder insertQuery = new StringBuilder("INSERT INTO " + tempTable + " (");
        StringBuilder values = new StringBuilder("VALUES (");

        for (int i = 0; i < columnNames.size(); i++) {
            insertQuery.append(columnNames.get(i));
            values.append("?");
            if (i < columnNames.size() - 1) {
                insertQuery.append(", ");
                values.append(", ");
            }
        }

        insertQuery.append(") ");
        values.append(")");

        return insertQuery.toString() + values.toString();
    }

}
