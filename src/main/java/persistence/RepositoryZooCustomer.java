package persistence;

import model.Ticket;
import model.ZooCustomer;
import util.DBUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class RepositoryZooCustomer {
    private final EntityManager entityManager;

    public RepositoryZooCustomer() {
        this.entityManager = DBUtil.getEntityManager();
    }

    public void addZooCustomer(ZooCustomer zooCustomer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(zooCustomer);
            entityManager.getTransaction().commit();
            System.out.println("ZooCustomer registered successfully");
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public ZooCustomer findZooCustomerById(int customerId) {
        if (entityManager.find(ZooCustomer.class, customerId) == null) {
            System.out.println("Did not find a ZooCustomer with ID: " + customerId);
        }
        return entityManager.find(ZooCustomer.class, customerId);
    }

    public List<ZooCustomer> listAllCustomers() {
        List<ZooCustomer> list = null;
        try {
            list = entityManager.createQuery("FROM ZooCustomer").getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void deleteZooCustomer(ZooCustomer zooCustomer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.merge(zooCustomer));
            entityManager.getTransaction().commit();
            System.out.println("ZooCustomer deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void updateZooCustomerType(String type, int id) {
        try {
            entityManager.getTransaction().begin();
            String sql = "UPDATE ZooCustomer SET type = :thisType WHERE id = :thisId";
            entityManager.createQuery(sql)
                    .setParameter("thisType", type)
                    .setParameter("thisId", id)
                    .executeUpdate();
            entityManager.getTransaction().commit();
            System.out.println("ZooCustomer type updated.");
            entityManager.clear();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void updateZooCustomerName(String newName, int id) {
        try {
            entityManager.getTransaction().begin();
            String sql = "UPDATE ZooCustomer SET name = :thisName WHERE id = :thisId";
            entityManager.createQuery(sql)
                    .setParameter("thisName", newName)
                    .setParameter("thisId", id)
                    .executeUpdate();
            entityManager.getTransaction().commit();
            System.out.println("ZooCustomer name updated.");
            entityManager.clear();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void updateZooCustomerToNotNew(int id) {
        try {
            entityManager.getTransaction().begin();
            String sql = "UPDATE ZooCustomer SET isNew = :thisNew WHERE id = :thisId";
            entityManager.createQuery(sql)
                    .setParameter("thisNew", false)
                    .setParameter("thisId", id)
                    .executeUpdate();
            entityManager.getTransaction().commit();
            System.out.println("ZooCustomer has been updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public List<ZooCustomer> listCustomersByZoo() {
        List<ZooCustomer> list = null;
        try {
            list = entityManager.createQuery("FROM ZooCustomer ORDER BY Zoo").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Ticket getMostCommonTicket() {
        RepositoryTicket repositoryTicket = new RepositoryTicket();

        int singleTicketAmount = 0;
        int familyTicketAmount = 0;
        int adultTicketAmount = 0;
        int childTicketAmount = 0;
        int mostCommonTicketAmount;
        Ticket mostCommonTicket;
        int mostCommonTicketId;


        // single
        for (ZooCustomer zooCustomer : listAllCustomers()) {
            if (zooCustomer.getTicket().getId() == 1) {
                singleTicketAmount++;
            }
        }
        mostCommonTicketAmount = singleTicketAmount;
        mostCommonTicketId = 1;

        // family
        for (ZooCustomer zooCustomer : listAllCustomers()) {
            if (zooCustomer.getTicket().getId() == 2) {
                familyTicketAmount++;
            }
        }
        if (mostCommonTicketAmount < familyTicketAmount) {
            mostCommonTicketAmount = familyTicketAmount;
            mostCommonTicketId = 2;
        }

        // adult
        for (ZooCustomer zooCustomer : listAllCustomers()) {
            if (zooCustomer.getTicket().getId() == 3) {
                adultTicketAmount++;
            }
        }
        if (mostCommonTicketAmount < adultTicketAmount) {
            mostCommonTicketAmount = adultTicketAmount;
            mostCommonTicketId = 3;
        }

        // child
        for (ZooCustomer zooCustomer : listAllCustomers()) {
            if (zooCustomer.getTicket().getId() == 4) {
                childTicketAmount++;
            }
        }
        if (mostCommonTicketAmount < childTicketAmount) {
            mostCommonTicketAmount = childTicketAmount;
            mostCommonTicketId = 4;
        }
        System.out.println("Most commonly bought ticket amount: " + mostCommonTicketAmount);
        mostCommonTicket = repositoryTicket.findTicketById(mostCommonTicketId);
        return mostCommonTicket;
    }
}
