package sample.osnS;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import sample.DataBaseHeandler;
import sample.plan.PlanS;

public class osnController {

    private ObservableList<Osnovnoi> list = FXCollections.observableArrayList();

    private  String tableName = "osnschet";

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
    private TableView<Osnovnoi> table;

    @FXML
    private TableColumn<Osnovnoi, String> idField;

    @FXML
    private TableColumn<Osnovnoi, String> idnd;

    @FXML
    private TableColumn<Osnovnoi, String> idop;

    @FXML
    private TableColumn<Osnovnoi, String> idns;

    @FXML
    private TableColumn<Osnovnoi, String> idsum;

    @FXML
    private TableColumn<Osnovnoi, String> idnsum;

    @FXML
    private TableColumn<Osnovnoi, String> idisum;

    @FXML
    private TableColumn<Osnovnoi, String> iddate;

    @FXML
    private Button backBut;

    @FXML
    private ComboBox<String> nomdF;

    @FXML
    private TextField sumF;

    @FXML
    private ComboBox<String> opF;



    @FXML
    void initialize() {

        dbHeandler.setForCombobox("dogovor", "nomdog", nomdF);
        dbHeandler.setForCombobox("operac", "name", opF);

        idField.setCellFactory(TextFieldTableCell.<Osnovnoi>forTableColumn());
        idField.setCellValueFactory(new PropertyValueFactory<Osnovnoi, String>("id"));

        idnd.setCellFactory(TextFieldTableCell.<Osnovnoi>forTableColumn());
        idnd.setCellValueFactory(new PropertyValueFactory<Osnovnoi, String>("dog"));

        idop.setCellFactory(TextFieldTableCell.<Osnovnoi>forTableColumn());
        idop.setCellValueFactory(new PropertyValueFactory<Osnovnoi, String>("vo"));

        idns.setCellFactory(TextFieldTableCell.<Osnovnoi>forTableColumn());
        idns.setCellValueFactory(new PropertyValueFactory<Osnovnoi, String>("nom"));

        idsum.setCellFactory(TextFieldTableCell.<Osnovnoi>forTableColumn());
        idsum.setCellValueFactory(new PropertyValueFactory<Osnovnoi, String>("sum"));

        idnsum.setCellFactory(TextFieldTableCell.<Osnovnoi>forTableColumn());
        idnsum.setCellValueFactory(new PropertyValueFactory<Osnovnoi, String>("nsum"));

        idisum.setCellFactory(TextFieldTableCell.<Osnovnoi>forTableColumn());
        idisum.setCellValueFactory(new PropertyValueFactory<Osnovnoi, String>("isum"));

        iddate.setCellFactory(TextFieldTableCell.<Osnovnoi>forTableColumn());
        iddate.setCellValueFactory(new PropertyValueFactory<Osnovnoi, String>("date"));
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

                DataBaseHeandler dbHeandler = new DataBaseHeandler();


                dbHeandler.insertInOsn(dbHeandler.fieldToId("dogovor","nomdog", nomdF.getValue()),
                        dbHeandler.fieldToId("operac","name", opF.getValue()), Double.parseDouble(sumF.getText()));

                sumF.clear();


                dbHeandler.showMesage("Вы успешно добавили запись");
                loadData();

            }
        });


    }

    void loadData(){

        list.clear();

        table.getItems().clear();
        ResultSet result = dbHeandler.getTable("osnschet");
        Osnovnoi obj;
        try {
            while (result.next()) {
                obj = new Osnovnoi(result.getString(1), result.getString(2), result.getString(3), result.getString(4),
                        result.getString(5),result.getString(6),result.getString(7),result.getString(8));
                list.add(obj);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        table.setItems(list);
    }
}
