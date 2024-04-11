package jan.bartalsky.comic.Data;

import jan.bartalsky.comic.Service.DataExporter;
import jan.bartalsky.comic.Service.FillTableView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class DataXmlExtract {

    public List<FillTableView.DataItem> extractData(Document document){
        List<FillTableView.DataItem> dataItems = new ArrayList<>();
        NodeList rowList = document.getDocumentElement().getElementsByTagName("row");

        for (int i = 0; i < rowList.getLength(); i++) {
            Element row = (Element) rowList.item(i);

            String id = getTextContentFromElement(row, "ID");
            String comicName = getTextContentFromElement(row, "Comic");
            String number = getTextContentFromElement(row, "Nummer");
            String packaging = getTextContentFromElement(row, "Verpackung");
            String box = getTextContentFromElement(row, "Kiste");
            String doubleComicIn = getTextContentFromElement(row, "Doppelt");
            String publisher = getTextContentFromElement(row, "Verlag");

            FillTableView.DataItem dataItem = new FillTableView.DataItem(id, comicName, number, packaging, box, doubleComicIn, publisher);
            dataItems.add(dataItem);
        }
        return dataItems;
    }

    private String getTextContentFromElement(Element parentElement, String tagName) {
        Element element = (Element) parentElement.getElementsByTagName(tagName).item(0);
        return element != null ? element.getTextContent() : "";
    }
}
