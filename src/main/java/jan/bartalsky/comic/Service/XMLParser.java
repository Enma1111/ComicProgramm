package jan.bartalsky.comic.Service;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class XMLParser {

    public Document CreateXml(String name, ResultSet resultSet) throws ParserConfigurationException, SQLException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

//      XML Wurzelelement wird erstellt
        Element rootElement = document.createElement(name);
        document.appendChild(rootElement);

        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        while(resultSet.next()){

            Element rowElement = document.createElement("row");


            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                String columnValue = resultSet.getString(i);

                Element columnElement = document.createElement(columnName);
                columnElement.appendChild(document.createTextNode(columnValue));
                rowElement.appendChild(columnElement);
            }
        }
        return document;
    }
}
