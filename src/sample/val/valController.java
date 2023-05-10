package sample.val;

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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import sample.DataBaseHeandler;

public class valController {

    private ObservableList<Valuta> list = FXCollections.observableArrayList();

    private  String tableName = "valuta";

    final DataBaseHeandler dbHeandler = new DataBaseHeandler();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nameField;

    @FXML
    private Button addBut;

    @FXML
    private Button delBut;

    @FXML
    private TableView<Valuta> table;

    @FXML
    private TableColumn<Valuta, String> idField;

    @FXML
    private TableColumn<Valuta, String> idName;

    @FXML
    private Button backBut;

    @FXML
    void initialize() {

        idField.setCellFactory(TextFieldTableCell.<Valuta>forTableColumn());
        idField.setCellValueFactory(new PropertyValueFactory<Valuta, String>("id"));
        idName.setCellFactory(TextFieldTableCell.<Valuta>forTableColumn());
        idName.setCellValueFactory(new PropertyValueFactory<Valuta, String>("name"));
        loadData();

        backBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dbHeandler.openNewScene("/sample/depMod/depM.fxml", backBut,"Модуль Депозиты");
            }
        });


        delBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String pos = ( table.getSelectionModel().getSelectedItem().getId().toString());
                dbHeandler.deleteFromTable(tableName, pos);
                loadData();
            }
        });


        addBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!nameField.getText().equals(""))
                {
                    dbHeandler.insertInSprav(tableName, nameField.getText());
                    loadData();
                }else
                {
                    dbHeandler.showMesage("Заполните поле");
                }

            }
        });

    }

    void loadData(){

        list.clear();
        nameField.clear();
        table.getItems().clear();
        ResultSet result = dbHeandler.getTable(tableName);
        Valuta obj;
        try {
            while (result.next()) {
                obj = new Valuta(result.getString(1), result.getString(2));
                list.add(obj);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        table.setItems(list);
    }
}
