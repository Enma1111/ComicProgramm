package jan.comic.Gui;

import jan.comic.Data.DataReadWrite;
import jan.comic.Data.DataXmlExtract;
import jan.comic.SQLServices.SQLWriteQuery;
import jan.comic.Service.*;
import jan.comic.TableService.FillTableView;
import jan.comic.TableService.TableIInitiator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class BookController {
    @FXML
    private Button BtnDelete;
    @FXML
    private Button BtnBackToOptions;
    @FXML
    private TextField TxtDeleteID;
    @FXML
    private TextField TxtSearch;
    @FXML
    private CheckBox CkBxSureDelete;
    @FXML
    private Button BtnBookSave;
    @FXML
    private TextField TxtPublisher;
    @FXML
    private Button BtnSearch;
    @FXML
    private TextField TxtBox;
    @FXML
    private TextField TxtBookName;
    @FXML
    private TableColumn<FillTableView.DataItem, String> colPlace;
    @FXML
    private TableColumn<FillTableView.DataItem, String> colBook;
    @FXML
    private TableColumn<FillTableView.DataItem, String> colPublisher;
    @FXML
    private TableColumn<FillTableView.DataItem, Integer> colID;
    @FXML
    private TableView<FillTableView.DataItem> tblBook;
    @FXML
    private Button BtnBackToTable;

    String table = "Book_Table";
    NewScene newScene = new NewScene();
    XMLParser xmlParser = new XMLParser();
    DataReadWrite dataReadWrite = new DataReadWrite(xmlParser, table);
    DataXmlExtract dataXmlExtract = new DataXmlExtract();
    SQLWriteQuery sqlWriteQuery = new SQLWriteQuery(table);
    TableIInitiator tableIInitiator = new TableIInitiator(dataXmlExtract);
    WarningHelper warningHelper = new WarningHelper();



    @FXML
    public void initialize(){
        tableIInitiator.initialize(tblBook, table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));
    }

    @FXML
    public void SaveBook(ActionEvent actionEvent) {
        String[] val = new String[3];
        val[0] = TxtBookName.getText();
        val[1] = TxtBox.getText();
        val[2] = TxtPublisher.getText();

//        for (int i = 0; i < val.length; i++) {
//            if (val[i].isEmpty()) {
//                val[i] = "";
//            }
//        }

        dataReadWrite.dataWrite(sqlWriteQuery.saveQuery(val));
        tableIInitiator.initialize(tblBook, table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));
    }

    @FXML
    public void BookDelete(ActionEvent actionEvent) {
        String id = TxtDeleteID.getText();
        if (CkBxSureDelete.isSelected()){
            dataReadWrite.dataDelete(sqlWriteQuery.deleteQuery(id));
            tableIInitiator.initialize(tblBook, table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));
            CkBxSureDelete.setSelected(false);
        }else{
            warningHelper.deleteWarning();
        }
    }

    @FXML
    public void BookSearch(ActionEvent actionEvent) {
    }

    @FXML
    public void BackToOptions(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) BtnBackToOptions.getScene().getWindow();
        try {
            newScene.newScene("option-view.fxml", stage, 200, 200, "Optionen");
        } catch (IOException e) {
            throw new IOException(e);
        }
    }


    @FXML
    public void BackToMainTable(ActionEvent actionEvent) {
        tableIInitiator.initialize(tblBook, table, dataReadWrite.dataRead(sqlWriteQuery.readQuery(table)));
    }
}
