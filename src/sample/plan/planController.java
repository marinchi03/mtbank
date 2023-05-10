package sample.plan;

import java.net.URL;
import java.sql.PreparedStatement;
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
import sample.val.Valuta;

public class planController {

    private ObservableList<PlanS> list = FXCollections.observableArrayList();

    private  String tableName = "plans";

    final DataBaseHeandler dbHeandler = new DataBaseHeandler();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nomoF;

    @FXML
    private Button addBut;

    @FXML
    private Button delBut;

    @FXML
    private TableView<PlanS> table;

    @FXML
    private TableColumn<PlanS, String> idField;

    @FXML
    private TableColumn<PlanS, String> nomo;

    @FXML
    private TableColumn<PlanS, String> nomp;

    @FXML
    private TableColumn<PlanS, String> op;

    @FXML
    private Button backBut;

    @FXML
    private TextField nomP;

    @FXML
    private TextField opF;

    @FXML
    void initialize() {


        idField.setCellFactory(TextFieldTableCell.<PlanS>forTableColumn());
        idField.setCellValueFactory(new PropertyValueFactory<PlanS, String>("id"));

        nomo.setCellFactory(TextFieldTableCell.<PlanS>forTableColumn());
        nomo.setCellValueFactory(new PropertyValueFactory<PlanS, String>("nomo"));

        nomp.setCellFactory(TextFieldTableCell.<PlanS>forTableColumn());
        nomp.setCellValueFactory(new PropertyValueFactory<PlanS, String>("nomp"));

        op.setCellFactory(TextFieldTableCell.<PlanS>forTableColumn());
        op.setCellValueFactory(new PropertyValueFactory<PlanS, String>("opis"));

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
                if( !nomoF.getText().equals("") & !nomP.getText().equals("") & !opF.getText().equals(""))
                {
                    DataBaseHeandler dbHeandler = new DataBaseHeandler();


                    String insert = "Insert into plans(nomosn, nomproc, opis) values (?,?,?)";

                    try {

                        PreparedStatement pr = dbHeandler.getDbConnection().prepareStatement(insert);

                        pr.setInt(1, Integer.parseInt(nomoF.getText()));
                        pr.setInt(2, Integer.parseInt(nomP.getText()));
                        pr.setString(3, opF.getText());


                        pr.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    nomoF.clear();
                    nomP.clear();
                    opF.clear();


                    dbHeandler.showMesage("Вы успешно добавили запись");

                }else
                {
                    dbHeandler.showMesage("Заполните поле");
                }

                loadData();

            }
        });


    }

    void loadData(){

        list.clear();

        table.getItems().clear();
        ResultSet result = dbHeandler.getTable(tableName);
        PlanS obj;
        try {
            while (result.next()) {
                obj = new PlanS(result.getString(1), result.getString(2), result.getString(3), result.getString(4));
                list.add(obj);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        table.setItems(list);
    }
}
