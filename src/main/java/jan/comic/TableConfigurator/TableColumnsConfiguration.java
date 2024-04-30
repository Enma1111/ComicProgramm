package jan.comic.TableConfigurator;

import java.util.Arrays;
import java.util.List;
@Deprecated
public class TableColumnsConfiguration {
    public static final List<String> COMIC_COLUMNS = Arrays.asList("ID", "Comic", "Nummer", "Verpackung", "Kiste", "Verlag", "Doppelt");
    public static final List<String> COMIC_INSERT_COLUMNS = Arrays.asList("Comic", "Nummer", "Verpackung", "Kiste", "Verlag");
    public static final List<String> MOVIE_COLUMNS = Arrays.asList("ID","Film","Hauptdarsteller","Ort","Vertrieb","Format","Doppelt");
    public static final List<String> MOVIE_INSERT_COLUMNS = Arrays.asList("Film","Hauptdarsteller","Ort","Vertrieb","Format");
    public static final List<String> BOOK_COLUMNS = Arrays.asList("ID","Buch","Ort","Verlag","Doppelt");
    public static final List<String> BOOK_INSERT_COLUMN = Arrays.asList("Buch","Ort","Verlag");
}
