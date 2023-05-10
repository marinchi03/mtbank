package sample.procS;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import sample.DataBaseHeandler;
import sample.osnS.Osnovnoi;

public class prsController {

    private ObservableList<ProcentS> list = FXCollections.observableArrayList();

    private  String tableName = "procschet";

    final DataBaseHeandler dbHeandler = new DataBaseHeandler();


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<ProcentS> table;

    @FXML
    private TableColumn<ProcentS, String> idField;

    @FXML
    private TableColumn<ProcentS, String> idnd;

    @FXML
    private TableColumn<ProcentS, String> idop;

    @FXML
    private TableColumn<ProcentS, String> idns;

    @FXML
    private TableColumn<ProcentS, String> idsum;

    @FXML
    private TableColumn<ProcentS, String> idnsum;

    @FXML
    private TableColumn<ProcentS, String> iddate;

    @FXML
    private TableColumn<ProcentS, String> iddoh;

    @FXML
    private Button backBut;

    @FXML
    void initialize() {
        idField.setCellFactory(TextFieldTableCell.<ProcentS>forTableColumn());
        idField.setCellValueFactory(new PropertyValueFactory<ProcentS, String>("id"));

        idnd.setCellFactory(TextFieldTableCell.<ProcentS>forTableColumn());
        idnd.setCellValueFactory(new PropertyValueFactory<ProcentS, String>("dog"));

        idop.setCellFactory(TextFieldTableCell.<ProcentS>forTableColumn());
        idop.setCellValueFactory(new PropertyValueFactory<ProcentS, String>("vo"));

        idns.setCellFactory(TextFieldTableCell.<ProcentS>forTableColumn());
        idns.setCellValueFactory(new PropertyValueFactory<ProcentS, String>("nom"));

        idsum.setCellFactory(TextFieldTableCell.<ProcentS>forTableColumn());
        idsum.setCellValueFactory(new PropertyValueFactory<ProcentS, String>("psum"));

        idnsum.setCellFactory(TextFieldTableCell.<ProcentS>forTableColumn());
        idnsum.setCellValueFactory(new PropertyValueFactory<ProcentS, String>("nsum"));

        iddoh.setCellFactory(TextFieldTableCell.<ProcentS>forTableColumn());
        iddoh.setCellValueFactory(new PropertyValueFactory<ProcentS, String>("proc"));

        iddate.setCellFactory(TextFieldTableCell.<ProcentS>forTableColumn());
        iddate.setCellValueFactory(new PropertyValueFactory<ProcentS, String>("date"));
        loadData();

        backBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dbHeandler.openNewScene("/sample/depMod/depM.fxml", backBut,"Модуль Депозиты");
            }
        });


    }

    void loadData(){

        list.clear();

        table.getItems().clear();
        ResultSet result = dbHeandler.getTable(tableName);
        ProcentS obj;
        try {
            while (result.next()) {
                obj = new ProcentS(result.getString(1), result.getString(2), result.getString(3), result.getString(4),
                        result.getString(5),result.getString(6),result.getString(7),result.getString(8));
                list.add(obj);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        table.setItems(list);
    }
}
