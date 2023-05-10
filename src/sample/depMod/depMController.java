package sample.depMod;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.DataBaseHeandler;

public class depMController {
    final DataBaseHeandler dbHeandler = new DataBaseHeandler();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button dog;

    @FXML
    private Button pls;

    @FXML
    private Button osns;

    @FXML
    private Button prs;

    @FXML
    private Button zd;

    @FXML
    private Button back;

    @FXML
    private Button vo;

    @FXML
    private Button val;

    @FXML
    void initialize() {
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dbHeandler.openNewScene("/sample/mainForm/mainF.fxml", back,"Главная форма");
            }
        });

        val.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dbHeandler.openNewScene("/sample/val/val.fxml", back,"Валюта");
            }
        });

        vo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dbHeandler.openNewScene("/sample/operac/op.fxml", back,"Операции");
            }
        });

        pls.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dbHeandler.openNewScene("/sample/plan/plan.fxml", back,"План счетов");
            }
        });

        dog.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dbHeandler.openNewScene("/sample/dog/dog.fxml", dog,"Договор");
            }
        });

        osns.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dbHeandler.openNewScene("/sample/osnS/osn.fxml", osns,"Основной счет");
            }
        });

        dog.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dbHeandler.openNewScene("/sample/dog/dog.fxml", dog,"Договор");
            }
        });

        prs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dbHeandler.openNewScene("/sample/procS/prs.fxml", prs,"Процентный счет");
            }
        });

        prs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dbHeandler.openNewScene("/sample/procS/prs.fxml", prs,"Процентный счет");
            }
        });


        zd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dbHeandler.openNewScene("/sample/zakr/zak.fxml", zd,"Закрытие дня");
            }
        });

    }
}
