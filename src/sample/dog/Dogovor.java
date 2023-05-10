package sample.dog;

import javafx.beans.property.SimpleStringProperty;

public class Dogovor {

    private final SimpleStringProperty id;

    private  final SimpleStringProperty sotr;

    private  final SimpleStringProperty cl;

    private  final SimpleStringProperty pl;

    private  final SimpleStringProperty val;

    private  final SimpleStringProperty nom;

    private  final SimpleStringProperty dates;

    private  final SimpleStringProperty datee;

    private  final SimpleStringProperty srok;

    private  final SimpleStringProperty sum;

    private  final SimpleStringProperty proc;

    public Dogovor(String id, String sotr, String cl, String pl, String val, String nom, String dates, String datee, String srok, String sum, String proc) {
        this.id = new SimpleStringProperty(id);
        this.sotr = new SimpleStringProperty(sotr);
        this.cl = new SimpleStringProperty(cl);
        this.pl = new SimpleStringProperty(pl);
        this.val = new SimpleStringProperty(val);
        this.nom = new SimpleStringProperty(nom);
        this.dates = new SimpleStringProperty(dates);
        this.datee = new SimpleStringProperty(datee);
        this.srok = new SimpleStringProperty(srok);
        this.sum = new SimpleStringProperty(sum);
        this.proc = new SimpleStringProperty(proc);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getSotr() {
        return sotr.get();
    }

    public SimpleStringProperty sotrProperty() {
        return sotr;
    }

    public void setSotr(String sotr) {
        this.sotr.set(sotr);
    }

    public String getCl() {
        return cl.get();
    }

    public SimpleStringProperty clProperty() {
        return cl;
    }

    public void setCl(String cl) {
        this.cl.set(cl);
    }

    public String getPl() {
        return pl.get();
    }

    public SimpleStringProperty plProperty() {
        return pl;
    }

    public void setPl(String pl) {
        this.pl.set(pl);
    }

    public String getVal() {
        return val.get();
    }

    public SimpleStringProperty valProperty() {
        return val;
    }

    public void setVal(String val) {
        this.val.set(val);
    }

    public String getNom() {
        return nom.get();
    }

    public SimpleStringProperty nomProperty() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public String getDates() {
        return dates.get();
    }

    public SimpleStringProperty datesProperty() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates.set(dates);
    }

    public String getDatee() {
        return datee.get();
    }

    public SimpleStringProperty dateeProperty() {
        return datee;
    }

    public void setDatee(String datee) {
        this.datee.set(datee);
    }

    public String getSrok() {
        return srok.get();
    }

    public SimpleStringProperty srokProperty() {
        return srok;
    }

    public void setSrok(String srok) {
        this.srok.set(srok);
    }

    public String getSum() {
        return sum.get();
    }

    public SimpleStringProperty sumProperty() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum.set(sum);
    }

    public String getProc() {
        return proc.get();
    }

    public SimpleStringProperty procProperty() {
        return proc;
    }

    public void setProc(String proc) {
        this.proc.set(proc);
    }
}
