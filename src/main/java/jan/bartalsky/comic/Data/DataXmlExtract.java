package jan.bartalsky.comic.Data;

import jan.bartalsky.comic.Service.DataExporter;
import jan.bartalsky.comic.Service.FillTableView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class DataXmlExtract {

    public List<FillTableView.DataItem> extractData(Document document, String table){
        List<FillTableView.DataItem> dataItems = new ArrayList<>();
        NodeList rowList = document.getDocumentElement().getElementsByTagName("row");

            switch(table) {
                case "Comic_Table" -> {
                    for (int i = 0; i < rowList.getLength(); i++) {
                        Element row = (Element) rowList.item(i);

                        String id = getTextContentFromElement(row, "ID");
                        String comicName = getTextContentFromElement(row, "Comic");
                        String number = getTextContentFromElement(row, "Nummer");
                        String packaging = getTextContentFromElement(row, "Verpackung");
                        String box = getTextContentFromElement(row, "Kiste");
                        String doubleComicIn = getTextContentFromElement(row, "Doppelt");
                        String publisher = getTextContentFromElement(row, "Verlag");

                        FillTableView.DataItem dataItem = FillTableView.DataItem.createComicDataItem(id, comicName, number, packaging, box, doubleComicIn, publisher);
                        dataItems.add(dataItem);
                    }
                }
                case "Movie_Table" -> {
                    for (int i = 0; i < rowList.getLength(); i++) {
                        Element row = (Element) rowList.item(i);

                        String id = getTextContentFromElement(row, "ID");
                        String movieName = getTextContentFromElement(row, "Film");
                        String mainActor = getTextContentFromElement(row, "Hauptdarsteller");
                        String box = getTextContentFromElement(row, "Ort");
                        String distributor = getTextContentFromElement(row, "Vertrieb");
                        String format = getTextContentFromElement(row, "Format");
                        String doubleItem = getTextContentFromElement(row, "Doppelt");

                        FillTableView.DataItem dataItem = FillTableView.DataItem.createMovieDataItem(id, movieName, mainActor, box, distributor, format, doubleItem);
                        dataItems.add(dataItem);
                    }
                }
                case "Book_Table" -> {
                    for (int i = 0; i < rowList.getLength(); i++) {
                        Element row = (Element) rowList.item(i);

                        String id = getTextContentFromElement(row, "ID");
                        String bookName = getTextContentFromElement(row, "Buch");
                        String box = getTextContentFromElement(row, "Ort");
                        String publisher = getTextContentFromElement(row, "Verlag");

                        FillTableView.DataItem dataItem = FillTableView.DataItem.createBookDataItem(id, bookName, box, publisher);
                        dataItems.add(dataItem);
                    }
                }
                default -> System.out.println("Unknown table");
            }
            return dataItems;
    }

    private String getTextContentFromElement(Element parentElement, String tagName) {
        Element element = (Element) parentElement.getElementsByTagName(tagName).item(0);
        return element != null ? element.getTextContent() : "";
    }
}
