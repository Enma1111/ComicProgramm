package jan.comic.Service;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.FileWriter;
import java.io.IOException;

public class DataExporter {

    public static void exportXmlDataToText(Document document, String outputFilePath) {
        try {
            FileWriter writer = new FileWriter(outputFilePath);

            // NodeList der Elemente "row" erhalten
            NodeList rowList = document.getDocumentElement().getElementsByTagName("row");

            // Durch jedes "row"-Element iterieren und Daten in die Textdatei schreiben
            for (int i = 0; i < rowList.getLength(); i++) {
                Element row = (Element) rowList.item(i);

                // Daten aus den Elementen extrahieren (hier als Beispiel für ID und Name)
                String id = row.getElementsByTagName("ID").item(0).getTextContent();
                String name = row.getElementsByTagName("Name").item(0).getTextContent();

                // Daten in die Textdatei schreiben (hier als Beispiel für ID und Name)
                writer.write("ID: " + id + ", Name: " + name + "\n");
            }

            // Datei schließen
            writer.close();

            System.out.println("Data exported successfully to " + outputFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

