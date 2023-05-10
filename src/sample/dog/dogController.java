package sample.dog;

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
import sample.clientPac.Client;

public class dogController {

    private ObservableList<Dogovor> list = FXCollections.observableArrayList();

    private  String tableName = "dogovor";

    final DataBaseHeandler dbHeandler = new DataBaseHeandler();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addBut;

    @FXML
    private Button delBut;

    @FXML
    private TableView<Dogovor> table;

    @FXML
    private TableColumn<Dogovor, String> idField;

    @FXML
    private TableColumn<Dogovor, String> sotrF;

    @FXML
    private TableColumn<Dogovor, String> clF;

    @FXML
    private TableColumn<Dogovor, String> psF;

    @FXML
    private TableColumn<Dogovor, String> valF;

    @FXML
    private TableColumn<Dogovor, String> nomF;

    @FXML
    private TableColumn<Dogovor, String> datesF;

    @FXML
    private TableColumn<Dogovor, String> dateeF;

    @FXML
    private TableColumn<Dogovor, String> srF;

    @FXML
    private TableColumn<Dogovor, String> sumF;

    @FXML
    private TableColumn<Dogovor, String> procF;

    @FXML
    private Button backBut;

    @FXML
    void initialize() {

        idField.setCellFactory(TextFieldTableCell.<Dogovor>forTableColumn());
        idField.setCellValueFactory(new PropertyValueFactory<Dogovor, String>("id"));

        sotrF.setCellFactory(TextFieldTableCell.<Dogovor>forTableColumn());
        sotrF.setCellValueFactory(new PropertyValueFactory<Dogovor, String>("sotr"));

        clF.setCellFactory(TextFieldTableCell.<Dogovor>forTableColumn());
        clF.setCellValueFactory(new PropertyValueFactory<Dogovor, String>("cl"));

        psF.setCellFactory(TextFieldTableCell.<Dogovor>forTableColumn());
        psF.setCellValueFactory(new PropertyValueFactory<Dogovor, String>("pl"));

        valF.setCellFactory(TextFieldTableCell.<Dogovor>forTableColumn());
        valF.setCellValueFactory(new PropertyValueFactory<Dogovor, String>("val"));

        nomF.setCellFactory(TextFieldTableCell.<Dogovor>forTableColumn());
        nomF.setCellValueFactory(new PropertyValueFactory<Dogovor, String>("nom"));

        datesF.setCellFactory(TextFieldTableCell.<Dogovor>forTableColumn());
        datesF.setCellValueFactory(new PropertyValueFactory<Dogovor, String>("dates"));

        dateeF.setCellFactory(TextFieldTableCell.<Dogovor>forTableColumn());
        dateeF.setCellValueFactory(new PropertyValueFactory<Dogovor, String>("datee"));

        srF.setCellFactory(TextFieldTableCell.<Dogovor>forTableColumn());
        srF.setCellValueFactory(new PropertyValueFactory<Dogovor, String>("srok"));

        sumF.setCellFactory(TextFieldTableCell.<Dogovor>forTableColumn());
        sumF.setCellValueFactory(new PropertyValueFactory<Dogovor, String>("sum"));

        procF.setCellFactory(TextFieldTableCell.<Dogovor>forTableColumn());
        procF.setCellValueFactory(new PropertyValueFactory<Dogovor, String>("proc"));

        loadData();

        delBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String pos = ( table.getSelectionModel().getSelectedItem().getId().toString());
                dbHeandler.deleteFromTable(tableName, pos);
                loadData();
            }
        });

        backBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dbHeandler.openNewScene("/sample/depMod/depM.fxml", backBut,"Модуль Депозиты");
            }
        });

        addBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dbHeandler.openNewScene("/sample/addDog/addDog.fxml", addBut,"Добавление");
            }
        });


    }

    void loadData(){

        list.clear();
        table.getItems().clear();
        ResultSet result = dbHeandler.getTable("dogovor");
        Dogovor obj;
        try {
            while (result.next()) {


                obj = new Dogovor(result.getString(1), result.getString(2), result.getString(3),
                        result.getString(4), result.getString(5), result.getString(6), result.getString(7),
                        result.getString(8), result.getString(9), result.getString(10), result.getString(11));

                list.add(obj);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        table.setItems(list);
    }
}
