package jan.comic.Service;

import jan.comic.Data.DataReadWrite;
import jan.comic.SQLServices.TemporaryTable;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SearchEngine {

    public List<Map<String, Object>> search (ResultSet resultSet, String tableName, String searchTerm) throws SQLException {

        String patternString = SearchEngine.patternCreator(searchTerm);

        Pattern pattern = Pattern.compile(patternString);


        Stream<Map<String, Object>> resultSetStream = SearchEngine.resultSetToStream(resultSet);

        return resultSetStream.filter(resultSetRow ->
            resultSetRow.values().stream()
                 .anyMatch(columnValue -> pattern.matcher(columnValue.toString()).matches()))
            .collect(Collectors.toList());
    }

    private static Stream<Map<String, Object>> resultSetToStream(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        return Stream.generate(() -> {
            try {
                if(!resultSet.next()) {
                    return null;
                }
                Map<String, Object> rowData = new HashMap<>();
                for (int i = 1; i < columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(i);
                    rowData.put(columnName, columnValue);
                }
                return rowData;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }).takeWhile(row -> row != null);
    }

    private static  String patternCreator(String searchTerm) {

        String patternString = "";
        String patternStart = "^";
        String patternEnd = "$";
        String patternMiddle = "[a-zA-Z]+";
        String blank = "[:blank:]";


        if(searchTerm.contains(" ")){

            ArrayList<String> searchTermParts = new ArrayList<>(Arrays.asList(searchTerm.split(" ")));
            ArrayList<String> searchTermLetters = new ArrayList<>();

            for (String part : searchTermParts) {
                String firstLetter = part.substring(0, 1);
                String lastLetter = part.substring(part.length() - 1);
                searchTermLetters.add(firstLetter);
                searchTermLetters.add(lastLetter);

            }
            for (int i = 0; i < searchTermLetters.size(); i++) {
                String letter = searchTermLetters.get(i);
                patternString += patternStart + letter + patternMiddle + letter;
                if (i % 2 == 1 && i != searchTermLetters.size() - 1){
                    patternString += blank;
                }
            }
            patternString += patternEnd;
        }
        else {
            String searchTermFirstLetter = searchTerm.substring(0, 1);
            String searchTermLastLetter = searchTerm.substring(searchTerm.length() - 1);
            patternString = patternStart + searchTermFirstLetter + patternMiddle + searchTermLastLetter + patternEnd;
        }
        return patternString;
    }
}
