package sample.operac;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Vladimir on 28.12.2018.
 */
public class Operacia {
    private final SimpleStringProperty id;

    private  final SimpleStringProperty name;

    public Operacia(String id, String name) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
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

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
