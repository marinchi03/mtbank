package sample.osnS;

import javafx.beans.property.SimpleStringProperty;

public class Osnovnoi {

    private final SimpleStringProperty id;

    private  final SimpleStringProperty dog;

    private  final SimpleStringProperty vo;

    private  final SimpleStringProperty nom;

    private  final SimpleStringProperty sum;

    private  final SimpleStringProperty nsum;

    private  final SimpleStringProperty isum;

    private  final SimpleStringProperty date;

    public Osnovnoi(String id, String dog, String vo, String nom, String sum,String nsum,String isum, String date) {
        this.id = new SimpleStringProperty(id);
        this.vo = new SimpleStringProperty(vo);
        this.nom = new SimpleStringProperty(nom);
        this.sum = new SimpleStringProperty(sum);
        this.nsum = new SimpleStringProperty(nsum);
        this.isum = new SimpleStringProperty(isum);
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

    public String getVo() {
        return vo.get();
    }

    public SimpleStringProperty voProperty() {
        return vo;
    }

    public void setVo(String vo) {
        this.vo.set(vo);
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

    public String getNsum() {
        return nsum.get();
    }

    public SimpleStringProperty nsumProperty() {
        return nsum;
    }

    public void setNsum(String nsum) {
        this.nsum.set(nsum);
    }

    public String getIsum() {
        return isum.get();
    }

    public SimpleStringProperty isumProperty() {
        return isum;
    }

    public void setIsum(String isum) {
        this.isum.set(isum);
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
