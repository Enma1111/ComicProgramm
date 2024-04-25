package jan.comic.XMLService;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//Klasse um aus ein ResultSet in ein XML umzuwandeln

public class XMLParser {

    private final String name;

    public XMLParser(String name) {
        this.name = name;
    }

    public Document createXml(@NotNull ResultSet resultSet) throws ParserConfigurationException, SQLException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

//      XML Wurzelelement wird erstellt
        Element rootElement = document.createElement(name);
        document.appendChild(rootElement);

        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        List<String> columnNames = new ArrayList<>();
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            if (!columnName.equalsIgnoreCase("bm25_score")) {
                columnNames.add(columnName);
            }
        }

        while (resultSet.next()) {
//      Child Element Row wird erstellt
            Element rowElement = document.createElement("row");
//      Child Column Elemente werden erstellt

            for (String columnName : columnNames) {
                String columnValue = resultSet.getString(columnName);
                Element columnElement = document.createElement(columnName);
                columnElement.appendChild(document.createTextNode(Objects.requireNonNullElse(columnValue, "")));
                rowElement.appendChild(columnElement);
            }
//            for (int i = 1; i <= columnCount; i++) {
//                String columnName = metaData.getColumnName(i);
//                String columnValue = resultSet.getString(i);
//                Element columnElement = document.createElement(columnName);
//
//                                    Element id = document.createElement(metaData.getColumnName(i));
//                                    id.appendChild(document.createTextNode(resultSet.getString(i)));
//                                    rowElement.appendChild(id);
//                columnElement.appendChild(document.createTextNode(Objects.requireNonNullElse(columnValue, "")));
//                rowElement.appendChild(columnElement);
//            }

            rootElement.appendChild(rowElement);

        }
//      Dient dazu das XML Document anzuspeicheren
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult("output.xml");
        transformer.transform(source, result);

        return document;
    }
}
