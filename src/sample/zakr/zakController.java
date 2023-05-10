package sample.zakr;

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
import sample.procS.ProcentS;

public class zakController {

    private ObservableList<ZakrDen> list = FXCollections.observableArrayList();

    private  String tableName = "zakrdnya";

    final DataBaseHeandler dbHeandler = new DataBaseHeandler();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<ZakrDen> table;

    @FXML
    private TableColumn<ZakrDen, String> idField;

    @FXML
    private TableColumn<ZakrDen, String> idnd;

    @FXML
    private TableColumn<ZakrDen, String> idns;

    @FXML
    private TableColumn<ZakrDen, String> idsum;

    @FXML
    private TableColumn<ZakrDen, String> idproc;

    @FXML
    private TableColumn<ZakrDen, String> iddate;

    @FXML
    private Button backBut;

    @FXML
    private Button zakr;

    @FXML
    void initialize() {

        idField.setCellFactory(TextFieldTableCell.<ZakrDen>forTableColumn());
        idField.setCellValueFactory(new PropertyValueFactory<ZakrDen, String>("id"));

        idnd.setCellFactory(TextFieldTableCell.<ZakrDen>forTableColumn());
        idnd.setCellValueFactory(new PropertyValueFactory<ZakrDen, String>("dog"));

        idns.setCellFactory(TextFieldTableCell.<ZakrDen>forTableColumn());
        idns.setCellValueFactory(new PropertyValueFactory<ZakrDen, String>("nom"));

        idsum.setCellFactory(TextFieldTableCell.<ZakrDen>forTableColumn());
        idsum.setCellValueFactory(new PropertyValueFactory<ZakrDen, String>("sum"));

        idproc.setCellFactory(TextFieldTableCell.<ZakrDen>forTableColumn());
        idproc.setCellValueFactory(new PropertyValueFactory<ZakrDen, String>("proc"));

        iddate.setCellFactory(TextFieldTableCell.<ZakrDen>forTableColumn());
        iddate.setCellValueFactory(new PropertyValueFactory<ZakrDen, String>("date"));

        loadData();

        backBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dbHeandler.openNewScene("/sample/depMod/depM.fxml", backBut,"Модуль Депозиты");
            }
        });

        zakr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {



                DataBaseHeandler dbHeandler = new DataBaseHeandler();

                if(dbHeandler.CheckZakr()){
                    dbHeandler.showMesage("Функцию закрытие дня можно использовать только 1 раз в день");
                }else {

                    dbHeandler.ZakrDay();

                    dbHeandler.showMesage("День закрыт");
                    loadData();
                }

            }
        });


    }

    void loadData(){

        list.clear();

        table.getItems().clear();
        ResultSet result = dbHeandler.getTable(tableName);
        ZakrDen obj;
        try {
            while (result.next()) {
                obj = new ZakrDen(result.getString(1), result.getString(2), result.getString(3), result.getString(4),
                        result.getString(5),result.getString(6));
                list.add(obj);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        table.setItems(list);
    }
}
