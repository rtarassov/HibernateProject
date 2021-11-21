package persistence;

import model.Employee;
import util.DBUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class RepositoryEmployee {
    private final EntityManager entityManager;

    public RepositoryEmployee() {
        this.entityManager = DBUtil.getEntityManager();
    }

    public void addEmployee(Employee employee) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(employee);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public List<Employee> listAllEmployees() {
        List<Employee> list = null;
        try {
            list = entityManager.createQuery("FROM Employee").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Employee findEmployeeById(int employeeId) {
        if (entityManager.find(Employee.class, employeeId) == null) {
            System.out.println("Did not find an Employee with ID: " + employeeId);
        }
        return entityManager.find(Employee.class, employeeId);
    }

    public void deleteEmployee(Employee employee) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.merge(employee));
            entityManager.getTransaction().commit();
            System.out.println("Employee deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void updateEmployeeSalary(int id, int newSalary) {
        try {
            entityManager.getTransaction().begin();
            String sql = "UPDATE Employee SET salary = :thisSalary WHERE id = :thisId";
            entityManager.createQuery(sql)
                    .setParameter("thisSalary", newSalary)
                    .setParameter("thisId", id)
                    .executeUpdate();
            entityManager.getTransaction().commit();
            System.out.println("Employee salary updated.");
            entityManager.clear();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void updateEmployeeTitle(String newTitle, int id) {
        try {
            entityManager.getTransaction().begin();
            String sql = "UPDATE Employee SET title = :thisTitle WHERE id = :thisId";
            entityManager.createQuery(sql)
                    .setParameter("thisTitle", newTitle)
                    .setParameter("thisId", id)
                    .executeUpdate();
            entityManager.getTransaction().commit();
            System.out.println("Employee title updated.");
            entityManager.clear();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void updateEmployeeName(String newName, int id) {
        try {
            entityManager.getTransaction().begin();
            String sql = "UPDATE Employee SET name = :thisName WHERE id = :thisId";
            entityManager.createQuery(sql)
                    .setParameter("thisName", newName)
                    .setParameter("thisId", id)
                    .executeUpdate();
            entityManager.getTransaction().commit();
            System.out.println("Employee name updated.");
            entityManager.clear();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void assignEmployeeToZoo(int employeeId, int zooId) {
        String sql = "UPDATE Employee SET zoo_id = :thisZooId WHERE id = :thisEmployeeId";
        try {
            entityManager.getTransaction().begin();
            entityManager.createQuery(sql)
                    .setParameter("thisZooId", zooId)
                    .setParameter("thisEmployeeId", employeeId)
                    .executeUpdate();
            entityManager.getTransaction().commit();
            System.out.println("Employee assigned to Zoo.");
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}
