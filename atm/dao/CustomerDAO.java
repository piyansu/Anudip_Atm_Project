package atm.dao;

import atm.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class CustomerDAO {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public CustomerDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("ATM_PU");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }

    public void createCustomer(Customer customer) {
        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();
    }

    public Customer getCustomerById(int customerId) {
        return entityManager.find(Customer.class, customerId);
    }

    public List<Customer> getAllCustomers() {
        Query query = entityManager.createQuery("SELECT c FROM Customer c");
        return query.getResultList();
    }

    public void updateCustomer(Customer customer) {
        entityManager.getTransaction().begin();
        entityManager.merge(customer);
        entityManager.getTransaction().commit();
    }

    public void deleteCustomer(Customer customer) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(customer) ? customer : entityManager.merge(customer));
        entityManager.getTransaction().commit();
    }
}
