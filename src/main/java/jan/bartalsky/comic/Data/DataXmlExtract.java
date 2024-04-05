package jan.bartalsky.comic.Data;

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
            Element element = (Element) rowList.item(i);
                String value = element.getTextContent();
                dataItems.add(new FillTableView.DataItem(value));
            }
        return dataItems;
        }
}
