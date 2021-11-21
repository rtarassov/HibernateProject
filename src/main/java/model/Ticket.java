package model;

import javax.persistence.*;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "type")
    private String type;

    @Column(name = "is_valid")
    private boolean isValid;

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", isValid=" + isValid +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public Ticket(String type, boolean isValid) {
        this.type = type;
        this.isValid = isValid;
    }

    public Ticket() {
    }
}
