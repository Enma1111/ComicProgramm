package jan.comic.Service;

public class SQLWriteQuery {

    String tableName;

    public SQLWriteQuery(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String saveQuery(String[] val){
        String query = null;
         switch (tableName){
            case "Comic_Table" ->{

                String comicName = val[0] != null ? "'" + val[0] + "'" : "NULL";
                String number = val[1] != null ? "'" + val[1] + "'" : "NULL";
                String packaging = val[2] != null ? "'" + val[2] + "'" : "NULL";
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
                String publischer = val[2] != null ? "'" + val[2] + "'" : "NULL";

                query = "INSERT INTO " + tableName + " (Buch,Ort,Verlag) VALUES " +
                        "(" + bookName + "," + box + "," + publischer + ")";
            }
            default -> {
                System.out.println("Unknown table name: " + tableName);
                return "";
            }
        }
        return query;
    }
}
