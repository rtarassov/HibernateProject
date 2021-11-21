package persistence;

import model.Cage;
import util.DBUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class RepositoryCage {
    private final EntityManager entityManager;
    public RepositoryCage() {
        this.entityManager = DBUtil.getEntityManager();
    }

    public void addCage(Cage cage) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(cage);
            entityManager.getTransaction().commit();
            System.out.println("Cage added with success.");
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void removeCage(Cage cage) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.merge(cage));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void updateCageSize(int cageId, int newSize) {
        try {
            entityManager.getTransaction().begin();

            String sql = "UPDATE Cage SET size = :cageSize WHERE id = :cageId";
            entityManager
                    .createQuery(sql)
                    .setParameter("cageSize", newSize)
                    .setParameter("cageId", cageId)
                    .executeUpdate();
            entityManager.getTransaction().commit();
            System.out.println("Size updated successfully");
            entityManager.clear();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public Cage findCageById(int cageId) {
        if (entityManager.find(Cage.class, cageId) == null) {
            System.out.println("Did not find a Cage with ID: " + cageId);
        }
        return entityManager.find(Cage.class, cageId);
    }

    public List<Cage> listAllCages() {
        List<Cage> list = null;
        try {
            list = entityManager.createQuery("FROM Cage").getResultList();
            if (list.isEmpty()) {
                System.out.println("There are no cages.");
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}