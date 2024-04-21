package jan.comic.Data;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

//Transportklasse um die Daten für die Anzeige vorzubereiten

public class DataItem {
    private final String id;
    private final String comicName;
    private final String number;
    private final String packaging;
    private final String box;
    private final String doubleItem;
    private final String publisher;
    private final String movieName;
    private final String mainActor;
    private final String distributer;
    private final String format;
    private final String bookName;

    public DataItem(String id, String comicName, String number, String packaging, String box, String doubleItem, String publisher, String movieName, String mainActor, String distributer, String format, String bookName) {
        this.id = id;
        this.comicName = comicName;
        this.number = number;
        this.packaging = packaging;
        this.box = box;
        this.doubleItem = doubleItem;
        this.publisher = publisher;
        this.movieName = movieName;
        this.mainActor = mainActor;
        this.distributer = distributer;
        this.format = format;
        this.bookName = bookName;
    }

    @Contract(value = "_, _, _, _, _, _, _ -> new", pure = true)
    public static @NotNull DataItem createComicDataItem(String id, String comicName, String number, String packaging, String box, String doubleItem, String publisher) {
        return new DataItem(id, comicName, number, packaging, box, doubleItem, publisher,"","","","","");
    }

    @Contract(value = "_, _, _, _, _, _, _ -> new", pure = true)
    public static @NotNull DataItem createMovieDataItem(String id, String movieName, String mainActor, String box, String distributer, String format, String doubleItem) {
        return new DataItem(id,"","","", box, doubleItem, "",movieName, mainActor,  distributer, format,"");
    }

    @Contract(value = "_, _, _, _ -> new", pure = true)
    public static @NotNull DataItem createBookDataItem(String id, String bookName, String box, String publisher ){
        return new DataItem(id, "", "","", box,"", publisher,"","", "", "",bookName);
    }

    public String getId() {
        return id;
    }

    public String getComicName() {
        return comicName;
    }

    public String getPackaging() {
        return packaging;
    }

    public String getNumber() {
        return number;
    }

    public String getBox() {
        return box;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getMainActor() {
        return mainActor;
    }

    public String getDistributer() {
        return distributer;
    }

    public String getFormat() {
        return format;
    }

    public String getBookName() {
        return bookName;
    }

    public String getDoubleComicIn() {
        return doubleItem;
    }

    public String getPublisher() {
        return publisher;
    }
}
