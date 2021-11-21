package model;

import javax.persistence.*;

@Entity
public class ZooCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @Column(name = "is_new")
    private boolean isNew;

    @ManyToOne
    @JoinColumn(name = "zoo_id")
    private Zoo zoo;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @Override
    public String toString() {
        String zooName = zoo != null ? zoo.getName() : "No zoo assigned";
        String ticketType = ticket != null ? ticket.getType() : "No ticket assigned";
        boolean ticketValidation;
        if (ticket != null) {
            ticketValidation = ticket.isValid();
        } else {
            ticketValidation = false;
        }

        return "Customer ID " + id
                + ", name " + name
                + ", ticket type: " + ticketType
                + ", ticket validation: " + ticketValidation
                + ", zoo Name: " + zooName
                + "\n";
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isNew() {
        return isNew;
    }
    public void setNew(boolean aNew) {
        isNew = aNew;
    }
    public Zoo getZoo() {
        return zoo;
    }
    public void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }
    public Ticket getTicket() {
        return ticket;
    }
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
    public ZooCustomer() {
    }

    public ZooCustomer(String type, String name, boolean isNew) {
        this.type = type;
        this.name = name;
        this.isNew = isNew;
    }
}
