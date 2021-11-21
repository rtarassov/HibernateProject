package model;

import javax.persistence.*;

@Entity
public class Cage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type")
    private String type;

    @Column(name = "size")
    private int size;

    @ManyToOne
    @JoinColumn(name = "zoo_id")
    private Zoo zoo;

    @Override
    public String toString() {
        return "Cage{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", size=" + size +
                ", zoo=" + zoo +
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Zoo getZoo() {
        return zoo;
    }

    public void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }

    public Cage(String type, int size) {
        this.type = type;
        this.size = size;
    }

    public Cage() {
    }
}
