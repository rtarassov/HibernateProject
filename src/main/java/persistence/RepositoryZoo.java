package persistence;

import model.Zoo;
import util.DBUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class RepositoryZoo {
    private final EntityManager entityManager;

    public RepositoryZoo() {
        this.entityManager = DBUtil.getEntityManager();
    }

    public void addZoo(Zoo zoo) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(zoo);
            entityManager.getTransaction().commit();
            System.out.println("Zoo added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public Zoo findZooById(int zooId) {
        if (entityManager.find(Zoo.class, zooId) == null) {
            System.out.println("Did not find a Zoo with ID: " + zooId);
        }
        return entityManager.find(Zoo.class, zooId);
    }

    public List<Zoo> listAllZoos() {
        List<Zoo> list = null;
        try {
            list = entityManager.createQuery("FROM Zoo").getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void deleteZoo(Zoo zoo) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.merge(zoo));
            entityManager.getTransaction().commit();
            System.out.println("Zoo deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void assignZooToCustomer(int customerId, int zooId) {
        String sql = "UPDATE ZooCustomer SET zoo_id = :thisZoo WHERE id = :thisCustomer";
        try {
            entityManager.getTransaction().begin();
            entityManager.createQuery(sql)
                    .setParameter("thisCustomer", customerId)
                    .setParameter("thisZoo", zooId)
                    .executeUpdate();
            entityManager.getTransaction().commit();
            System.out.println("Zoo assigned to customer.");
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void updateZooName(String name, int id) {
        try {
            entityManager.getTransaction().begin();
            String sql = "UPDATE Zoo SET name = :thisName WHERE id = :thisId";
            entityManager.createQuery(sql)
                    .setParameter("thisName", name)
                    .setParameter("thisId", id)
                    .executeUpdate();
            entityManager.getTransaction().commit();
            System.out.println("Zoo name updated.");
            entityManager.clear();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void updateZooLocation(String location, int id) {
        try {
            entityManager.getTransaction().begin();
            String sql = "UPDATE Zoo SET location = :thisLocation WHERE id = :thisId";
            entityManager.createQuery(sql)
                    .setParameter("thisLocation", location)
                    .setParameter("thisId", id)
                    .executeUpdate();
            entityManager.getTransaction().commit();
            System.out.println("Zoo location updated.");
            entityManager.clear();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}
