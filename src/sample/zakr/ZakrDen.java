package sample.zakr;

import javafx.beans.property.SimpleStringProperty;

public class ZakrDen {

    private final SimpleStringProperty id;

    private  final SimpleStringProperty dog;

    private  final SimpleStringProperty nom;

    private  final SimpleStringProperty sum;

    private  final SimpleStringProperty proc;

    private  final SimpleStringProperty date;

    public ZakrDen(String id, String dog, String nom, String sum,String proc,String date) {
        this.id = new SimpleStringProperty(id);
        this.nom = new SimpleStringProperty(nom);
        this.sum = new SimpleStringProperty(sum);
        this.proc = new SimpleStringProperty(proc);
        this.date = new SimpleStringProperty(date);
        this.dog = new SimpleStringProperty(dog);
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

    public String getDog() {
        return dog.get();
    }

    public SimpleStringProperty dogProperty() {
        return dog;
    }

    public void setDog(String dog) {
        this.dog.set(dog);
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

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }
}
