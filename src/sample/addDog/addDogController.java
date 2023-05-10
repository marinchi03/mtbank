package sample.addDog;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import sample.DataBaseHeandler;
import sample.clientPac.Client;
import sample.dog.Dogovor;

public class addDogController {

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
    private Button backBut;

    @FXML
    private ComboBox<String> sotrF;

    @FXML
    private TextField datesF;

    @FXML
    private TextField nomF;

    @FXML
    private ComboBox<String> clF;

    @FXML
    private ComboBox<String> psF;

    @FXML
    private ComboBox<String> valF;

    @FXML
    private TextField dateeF;

    @FXML
    private TextField srF;

    @FXML
    private TextField sumF;

    @FXML
    private TextField prF;

    @FXML
    void initialize() {

        dbHeandler.setForCombobox("sotr", "FIO", sotrF);
        dbHeandler.setForCombobox("client", "fam", clF);
        dbHeandler.setForCombobox("plans", "opis", psF);
        dbHeandler.setForCombobox("valuta", "name", valF);

        backBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dbHeandler.openNewScene("/sample/dog/dog.fxml", backBut,"Договор");
            }
        });

        addBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                DataBaseHeandler dbHeandler = new DataBaseHeandler();


               dbHeandler.insertInDog(dbHeandler.fieldToId("sotr","FIO", sotrF.getValue()),
                                     dbHeandler.fieldToId("client","fam", clF.getValue()),
                                     dbHeandler.fieldToId("plans","opis", psF.getValue()),
                                     dbHeandler.fieldToId("valuta","name", valF.getValue()),
                                     nomF.getText(), datesF.getText(), dateeF.getText(), Integer.parseInt(srF.getText()),
                                     Integer.parseInt(sumF.getText()), Double.parseDouble(prF.getText()));

                nomF.clear();
                dateeF.clear();
                datesF.clear();
                srF.clear();
                sumF.clear();
                prF.clear();

                dbHeandler.showMesage("Вы успешно добавили запись");


            }
        });



    }
}
