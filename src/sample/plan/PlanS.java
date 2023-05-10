package sample.plan;


import javafx.beans.property.SimpleStringProperty;

public class PlanS {

    private final SimpleStringProperty id;

    private  final SimpleStringProperty nomo;

    private  final SimpleStringProperty nomp;

    private  final SimpleStringProperty opis;

    public PlanS(String id, String nomo, String nomp, String opis ) {
        this.id = new SimpleStringProperty(id);
        this.nomo = new SimpleStringProperty(nomo);
        this.nomp = new SimpleStringProperty(nomp);
        this.opis = new SimpleStringProperty(opis);
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

    public String getNomo() {
        return nomo.get();
    }

    public SimpleStringProperty nomoProperty() {
        return nomo;
    }

    public void setNomo(String nomo) {
        this.nomo.set(nomo);
    }

    public String getNomp() {
        return nomp.get();
    }

    public SimpleStringProperty nompProperty() {
        return nomp;
    }

    public void setNomp(String nomp) {
        this.nomp.set(nomp);
    }

    public String getOpis() {
        return opis.get();
    }

    public SimpleStringProperty opisProperty() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis.set(opis);
    }
}
