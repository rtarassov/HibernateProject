package model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private int id;

    @Column(name = "title")
    @NotNull
    private String title;

    @Column(name = "name", nullable = false)
    @NotNull
    private String name;

    @Column(name = "salary", nullable = false)
    private int salary;

    @ManyToOne
    @JoinColumn(name = "zoo_id")
    private Zoo zoo;

    @Override
    public String toString() {
        return "Name: " + name
                + ", ID: " + id
                + "\n";
    }

    public Employee(String title, String name, int salary) {
        this.title = title;
        this.name = name;
        this.salary = salary;
    }

    public Employee() {
    }
}
