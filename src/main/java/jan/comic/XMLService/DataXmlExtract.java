package jan.comic.XMLService;

import jan.comic.Data.DataItem;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

//Klasse um die XML Datei zu lesen

public class DataXmlExtract {

    private static final Logger logger = LoggerFactory.getLogger(DataXmlExtract.class);

    public List<DataItem> extractData(@NotNull Document document, @NotNull String table){
        List<DataItem> dataItems = new ArrayList<>();
        NodeList rowList = document.getDocumentElement().getElementsByTagName("row");

            switch(table) {
                case "Comic_Table" -> {
                    for (int i = 0; i < rowList.getLength(); i++) {
                        Element row = (Element) rowList.item(i);
                        DataItem dataItem = createComicDataItem(row);
                        dataItems.add(dataItem);
                    }
                }
                case "Movie_Table" -> {
                    for (int i = 0; i < rowList.getLength(); i++) {
                        Element row = (Element) rowList.item(i);
                        DataItem dataItem = createMovieDataItem(row);
                        dataItems.add(dataItem);
                    }
                }
                case "Book_Table" -> {
                    for (int i = 0; i < rowList.getLength(); i++) {
                        Element row = (Element) rowList.item(i);
                        DataItem dataItem = createBookDataItem(row);
                        dataItems.add(dataItem);
                    }
                }
                default -> logger.info("Unknown table");
            }
            return dataItems;
    }

    private @NotNull DataItem createComicDataItem(Element row) {
        String id = getTextContentFromElement(row, "ID");
        String comicName = getTextContentFromElement(row, "Comic");
        String number = getTextContentFromElement(row, "Nummer");
        String packaging = getTextContentFromElement(row, "Verpackung");
        String box = getTextContentFromElement(row, "Kiste");
        String doubleComicIn = getTextContentFromElement(row, "Doppelt");
        String publisher = getTextContentFromElement(row, "Verlag");
        return DataItem.createComicDataItem(id, comicName, number, packaging, box, doubleComicIn, publisher);
    }

    private @NotNull DataItem createMovieDataItem(Element row) {
        String id = getTextContentFromElement(row, "ID");
        String movieName = getTextContentFromElement(row, "Film");
        String mainActor = getTextContentFromElement(row, "Hauptdarsteller");
        String box = getTextContentFromElement(row, "Ort");
        String distributor = getTextContentFromElement(row, "Vertrieb");
        String format = getTextContentFromElement(row, "Format");
        String doubleItem = getTextContentFromElement(row, "Doppelt");
        return DataItem.createMovieDataItem(id, movieName, mainActor, box, distributor, format, doubleItem);
    }

    private @NotNull DataItem createBookDataItem(Element row) {
        String id = getTextContentFromElement(row, "ID");
        String bookName = getTextContentFromElement(row, "Buch");
        String box = getTextContentFromElement(row, "Ort");
        String publisher = getTextContentFromElement(row, "Verlag");
        return DataItem.createBookDataItem(id, bookName, box, publisher);
    }

    private String getTextContentFromElement(@NotNull Element parentElement, String tagName) {
        Element element = (Element) parentElement.getElementsByTagName(tagName).item(0);
        return element != null ? element.getTextContent() : "";
    }
}
