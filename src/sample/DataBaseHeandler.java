package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.ArrayList;
import sample.dog.Dogovor;
import java.util.List;

import sample.osnS.Osnovnoi;
import sample.procS.ProcentS;
import sample.zakr.ZakrDen;

import java.util.Calendar;

import java.io.IOException;
import java.sql.*;

public class DataBaseHeandler extends Configs {


    Connection dbConnection;


    public ResultSet getUser(User user){
        ResultSet reSet = null;
        String query = "SELECT * FROM sotr where log = '" + user.getLog() + "' and pas = '" + user.getPas() + "'" ;
        try {
            PreparedStatement pr = getDbConnection().prepareStatement(query);
            reSet = pr.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return  reSet;
    }






    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {

        String connectionString  = "jdbc:mysql://" + hostName + ":"
                + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, userName, password);

        return  dbConnection;

    }

    public void insertInDog(int id_s, int id_c, int id_pl, int id_val, String nom, String dates,
                            String datee, int sr, int sum, double proc){

        DataBaseHeandler dbHeandler = new DataBaseHeandler();
        String sql = "Select count(*) as кол from dogovor where id_cl = " + id_c;
        ResultSet reSet = null;
        int col = 0;

        try {
            PreparedStatement pr = getDbConnection().prepareStatement(sql);
            reSet = pr.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            while (reSet.next()) {
                col = reSet.getInt("кол");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        col++;
        String col1 = "";

        if (col < 10) {
            col1 = "00" + Integer.toString(col);
        }
        if (col >= 10 && col < 100) {
            col1 = "0" + Integer.toString(col);
        }
        if (col >= 100) {
            col1 = Integer.toString(col);
        }

        nom = nom + col1;

        int nom1 = Integer.parseInt(nom);

        sql = "Insert into dogovor(id_sotr, id_cl, id_pl, id_val, nomdog, dates, datee, srok, summa, proc) values (?,?,?,?,?,?,?,?,?,?)";

        try {

            PreparedStatement pr = dbHeandler.getDbConnection().prepareStatement(sql);

            pr.setInt(1, id_s);
            pr.setInt(2, id_c);
            pr.setInt(3, id_pl);
            pr.setInt(4, id_val);
            pr.setInt(5, nom1);
            pr.setString(6, dates);
            pr.setString(7, datee);
            pr.setInt(8, sr);
            pr.setInt(9, sum);
            pr.setDouble(10, proc);
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        sql = "Select id from dogovor where nomdog = " + nom1;

        int idd = 0;
        try {
            PreparedStatement pr = getDbConnection().prepareStatement(sql);
            reSet = pr.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            while (reSet.next()) {
                idd = reSet.getInt("id");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        sql = "Select nomosn, nomproc from plans where id = " + id_pl;

        try {
            PreparedStatement pr = getDbConnection().prepareStatement(sql);
            reSet = pr.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        int nomosn = 0;
        int nomproc = 0;

        try {
            while (reSet.next()) {
                nomosn = reSet.getInt("nomosn");
                nomproc = reSet.getInt("nomproc");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        String nomosn1 = Integer.toString(nomosn) + nom1;
        String nomproc1 = Integer.toString(nomproc) + nom1;

        sql = "Insert into osnschet(id_dog, id_vo, noms, sum, newsum, itogsum, date) values (?,?,?,?,?,?,?)";

        try {

            PreparedStatement pr = dbHeandler.getDbConnection().prepareStatement(sql);

            pr.setInt(1, idd);
            pr.setInt(2, id_val);
            pr.setString(3, nom);
            pr.setInt(4, sum);
            pr.setInt(5, nomosn);
            pr.setInt(6, sum);
            pr.setString(7, dates);

            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        double sum1 = (double) sum;
        sql = "Insert into procschet(id_dog, id_vo, nomp, preds, nows, date, proc) values (?,?,?,?,?,?,?)";

        try {

            PreparedStatement pr = dbHeandler.getDbConnection().prepareStatement(sql);

            pr.setInt(1, idd);
            pr.setInt(2, id_val);
            pr.setString(3, nom);
            pr.setDouble(4, sum1);
            pr.setDouble(5, 0);
            pr.setString(6, dates);
            pr.setDouble(7, proc);

            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void insertInOsn(int id_d, int id_o, Double newsum) {
        DataBaseHeandler dbHeandler = new DataBaseHeandler();
        String sql = "Select * from osnschet where id_dog = " + id_d + " order by id desc Limit 1 ";

        ResultSet rs = null;
        int col = 0;

        double starsum = 0.0;
        String noms = "";
        Date data = null;

        try {
            PreparedStatement pr = getDbConnection().prepareStatement(sql);
            rs = pr.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            while (rs.next()) {

                starsum = rs.getDouble("itogsum");
                noms = rs.getString("noms");
                data = rs.getDate("date");
            }
        } catch (SQLException e){
             e.printStackTrace();
         }


        double sumpr = starsum;
        Double itsum = 0.0;
        if (id_o == 1) {
            itsum = starsum + newsum;
        } else {
            itsum = starsum - newsum;
        }


        java.sql.Date nowDate = new java.sql.Date((new java.util.Date()).getTime());

        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        int monn = cal.get(Calendar.MONTH);
        monn++;
        int dayn = cal.get(Calendar.DAY_OF_MONTH);
        int yern = cal.get(Calendar.YEAR);

        cal = Calendar.getInstance();
        cal.setTime(data);
        int monp = cal.get(Calendar.MONTH);
        monp++;
        int dayp = cal.get(Calendar.DAY_OF_MONTH);
        int yerp = cal.get(Calendar.YEAR);

        int days = ((yern - yerp) * 365) + ((monn - monp) * 30) + (dayn - dayp);

        sql = "Select * from dogovor where id = " + id_d;

        try {
            PreparedStatement pr = getDbConnection().prepareStatement(sql);
            rs = pr.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Double proc = 0.0;

        try {
            while (rs.next()) {
                proc = (rs.getDouble("proc"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        double nproc = proc / 365;

        double prib = (sumpr / 100) * (days * nproc);
        itsum += prib;

        sql = "Insert into osnschet(id_dog, id_vo, noms, sum, newsum, itogsum, date) values (?,?,?,?,?,?,?)";

        try {

            PreparedStatement pr = dbHeandler.getDbConnection().prepareStatement(sql);

            pr.setInt(1, id_d);
            pr.setInt(2, id_o);
            pr.setString(3, noms);
            pr.setDouble(4, starsum);
            pr.setDouble(5, newsum);
            pr.setDouble(6, itsum);
            pr.setDate(7, nowDate);

            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        sql = "Select * from procschet where id_dog = " + id_d;

        try {
            PreparedStatement pr = getDbConnection().prepareStatement(sql);
            rs = pr.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String procn = "";

        try {
            while (rs.next()) {
                procn = (rs.getString("nomp"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }


        sql = "Insert into procschet(id_dog, id_vo, nomp, preds, nows, date, proc) values (?,?,?,?,?,?,?)";

        try {

            PreparedStatement pr = dbHeandler.getDbConnection().prepareStatement(sql);

            pr.setInt(1, id_d);
            pr.setInt(2, id_o);
            pr.setString(3, procn);
            pr.setDouble(4, sumpr);
            pr.setDouble(5, newsum);
            pr.setDate(6, nowDate);
            pr.setDouble(7, prib);

            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void ZakrDay(){

        DataBaseHeandler dbHeandler = new DataBaseHeandler();
        java.sql.Date nowDate = new java.sql.Date((new java.util.Date()).getTime());
        String sql = "Select * from dogovor";

        ResultSet rs = null;



        try {
            PreparedStatement pstm = getDbConnection().prepareStatement(sql);
            rs = pstm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        List<Dogovor> list = new ArrayList<Dogovor>();

        try {
            while (rs.next()) {

                Dogovor obj = new Dogovor(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11));

                list.add(obj);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        for (int i = 0; i < list.size(); i++) {

            dbHeandler.insertInOsn(Integer.parseInt(list.get(i).getId()), 1, 0.0);

            sql = "Select * from osnschet where noms = '" + list.get(i).getNom()
                    + "'";

            try {
                PreparedStatement pstm = getDbConnection().prepareStatement(sql);
                rs = pstm.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            Osnovnoi obj1 = null;

            try {
                while (rs.next()) {
                    obj1 = new Osnovnoi(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));


                }
            } catch (SQLException e){
                System.out.println(e);
            }

            sql = "Insert into zakrdnya(id_dog, nom, sum, date) values (?,?,?,?)";

            try {

                PreparedStatement pstm = dbHeandler.getDbConnection().prepareStatement(sql);

                pstm.setInt(1, Integer.parseInt(list.get(i).getId()));
                pstm.setString( 2, obj1.getNom());
                pstm.setDouble(3, Double.parseDouble(obj1.getIsum()));
                pstm.setDate(4, nowDate);

                pstm.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            } catch (ClassNotFoundException e) {
                System.out.println(e);
            }


            sql = "Select * from procschet where nomp = '" + list.get(i).getNom()
                    + "' order by nows desc Limit 1 ";

            try {
                PreparedStatement pstm = getDbConnection().prepareStatement(sql);
                rs = pstm.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            ProcentS obj2 = null;

            try {
                while (rs.next()) {

                    obj2 = new ProcentS(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));

                }
            } catch (SQLException e){
                e.printStackTrace();
            }

            sql = "Insert into zakrdnya(id_dog, nom, proc, date) values (?,?,?,?)";

            try {

                PreparedStatement pstm = dbHeandler.getDbConnection().prepareStatement(sql);

                pstm.setInt(1, Integer.parseInt(list.get(i).getId()));
                pstm.setString(2, obj2.getNom());
                pstm.setDouble(3, Double.parseDouble(obj2.getProc()));
                pstm.setDate(4, nowDate);

                pstm.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }




    }

    public boolean CheckZakr(){
        DataBaseHeandler dbHeandler = new DataBaseHeandler();
        String sql = "Select * from zakrdnya  ORDER BY `date` DESC LIMIT 1";
        ResultSet rs = null;
        boolean flag = false;

        try {
            PreparedStatement pstm = getDbConnection().prepareStatement(sql);
            rs = pstm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ZakrDen obj = null;

        try {
            while (rs.next()) {

                obj = new ZakrDen(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5),rs.getString(6));
            }
        } catch (SQLException e){
            return false;
        }


        java.sql.Date nowDate = new java.sql.Date((new java.util.Date()).getTime());

        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        int monn = cal.get(Calendar.MONTH);
        monn++;
        int dayn = cal.get(Calendar.DAY_OF_MONTH);
        int yern = cal.get(Calendar.YEAR);

        cal = Calendar.getInstance();
        if (obj!=null){
        cal.setTime(Date.valueOf(obj.getDate()));
        int monp = cal.get(Calendar.MONTH);
        monp++;
        int dayp = cal.get(Calendar.DAY_OF_MONTH);
        int yerp = cal.get(Calendar.YEAR);

        if (monn == monp && dayn == dayp && yern == yerp) {
            flag = true;
        }

        return flag;
        }else return false;
    }

    public void insertInSprav(String table, String val){

        DataBaseHeandler dbHeandler = new DataBaseHeandler();
        String insert = "insert into " + table+ "(name) values('" + val + "')";

        try {
            PreparedStatement pr = dbHeandler.getDbConnection().prepareStatement(insert);
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void insertInThree(String table,String field1, String val1, String field2, String val2){

        DataBaseHeandler dbHeandler = new DataBaseHeandler();
        String insert = "insert into " + table+ "("+field1+","+field2+") values('" + val1 + "', '"+val2+"')";

//        System.out.println("insert = " + insert);

        try {
            PreparedStatement pr = dbHeandler.getDbConnection().prepareStatement(insert);
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void changeSprav(String table,String id, String val){

        DataBaseHeandler dbHeandler = new DataBaseHeandler();
        String insert = "UPDATE " + table+ " set name = '" + val + "' where id =" + id;

        try {
            PreparedStatement pr = dbHeandler.getDbConnection().prepareStatement(insert);
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void changeThree(String table,String id, String field1, String val1, String field2, String val2){

        DataBaseHeandler dbHeandler = new DataBaseHeandler();
        String insert = "UPDATE " + table+ " set "+field1+" = '" + val1 + "',"+field2+"='"+val2+"' where id =" + id;

        try {
            PreparedStatement pr = dbHeandler.getDbConnection().prepareStatement(insert);
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }




    public  void deleteFromTable(String table, String id){

        DataBaseHeandler dbHeandler = new DataBaseHeandler();
        String insert = "Delete from " + table+ " where id = " + id;

        try {
            PreparedStatement pr = dbHeandler.getDbConnection().prepareStatement(insert);
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        showMesage("Запись удалена");

    }

    public ResultSet getTable(String table){
        ResultSet reSet = null;
        String query = "SELECT * FROM " + table;
        try {
            PreparedStatement pr = getDbConnection().prepareStatement(query);
            reSet = pr.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return  reSet;
    }



    public ResultSet getTableId(String table, String id){
        ResultSet reSet = null;
        String query = "SELECT * FROM " + table + " where Код = " + id;
        try {
            PreparedStatement pr = getDbConnection().prepareStatement(query);
            reSet = pr.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return  reSet;
    }


    public String getSTableId(String table, String id){
        ResultSet reSet = null;
        String query = "SELECT name FROM " + table + " where id = " + id;
        try {
            PreparedStatement pr = getDbConnection().prepareStatement(query);
            reSet = pr.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String s = "";
        try {
            while (reSet.next()) {

                s = reSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }





    public void setForCombobox(String table, String field, ComboBox<String> cb){
        ResultSet reSet = null;
        String query = "SELECT " + field + " FROM " + table;
        try {

            PreparedStatement pr = getDbConnection().prepareStatement(query);
            reSet = pr.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            while (reSet.next()) {
                cb.getItems().addAll(reSet.getString(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public int fieldToId(String table, String field, String value){
        ResultSet reSet = null;

        String query = "SELECT id  FROM " + table + " where  " + field + " = '" + value + "'";

        try {

            PreparedStatement pr = getDbConnection().prepareStatement(query);
            reSet = pr.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        int res = 0;

        try {
            reSet.next();
            res = reSet.getInt(1);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  res;
    }



    public void showMesage(String mess){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Уведомление");
        alert.setHeaderText(null);
        alert.setContentText(mess);
        alert.showAndWait();
    }

    public void openNewScene(String window, Button but, String title){



        Stage stage = (Stage) but.getScene().getWindow();
        // do what you have to do
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(window));
        try{
            Parent root1 = (Parent) fxmlLoader.load();


            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(title);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }




}
