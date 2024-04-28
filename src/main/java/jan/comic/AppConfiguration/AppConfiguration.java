package jan.comic.AppConfiguration;

import jan.comic.Helper.PreparedStatementHelper;
import jan.comic.Helper.ValueNullCheckHelper;
import jan.comic.Interfaces.TableBaseValues;
import jan.comic.OverController.OverlordControllerConfigurator;
import jan.comic.SQLServices.SQLWriteQuery;
import jan.comic.Scene.NewScene;
import jan.comic.TableConfigurator.BookTableConfigurator;
import jan.comic.TableConfigurator.ComicTableConfigurator;
import jan.comic.TableConfigurator.MovieTableConfigurator;
import jan.comic.XMLService.DataXmlExtract;
import jan.comic.XMLService.XMLParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class AppConfiguration {

    @Value("${comicTableName}")
    private String comicTableName;

    @Value("${comicSearchColumn}")
    private String comicSearchColumn;

    @Value("${movieTableName}")
    private String movieTableName;

    @Value("${movieSearchColumn}")
    private String movieSearchColumn;

    @Value("${bookTableName}")
    private String bookTableName;

    @Value("${bookSearchColumn}")
    private String bookSearchColumn;

    @Value("${comic.table.columns}")
    private String comicTableColumnsStr;

    @Value("${comic.table.insert.columns}")
    private String comicInsertColumnsStr;

    @Value("${movie.table.columns}")
    private String movieTableColumnsStr;

    @Value("${movie.table.insert.columns}")
    private String movieInsertColumnsStr;

    @Value("${book.table.columns}")
    private String bookTableColumnsStr;

    @Value("${boook.table.insert.columns}")
    private String bookInsertColumnsStr;

    @Bean
    public XMLParser comicXMLParser() {
        return new XMLParser(comicTableName);
    }

    @Bean
    public XMLParser moviwXMLParser() {
        return new XMLParser(movieTableName);
    }

    @Bean
    public XMLParser bookXMLParser() {
        return new XMLParser(bookTableName);
    }

    @Bean
    public NewScene newScene() {
        return new NewScene();
    }

    @Bean
    public OverlordControllerConfigurator comicTableConfigurator(NewScene newScene XMLParser comicXMLParser) {
        return new ComicTableConfigurator(newScene, comicXMLParser);
    }

    @Bean
    public OverlordControllerConfigurator movieTableConfigurator(XMLParser movieXMLParser) {
        return new MovieTableConfigurator(movieXMLParser);
    }

    @Bean
    public OverlordControllerConfigurator bookTableConfigurator(XMLParser bookXMLParser) {
        return new BookTableConfigurator(bookXMLParser);
    }



    @Bean
    public PreparedStatementHelper preparedStatementHelper() {
        return new PreparedStatementHelper();
    }

    @Bean
    public ValueNullCheckHelper valueNullCheckHelper() {
        return new ValueNullCheckHelper();
    }

    @Bean
    public DataXmlExtract dataXmlExtract() {
        return new DataXmlExtract();
    }

    @Bean
    public SQLWriteQuery sqlWriteQuery(XMLParser comicXMLParser) {
        List<String> comicTableColumns = Arrays.asList(comicTableColumnsStr.split(","));
        return new SQLWriteQuery(comicTableName, comicTableColumns, comicSearchColumn);
    }
}
