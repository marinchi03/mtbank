package sample.procS;

import javafx.beans.property.SimpleStringProperty;

public class ProcentS {

    private final SimpleStringProperty id;

    private  final SimpleStringProperty dog;

    private  final SimpleStringProperty vo;

    private  final SimpleStringProperty nom;

    private  final SimpleStringProperty psum;

    private  final SimpleStringProperty nsum;

    private  final SimpleStringProperty date;

    private  final SimpleStringProperty proc;

    public ProcentS(String id, String dog, String vo, String nom, String psum,String nsum,String date, String proc) {
        this.id = new SimpleStringProperty(id);
        this.vo = new SimpleStringProperty(vo);
        this.nom = new SimpleStringProperty(nom);
        this.psum = new SimpleStringProperty(psum);
        this.nsum = new SimpleStringProperty(nsum);
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

    public String getPsum() {
        return psum.get();
    }

    public SimpleStringProperty psumProperty() {
        return psum;
    }

    public void setPsum(String psum) {
        this.psum.set(psum);
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

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
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
