package jan.comic.TableConfigurator;

import jan.comic.Interfaces.TableBaseValues;

import java.util.ArrayList;
import java.util.List;

public class MovieTableConfigurator implements TableBaseValues {
    @Override
    public List<String> getTableColumns() {
        return TableColumnsConfiguration.MOVIE_COLUMNS;
    }

    @Override
    public List<String> getInsertColumns() {
        return TableColumnsConfiguration.MOVIE_INSERT_COLUMNS;
    }
}
