package persistence;

import model.Ticket;
import model.ZooCustomer;
import util.DBUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class RepositoryTicket {
    private final EntityManager entityManager;

    public RepositoryTicket() {
        this.entityManager = DBUtil.getEntityManager();
    }

    public void addTicket(Ticket ticket) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(ticket);
            entityManager.getTransaction().commit();
            System.out.println("Ticket made successfully");
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public Ticket findTicketById(int ticketId) {
        if (entityManager.find(Ticket.class, ticketId) == null) {
            System.out.println("Did not find a Ticket with ID: " + ticketId);
        }
        return entityManager.find(Ticket.class, ticketId);
    }

    public List<Ticket> listAllTickets() {
        List<Ticket> list = null;
        try {
            list = entityManager.createQuery("FROM Ticket").getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void deleteTicket(Ticket ticket) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.merge(ticket));
            entityManager.getTransaction().commit();
            System.out.println("Ticket deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void assignTicketToCustomer(int customerId, int ticketId) {
        String sql = "UPDATE ZooCustomer SET ticket_id = :thisTicket WHERE id = :thisCustomer";
        try {
            entityManager.getTransaction().begin();
            entityManager.createQuery(sql)
                    .setParameter("thisCustomer", customerId)
                    .setParameter("thisTicket", ticketId)
                    .executeUpdate();
            entityManager.getTransaction().commit();
            System.out.println("Ticket assigned to customer.");
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
    }
