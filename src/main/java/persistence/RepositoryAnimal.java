package persistence;

import model.Animal;
import util.DBUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class RepositoryAnimal {

    private final EntityManager entityManager;

    public RepositoryAnimal() {
        this.entityManager = DBUtil.getEntityManager();
    }

    public void addAnimal(Animal animal) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(animal);
            entityManager.getTransaction().commit();
            System.out.println("Animal added with success.");
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void updateAnimalGrowthStage(int animalId, String growthStage) {
        try {
            entityManager.getTransaction().begin();
            String sql = "UPDATE Animal SET animal_growth_stage = :newStage WHERE id = :animalId";
            entityManager.createQuery(sql)
                    .setParameter("newStage", growthStage)
                    .setParameter("animalId", animalId)
                    .executeUpdate();
            entityManager.getTransaction().commit();
            System.out.println("Growth stage updated successfully");
            entityManager.clear();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public Animal findAnimalById(int animalId) {
        if (entityManager.find(Animal.class, animalId) == null) {
            System.out.println("Did not find an Animal with ID: " + animalId);
        }
        return entityManager.find(Animal.class, animalId);
    }

    public List<Animal> listAllAnimals() {
        List<Animal> list = null;
        try {
            list = entityManager.createQuery("FROM Animal").getResultList();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void deleteAnimal(Animal animal) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.merge(animal));
            entityManager.getTransaction().commit();
            System.out.println("Animal deleted with success.");
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void assignAnimaltoCage(int animalId, int cageId) {
        String sql = "UPDATE Animal SET cage_id = :thisCageId WHERE id = :thisAnimalId";
        try {
            entityManager.getTransaction().begin();
            entityManager.createQuery(sql)
                    .setParameter("thisCageId", cageId)
                    .setParameter("thisAnimalId", animalId)
                    .executeUpdate();
            entityManager.getTransaction().commit();
            System.out.println("Animal assigned to Cage.");
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}