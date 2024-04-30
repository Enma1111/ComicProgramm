package jan.comic.AppConfiguration;

import jan.comic.Data.DataReadWrite;
import jan.comic.GuiController.BookController;
import jan.comic.Helper.PreparedStatementHelper;
import jan.comic.Helper.ValueNullCheckHelper;
import jan.comic.Helper.WarningHelper;
import jan.comic.OverController.OverlordOptionViewConfigurator;
import jan.comic.OverController.OverlordViewControllerConfigurator;
import jan.comic.SQLServices.SQLWriteQuery;
import jan.comic.Scene.NewScene;
import jan.comic.Search.Search;
import jan.comic.TableConfigurator.BookViewConfigurator;
import jan.comic.TableConfigurator.ComicViewConfigurator;
import jan.comic.TableConfigurator.MovieViewConfigurator;
import jan.comic.TableConfigurator.OptionViewConfigurator;
import jan.comic.TableService.TableIInitiator;
import jan.comic.XMLService.DataXmlExtract;
import jan.comic.XMLService.XMLParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;
import java.util.List;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(AppConfiguration.class);

//  Comic Properties
    @Value("${comicTableName}")
    private String comicTableName;

    @Value("${comicSearchColumn}")
    private String comicSearchColumn;

    @Value("${comic.table.columns}")
    private String comicTableColumnsStr;

    @Value("${comic.table.insert.columns}")
    private String comicInsertColumnsStr;
//  --------------------------------------------------------------------------------------------------------------------

//  Movie Properties
    @Value("${movieTableName}")
    private String movieTableName;

    @Value("${movieSearchColumn}")
    private String movieSearchColumn;

    @Value("${movie.table.columns}")
    private String movieTableColumnsStr;

    @Value("${movie.table.insert.columns}")
    private String movieInsertColumnsStr;
//  --------------------------------------------------------------------------------------------------------------------

//  Book Properties
    @Value("${bookTableName}")
    private String bookTableName;

    @Value("${bookSearchColumn}")
    private String bookSearchColumn;

    @Value("${book.table.columns}")
    private String bookTableColumnsStr;

    @Value("${book.table.insert.columns}")
    private String bookInsertColumnsStr;
//  --------------------------------------------------------------------------------------------------------------------

//  FXML Option Properties
    @Value("${gui.label.optionen}")
    private String optionViewName;

    @Value("${gui.views.optionView}")
    private String optionViewFXML;
//  --------------------------------------------------------------------------------------------------------------------

//  FXML Comic Properties
    @Value("${gui.label.comic}")
    private String comicViewName;

    @Value("${gui.views.comicView}")
    private String comicViewFXML;
//  --------------------------------------------------------------------------------------------------------------------

//  FXML Movie Properties
    @Value("${gui.label.movie}")
    private String movieViewName;

    @Value("${gui.views.movieView}")
    private String movieViewFXML;
//  --------------------------------------------------------------------------------------------------------------------

//  FXML Movie Properties
    @Value("${gui.labels.book}")
    private String bookViewName;

    @Value("${gui.views.bookView}")
    private String bookViewFXML;
//  --------------------------------------------------------------------------------------------------------------------

//  InsertColumns
    @Bean
    public List<String> comicInsertColumns(@Value("${comic.table.insert.columns}") String comicInsertColumnsStr) {
        logger.info("comicInsertColumns");
        return Arrays.asList(comicInsertColumnsStr.split(","));
    }
    @Bean
    public List<String> movieInsertColumns(@Value("${movie.table.insert.columns}") String movieInsertColumnsStr) {
        logger.info("movieInsertColumns");
        return Arrays.asList(movieInsertColumnsStr.split(","));
    }
    @Bean
    public List<String> bookInsertColumns(@Value("${book.table.insert.columns}") String bookInsertColumnsStr) {
        logger.info("bookInsertColumns");
        return Arrays.asList(bookInsertColumnsStr.split(","));
    }
//  --------------------------------------------------------------------------------------------------------------------


//  XMLParser
    @Bean
    public XMLParser comicXMLParser() {
        logger.info("comicXMLParser");
        return new XMLParser(comicTableName);
    }

    @Bean
    public XMLParser movieXMLParser() {
        logger.info("movieXMLParser");
        return new XMLParser(movieTableName);
    }

    @Bean
    public XMLParser bookXMLParser() {
        logger.info("bookXMLParser");
        return new XMLParser(bookTableName);
    }
//  --------------------------------------------------------------------------------------------------------------------

//  NewScene
    @Bean
    public NewScene newScene() {
        logger.info("newScene");
        return new NewScene();
    }
//  --------------------------------------------------------------------------------------------------------------------

//  PreparedStatementHelper
    @Bean
    public PreparedStatementHelper preparedStatementHelper() {
        logger.info("preparedStatementHelper");
        return new PreparedStatementHelper();
    }
//  --------------------------------------------------------------------------------------------------------------------

//  ValueNullChecker
    @Bean
    public ValueNullCheckHelper valueNullCheckHelper() {
        logger.info("valueNullCheckHelper");
        return new ValueNullCheckHelper();
    }
//  --------------------------------------------------------------------------------------------------------------------

//  DataXmlExtract
    @Bean
    public DataXmlExtract dataXmlExtract() {
        logger.info("dataXmlExtract");
        return new DataXmlExtract();
    }
//  --------------------------------------------------------------------------------------------------------------------

//   SQLWriteQuery
    @Bean
    public SQLWriteQuery comicSqlWriteQuery() {
        List<String> comicTableColumns = Arrays.asList(comicTableColumnsStr.split(","));
        logger.info("comicSqlWriteQuery");
        return new SQLWriteQuery(comicTableName, comicTableColumns, comicSearchColumn);
    }

    @Bean
    public SQLWriteQuery movieSqlWriteQuery() {
        List<String> movieTableColumns = Arrays.asList(movieTableColumnsStr.split(","));
        logger.info("movieSqlWriteQuery");
        return new SQLWriteQuery(movieTableName, movieTableColumns, movieSearchColumn);
    }

    @Bean
    public SQLWriteQuery bookSqlWriteQuery() {
        List<String> bookTableColumns = Arrays.asList(bookTableColumnsStr.split(","));
        logger.info("bookSqlWriteQuery");
        return new SQLWriteQuery(bookTableName, bookTableColumns, bookSearchColumn);
    }
//  --------------------------------------------------------------------------------------------------------------------

//  DataReadWrite
    @Bean
    public DataReadWrite comicDataReadWrite(XMLParser comicXMLParser,
                                            PreparedStatementHelper preparedStatementHelper) {
        logger.info("comicDataReadWrite");
        return new DataReadWrite(comicTableName, comicXMLParser, preparedStatementHelper);
    }

    @Bean
    public DataReadWrite movieDataReadWrite(XMLParser comicXMLParser,
                                            PreparedStatementHelper preparedStatementHelper) {
        logger.info("movieDataReadWrite");
        return new DataReadWrite(movieTableName, comicXMLParser, preparedStatementHelper);
    }

    @Bean
    public DataReadWrite bookDataReadWrite(XMLParser comicXMLParser,
                                           PreparedStatementHelper preparedStatementHelper) {
        logger.info("bookDataReadWrite");
        return new DataReadWrite(bookTableName, comicXMLParser, preparedStatementHelper);
    }
//  --------------------------------------------------------------------------------------------------------------------

//  TablelInitiatator
    @Bean
    public TableIInitiator tableIInitiator(DataXmlExtract dataXmlExtract) {
        logger.info("tableIInitiator");
        return  new TableIInitiator(dataXmlExtract);
    }
//  --------------------------------------------------------------------------------------------------------------------

//  WarningHelper
    @Bean
    public WarningHelper warningHelper() {
        logger.info("warningHelper");
        return new WarningHelper();
    }
//  --------------------------------------------------------------------------------------------------------------------

//  Search
    @Bean
    public Search comicSearch(XMLParser comicXMlParser, TableIInitiator tableIInitiator,
                              @Value("${comicTableName}") String comicTableName) {
        logger.info("comicSearch");
        return new Search(comicXMlParser, tableIInitiator, comicTableName);
    }

    @Bean
    public Search movieSearch(XMLParser movieXmlParser, TableIInitiator tableIInitiator,
                              @Value("${movieTableName}") String movieTableName) {
        logger.info("movieSearch");
        return new Search(movieXmlParser, tableIInitiator, movieTableName);
    }

    @Bean
    public Search bookSearch(XMLParser movieXmlParser, TableIInitiator tableIInitiator,
                             @Value("${bookTableName}") String bookTableName) {
        logger.info("bookSearch");
        return new Search(movieXmlParser, tableIInitiator, bookTableName);
    }
//  --------------------------------------------------------------------------------------------------------------------

//  Configurator
//    @Bean
//    public OverlordViewControllerConfigurator comicTableConfigurator() {
//        logger.info("comicTableConfigurator");
//        return new ComicViewConfigurator(comicTableName, comicInsertColumns(comicInsertColumnsStr),
//                optionViewName, optionViewFXML ,newScene(), valueNullCheckHelper(),
//                comicSqlWriteQuery(), comicDataReadWrite(comicXMLParser(),
//                preparedStatementHelper()), tableIInitiator(dataXmlExtract()), warningHelper(),
//                comicSearch(comicXMLParser(),tableIInitiator(dataXmlExtract()), comicTableName));
//    }
//
//    @Bean
//    public OverlordViewControllerConfigurator movieTableConfigurator() {
//        logger.info("movieTableConfigurator");
//        return new MovieViewConfigurator(movieTableName, movieInsertColumns(movieInsertColumnsStr),
//                optionViewName, optionViewFXML, newScene(), valueNullCheckHelper(),
//                movieSqlWriteQuery(), movieDataReadWrite(movieXMLParser(),
//                preparedStatementHelper()), tableIInitiator(dataXmlExtract()), warningHelper(),
//                movieSearch(movieXMLParser(),tableIInitiator(dataXmlExtract()), movieTableName));
//    }
//
//    @Bean
//    public OverlordViewControllerConfigurator bookTableConfigurator() {
//        logger.info("bookTableConfigurator");
//        return new BookViewConfigurator(bookTableName,bookInsertColumns(bookInsertColumnsStr),
//                optionViewName, optionViewFXML, newScene(), valueNullCheckHelper(),
//                bookSqlWriteQuery(), bookDataReadWrite(bookXMLParser(),
//                preparedStatementHelper()), tableIInitiator(dataXmlExtract()), warningHelper(),
//                bookSearch(bookXMLParser(),tableIInitiator(dataXmlExtract()), bookTableName));
//    }
//
//    @Bean
//    public OverlordOptionViewConfigurator OptionTableViewConfigurator(@Value("${gui.views.comicView}") String comicViewFXML,
//                                                                      @Value("${gui.views.movieView}") String movieViewFXML,
//                                                                      @Value("${gui.views.bookView}") String bookViewFXML){
//        logger.info("optionViewConfigurator");
//        return new OptionViewConfigurator(newScene(), comicViewName,comicViewFXML, movieViewName,movieViewFXML,
//                bookViewName,bookViewFXML);
//    }
//  --------------------------------------------------------------------------------------------------------------------
}
