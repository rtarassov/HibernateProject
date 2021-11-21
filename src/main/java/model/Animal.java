package model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int animalId;

    @Column(name = "type")
    private String type;

    @Column(name = "animal_growth_stage")
    private String animalGrowthStage;

    @Column(name = "date_of_register")
    private LocalDate dateOfRegister;

    @ManyToOne
    @JoinColumn(name = "cage_id")
    private Cage cage;

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + animalId +
                ", type='" + type + '\'' +
                ", animalGrowthStage='" + animalGrowthStage + '\'' +
                ", dateOfRegister=" + dateOfRegister +
                ", cage=" + cage +
                '}';
    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAnimalGrowthStage() {
        return animalGrowthStage;
    }

    public void setAnimalGrowthStage(String animalGrowthStage) {
        this.animalGrowthStage = animalGrowthStage;
    }

    public Cage getCage() {
        return cage;
    }

    public void setCage(Cage cage) {
        this.cage = cage;
    }

    public Animal(String type, String animalGrowthStage, LocalDate dateOfRegister) {
        this.type = type;
        this.animalGrowthStage = animalGrowthStage;
        this.dateOfRegister = dateOfRegister;
    }

    public Animal() {
    }
}
