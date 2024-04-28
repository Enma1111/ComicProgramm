package jan.comic.TableConfigurator;

import jan.comic.Interfaces.TableBaseValues;

import java.util.List;

public class BookTableConfigurator implements TableBaseValues {
    @Override
    public List<String> getTableColumns() {
        return TableColumnsConfiguration.BOOK_COLUMNS;
    }

    @Override
    public List<String> getInsertColumns() {
        return TableColumnsConfiguration.BOOK_INSERT_COLUMN;
    }
}
